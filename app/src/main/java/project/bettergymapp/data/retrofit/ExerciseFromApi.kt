package project.bettergymapp.data.retrofit

data class ExerciseFromApi(
    val id: Int,
    val name: String,
    val description: String,
    val muscleGroup: String,
    val difficulty: String
)