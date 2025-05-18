import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.habitapp.HabitApplication
import com.example.habitapp.data.habit.Habit
import com.example.habitapp.data.util.DatabaseResult
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import java.time.LocalDate

class DailyWorker(appContext: Context, workerParams: WorkerParameters) : CoroutineWorker(appContext, workerParams) {
    override suspend fun doWork(): Result {
        Log.d("dailyWorker", "Running Daily worker")

        val habitRepo = HabitApplication.getHabitRepository()
        val currentUser = HabitApplication.getAuthRepository().currentUser

        if (currentUser == null) {
            Log.w("dailyWorker", "Current user is null. Skipping work.")
            return Result.success()
        }

        val groupNamesResult = habitRepo.getAllGroupNames(currentUser.uid.toString())
            .filter { it !is DatabaseResult.Loading }
            .first()
        if (groupNamesResult !is DatabaseResult.Success) {
            Log.e("dailyWorker", "Failed to fetch group names: $groupNamesResult")
            return Result.retry()
        }

        val groupNames = groupNamesResult.data
        Log.d("dailyWorker", "Fetched groups: $groupNames")

//        for (group in groupNames) {
//            val habitsResult = habitRepo.getHabitsInGroupOnDate(
//                currentUser.uid.toString(),
//                group,
//                LocalDate.now().minusDays(1).toString()
//            ).filter { it !is DatabaseResult.Loading }
//                .first()
//
//            if (habitsResult !is DatabaseResult.Success) {
//                Log.e("dailyWorker", "Failed to fetch habits for $group: $habitsResult")
//                continue
//            }
//
//            for (habit in habitsResult.data.filterNotNull()) {
//                if (!habit.suspended) {
//                    if (habit.daysSinceReset < habit.timeframe) {
//                        habit.daysSinceReset += 1
//                    } else {
//                        habit.daysSinceReset = 1
//                        habit.progress = 0
//                    }
//                    habit.date = LocalDate.now().toString()
//                    habit.group = group
//                    Log.d("dailyWorker", "Updating habit: $habit")
//
//                    habitRepo.edit(habit, currentUser.uid.toString())
//                }
//            }
//        }

        for (group in groupNames) {
            var dateOffset = 1
            var habitsResult: DatabaseResult<List<Habit?>>? = null

            // Find the most recent habits
            while (dateOffset <= 10) {
                habitsResult = habitRepo.getHabitsInGroupOnDate(
                    currentUser.uid.toString(),
                    group,
                    LocalDate.now().minusDays(dateOffset.toLong()).toString()
                ).filter { it !is DatabaseResult.Loading }
                    .first()

                if (habitsResult is DatabaseResult.Success && habitsResult.data.isNotEmpty()) {
                    break
                }

                dateOffset++
            }

            if (habitsResult !is DatabaseResult.Success || habitsResult.data.isEmpty()) {
                Log.e(
                    "dailyWorker",
                    "No habits found for group $group after checking last 10 days."
                )
                continue
            }

            val lastKnownDate = LocalDate.now().minusDays(dateOffset.toLong()) // Last habit date

            for (habit in habitsResult.data.filterNotNull()) {
                if (!habit.suspended) {
                    var habitDate = lastKnownDate.plusDays(1)
                    while (habitDate.isBefore(LocalDate.now()) || habitDate.isEqual(LocalDate.now())) {
                        if (!habit.suspended) {
                            if (habit.daysSinceReset < habit.timeframe) {
                                habit.daysSinceReset += 1
                            } else {
                                habit.daysSinceReset = 1
                                habit.progress = 0
                            }
                            habit.date = habitDate.toString()
                            habit.group = group
                            Log.d("dailyWorker", "Updating habit: $habit")

                            habitRepo.edit(habit, currentUser.uid.toString())

                            habitDate = habitDate.plusDays(1) // Move to next missing day
                        }
                    }
                }
            }
        }

            return Result.success()
        }
    }
