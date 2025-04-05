//package com.example.habitapp.presentation.screens.Register
//
//import androidx.compose.foundation.layout.padding
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalContext
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import androidx.room.Room
//import com.example.habitapp.data.room.database.user.UserDatabase
//import com.example.habitapp.presentation.components.RegisterForm
//
//@Composable
//fun RegisterScreen(text:String,
//                modifier: Modifier = Modifier) {
//    val context = LocalContext.current
//    val db by lazy {
//       Room.databaseBuilder(
//            context,
//            UserDatabase::class.java,
//            "users.db"
//        ).build()
//    }
//    val viewModel: UserViewModel = ViewModelProvider(
//        context as androidx.lifecycle.ViewModelStoreOwner,
//        object : ViewModelProvider.Factory {
//            override fun <T : ViewModel> create(modelClass: Class<T>): T {
//                return UserViewModel(db.dao) as T
//            }
//        }
//    )[UserViewModel::class.java]
//
//    val state by viewModel.state.collectAsState()
//
//    Text(text = text)
//
//
//    RegisterForm(state = state, onEvent = viewModel::onEvent, modifier = modifier.padding())
//}
