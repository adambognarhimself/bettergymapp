package project.bettergymapp.data.retrofit

data class ExerciseFromApi(
    val id: Int,
    val description: String,
    val difficulty: String,
    val equipment: String,
    val instructions: String,
    val muscleGroup: String,
    val name: String
)