package project.bettergymapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class ExerciseLog(
    val id: Int = 0,
    val sets: Map<Int,Int> //A dictionary of reps to weight
)
