package project.bettergymapp.ui.screen

import Timer
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import project.bettergymapp.R
import project.bettergymapp.data.Exercise
import project.bettergymapp.data.Routine
import project.bettergymapp.data.viewmodel.RoutineViewModel

@Composable
fun WorkoutPage(
    routine: Routine,
    navController: NavController,
    viewModel: RoutineViewModel = viewModel(factory = RoutineViewModel.Factory),
) {
    var exercises by remember { mutableStateOf(routine.exercises) }
    val addExercise = remember { mutableStateOf(false) }
    val showTimer = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize() // This allows stacking elements.
    ) {
        // Main content
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            WorkoutHeader(
                name = routine.name,
                onFinish = {
                    viewModel.update(routine.copy(exercises = exercises))
                    navController.popBackStack()
                },
                onTimerClick = { /*TODO*/ }
            )

            LazyColumn(
                modifier = Modifier.weight(1f) // Allows LazyColumn to take up remaining space.
            ) {
                items(exercises.size) { index ->
                    WorkoutItem(
                        exercise = exercises[index],
                        onUpdate = { updatedExercise ->
                            exercises = exercises.toMutableList().apply {
                                set(index, updatedExercise)
                            }
                        },
                        onDoneClick = { showTimer.value = true }
                    )
                }

                item {
                    TextButton(
                        onClick = { addExercise.value = true },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp, bottom = 10.dp),
                    ) {
                        Text(
                            text = "Add new exercise",
                            style = TextStyle(
                                fontSize = 15.sp,
                                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                                color = colorResource(id = R.color.happyblue)
                            ),
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }

            if (addExercise.value) {
                AddExerciseDialog(
                    onDismissRequest = { addExercise.value = false },
                    onConfirmation = { name ->
                        exercises = exercises + Exercise(name = name)
                        addExercise.value = false
                    }
                )
            }
        }

        // Timer at the bottom
        if (showTimer.value) {
            Timer(
                initialTime = 120,
                onDismiss = { showTimer.value = false },
                modifier = Modifier
                    .align(Alignment.BottomCenter) // Aligns the timer to the bottom.
                    .fillMaxWidth()
                    .padding(16.dp)
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun WorkoutPagePreview() {
    val exercises = listOf(
        Exercise(name = "Exercise 1"),
        Exercise(name = "Exercise 2"),
        Exercise(name = "Exercise 3"),
    )

    val sampleRoutines = listOf(
        Routine(name = "Routine 1", description = "", exercises = exercises),
        Routine(name = "Routine 2", description = "", exercises = exercises)
    )

    //WorkoutPage(routine = sampleRoutines[0])
}