package project.bettergymapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow
import project.bettergymapp.data.Session

@Dao
interface SessionDao {
    @Insert
    suspend fun insert(session: Session)

    @Update
    suspend fun update(session: Session)

    @Delete
    suspend fun delete(session: Session)

    @Query("SELECT * FROM session")
    fun getAllSessions(): Flow<List<Session>>

    @Query("SELECT * FROM session WHERE id = :id")
    fun getSessionById(id: Int): Session?
}