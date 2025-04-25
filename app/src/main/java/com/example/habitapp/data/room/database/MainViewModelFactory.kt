//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import com.example.habitapp.data.room.database.AppDatabase
//
//class MainViewModelFactory(private val db: AppDatabase) : ViewModelProvider.Factory {
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
//        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
//            @Suppress("UNCHECKED_CAST")
//            return MainViewModel(db) as T
//        }
//        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}
