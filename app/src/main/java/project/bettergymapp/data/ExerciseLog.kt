package project.bettergymapp.data

data class ExerciseLog(
    val id: Int = 0,
    val sets: Map<Int,Int> //A dictionary of reps to weight
)
