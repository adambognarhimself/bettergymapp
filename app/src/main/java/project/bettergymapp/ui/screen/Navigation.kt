package project.bettergymapp.ui.screen

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import project.bettergymapp.MainActivity
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
                        routine ->
                    val routineJson = Gson().toJson(routine)
                    navController.navigate("exercise add/$routineJson")
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

        composable(
            "routine add"
        ) {
            RoutineEditScreen(
                onNavigateBack = {
                    navController.navigate("home")
                },
                onNavigateToExerciseScreen = { routine ->
                    val routineJson2 = Gson().toJson(routine)
                    navController.navigate("exercise add/$routineJson2")
                },
                routine = Routine(
                    name = "",
                    description = ""
                ),
                navController = navController
            )
        }

        composable("exercise add/{routine}",
            arguments = listOf(navArgument("routine") { type = NavType.StringType })
        ) { backStackEntry ->
            val routineJson = backStackEntry.arguments?.getString("routine")
            val routine = Gson().fromJson(routineJson, Routine::class.java)
            ExerciseScreen(
                routine = routine,
                onNavigateBack = {
                    navController.popBackStack()
                },
                onSelected = { update ->
                    val previousBackStackEntry = navController.previousBackStackEntry
                    val previousRoute = previousBackStackEntry?.destination?.route

                    if (previousRoute != null) {
                        // Do something based on the previous route
                        when (previousRoute) {
                            "home" -> {
                                // Handle the case when the previous screen was "home"
                                CoroutineScope(Dispatchers.IO).launch {
                                    MainActivity.routineRepository.update(update)
                                }
                            }
                            "routine add" -> {
                                // Handle the case when the previous screen was "routine add"
                                val updateRoutineJson = Gson().toJson(update)
                                previousBackStackEntry.savedStateHandle.set("updatedRoutine", updateRoutineJson)
                                Log.d("NavGraph", "Updated routine: ${update.exercises.joinToString { it.name }}")
                            }
                            // Add more cases as needed
                        }
                    }
                }
            )
        }
    }
}