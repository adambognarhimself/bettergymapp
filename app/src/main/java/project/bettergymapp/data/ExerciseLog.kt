package project.bettergymapp.data

data class ExerciseLog(
    var id: String = "",
    var sets: List<Pair<Int, Int>> = emptyList()
)

