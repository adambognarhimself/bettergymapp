package project.bettergymapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "exercise")
data class Exercise(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) var id: String = "",
    @ColumnInfo(name = "name") val name: String = "",
    val lastLog: ExerciseLog? = null
)
