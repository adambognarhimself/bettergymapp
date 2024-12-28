package project.bettergymapp.ui.screen

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import project.bettergymapp.data.Routine

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
){
    NavHost(
        navController = navController,
        startDestination = "home"
    ){
        composable("home") {
            MainScreen(
                onNavigateToWorkout = { routine ->
                    val routineJson = Gson().toJson(routine)
                    navController.navigate("workout/$routineJson")
                },
                onNavigateToRoutineAdd = {
                    navController.navigate("routine add")
                },
                onNavigateToExerciseAdd = {
                    navController.navigate("exercise add")
                }
            )
        }

        composable(
            route = "workout/{routine}",
            arguments = listOf(navArgument("routine") { type = NavType.StringType })
        ) { backStackEntry ->
            val routineJson = backStackEntry.arguments?.getString("routine")
            val routine = Gson().fromJson(routineJson, Routine::class.java)
            WorkoutPage(routine,
                onNavigateBack = {
                    navController.navigate("home")
                }
            )
        }

        composable("routine add") {
            RoutineEditScreen(
                onNavigateBack = {
                    navController.navigate("home")
                },
                onNavigateToExerciseScreen = {
                    navController.navigate("exercise add")
                }
            )
        }
        composable("exercise add") {
            ExerciseScreen(
                added = {

                },
                onNavigateBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}