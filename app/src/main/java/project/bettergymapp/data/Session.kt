package project.bettergymapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.time.LocalDate

@Entity(tableName = "session")
@TypeConverters(Converters::class)
data class Session(
    var id: String = "",
    var date: LocalDate = LocalDate.now(),
    var duration: Int = 0,
    var log: List<Exercise> = emptyList()
)