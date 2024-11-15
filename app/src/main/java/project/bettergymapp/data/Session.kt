package project.bettergymapp.data

import java.time.LocalDate

data class Session(
    val id: Int,
    val date: LocalDate,
    val duration: Int,
    val log: List<ExerciseLog>
)
