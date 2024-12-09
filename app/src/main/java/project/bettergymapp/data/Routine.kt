package project.bettergymapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "routine")
@TypeConverters(Converters::class)
data class Routine(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val description: String,
    val exercises: List<Exercise> = emptyList()
)