package rs.ac.metropolitan.cs330_dz11_anteaprimorac5157.ui.navigation

import android.widget.Toast
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import rs.ac.metropolitan.cs330_dz11_anteaprimorac5157.ui.AppViewModel
import rs.ac.metropolitan.cs330_dz11_anteaprimorac5157.ui.view.DatingUserDetailScreen
import rs.ac.metropolitan.cs330_dz11_anteaprimorac5157.ui.view.HomeScreen
import rs.ac.metropolitan.cs330_dz11_anteaprimorac5157.ui.view.NewDatingUserScreen

@Composable
fun NavSetup() {
    val navController = rememberNavController()
    val vm: AppViewModel = viewModel()
    val paddingValues = PaddingValues()
    vm.navController = navController

    NavHost(navController = navController, startDestination = NavigationRoutes.Home.route) {
        composable(route = NavigationRoutes.Home.route) {
            HomeScreen(
                datingUserList = vm.datingUsers,
                loadDatingUsers = vm::loadDatingUsers,
                goToNewDatingUser = { navController.goToNewDatingUser() },
                goToDetails = { navController.goToDetails(it) }
            )
        }
        composable(route = NavigationRoutes.DatingUserDetailScreen.route) { navBackStackEntry ->
            val elementId = navBackStackEntry.arguments?.getString("elementId")
            if (elementId != null) {
                DatingUserDetailScreen(
                    datingUser = vm.getDatingUser(elementId),
                    goBack = { navController.goBack() },
                    deleteDatingUser = { vm.deleteDatingUser(elementId) },
                )
            } else {
                Toast.makeText(
                    navController.context,
                    "Error, elementId is required!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        composable(route = NavigationRoutes.NewDatingUser.route) {
            NewDatingUserScreen(
                submitDatingUser = vm::submitDatingUser,
                goBack = { navController.goBack() }
            )
        }
    }
}

fun NavController.goBack() {
    this.popBackStack()
}

fun NavController.goToNewDatingUser() {
    this.navigate(NavigationRoutes.NewDatingUser.route)
}

fun NavController.goToDetails(id: String) {
    this.navigate(NavigationRoutes.DatingUserDetailScreen.createRoute(id))
}
