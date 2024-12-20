package project.bettergymapp.data.repository

import kotlinx.coroutines.flow.Flow
import project.bettergymapp.data.Routine
import project.bettergymapp.data.dao.RoutineDao

class RoomRoutinesRepository(private val dao: RoutineDao) : IRoutineRepository {


    override fun getAllRoutines(): Flow<List<Routine>> = dao.getAllRoutines()

    override suspend fun insert(routine: Routine) {
        dao.insert(routine)
    }

    override suspend fun delete(routine: Routine) {
        dao.delete(routine)
    }

    override suspend fun update(routine: Routine) {
        dao.update(routine)
    }

    override suspend fun deleteAll() {
        dao.deleteAll() // Implement this function
    }
}