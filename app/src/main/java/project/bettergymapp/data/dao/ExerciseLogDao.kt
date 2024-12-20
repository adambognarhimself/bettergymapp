package project.bettergymapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow
import project.bettergymapp.data.ExerciseLog

@Dao
interface ExerciseLogDao {
    @Insert
    suspend fun insert(exerciseLog: ExerciseLog)

    @Update
    suspend fun update(exerciseLog: ExerciseLog)

    @Delete
    suspend fun delete(exerciseLog: ExerciseLog)

    @Query("SELECT * FROM exerciseLog")
    suspend fun getAllExerciseLogs(): Flow<List<ExerciseLog>>

    @Query("SELECT * FROM exerciseLog WHERE id = :id")
    suspend fun getExerciseLogById(id: Int): ExerciseLog?
}