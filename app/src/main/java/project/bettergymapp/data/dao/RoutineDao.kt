package project.bettergymapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import project.bettergymapp.data.Routine

@Dao
interface RoutineDao {
    @Insert
    suspend fun insert(routine: Routine)

    @Update
    suspend fun update(routine: Routine)

    @Delete
    suspend fun delete(routine: Routine)

    @Query("SELECT * FROM routine")
    fun getAllRoutines(): Flow<List<Routine>>

    @Query("SELECT * FROM routine WHERE id = :id")
    fun getRoutineById(id: Int): Routine?

    @Query("DELETE FROM routine")
    suspend fun deleteAll()
}