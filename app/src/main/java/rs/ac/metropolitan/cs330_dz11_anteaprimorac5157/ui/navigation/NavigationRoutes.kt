package rs.ac.metropolitan.cs330_dz11_anteaprimorac5157.ui.navigation

sealed class NavigationRoutes(val route: String) {
    object Home : NavigationRoutes(route = "home")
    object NewDatingUser : NavigationRoutes(route = "new")
    object DatingUserDetailScreen: NavigationRoutes(route = "detail/{elementId}"){
        fun createRoute(elementId: String) = "detail/$elementId"
    }
}