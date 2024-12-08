package project.bettergymapp.data.repository

import androidx.compose.runtime.mutableStateListOf
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import project.bettergymapp.data.Routine

class MemoryRoutineRepository : IRoutineRepository {

//    private var list = mutableStateListOf(
//        Routine(
//            id = 1,
//            name = "Test1",
//            description = "Test1",
//            //exercises =
//
//        ),
//        Routine(
//            id = 2,
//            name = "Test2",
//            description = "Test2",
//            //exercises =
//
//        ),
//        Routine(
//            id = 3,
//            name = "Test3",
//            description = "Test3",
//            //exercises =
//
//        ),
//
//    )

    private val list = mutableStateListOf<Routine>()

    override fun getAllRoutines(): Flow<List<Routine>> = flow {
        emit(list)
    }

    override suspend fun insert(routine: Routine) {
        delay(1000)
        list.add(routine.copy())
    }

    override suspend fun update(routine: Routine) {
        delay(1000)
        for (item in list) {
            if (item.id == routine.id)
                list[list.indexOf(item)] = routine
        }
    }

    override suspend fun delete(routine: Routine) {
        delay(1000)
        list.remove(routine)
    }
}