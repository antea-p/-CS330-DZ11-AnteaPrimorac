package rs.ac.metropolitan.cs330_dz11_anteaprimorac5157.ui

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import rs.ac.metropolitan.common.DatingUser
import rs.ac.metropolitan.cs330_dz11_anteaprimorac5157.ui.navigation.NavigationRoutes
import rs.ac.metropolitan.repository.Repository


class AppViewModel : ViewModel() {
    lateinit var navController: NavHostController
    val repository = Repository()
    var granted = mutableStateOf(false)

    private val _datingUsers = MutableLiveData<List<DatingUser>>()
    val datingUsers: LiveData<List<DatingUser>>
        get() = _datingUsers

    fun loadDatingUsers() {
        GlobalScope.launch {
            repository.loadDatingUsers()
            MainScope().launch {
                repository.datingUsersFlow.collect {
                    _datingUsers.value = it
                }
            }
        }
    }

    fun getDatingUser(id: String): DatingUser? {
        return _datingUsers.value?.find { it.id == id }
    }

    fun deleteDatingUser(id: String) {
        Log.d("AppViewModel", "Deleting user...")
        GlobalScope.launch {
            repository.deleteDatingUser(id)
        }
        goBack()
    }

    fun submitDatingUser(datingUser: DatingUser) {
        GlobalScope.launch {
            repository.submitDatingUser(datingUser)
        }
    }

    fun navigateToDatingUserDetails(id: String) {
        navController.navigate(NavigationRoutes.DatingUserDetailScreen.createRoute(id))
    }

    fun navigateToNewDatingUser() {
        navController.navigate(NavigationRoutes.NewDatingUser.route)
    }

    fun goBack() {
        navController.popBackStack()
    }
}