package project.bettergymapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters

@Entity(tableName = "routine")
@TypeConverters(Converters::class)
data class Routine(
    var id: String = "",
    var name: String = "",
    var description: String = "",
    var exercises: List<Exercise> = emptyList()
)
