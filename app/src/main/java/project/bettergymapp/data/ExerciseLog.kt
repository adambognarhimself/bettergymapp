package project.bettergymapp.data

data class ExerciseLog(
    val exerciseId: Int,
    val sets: Map<Int,Int> //A dictionary of reps to weight
)
