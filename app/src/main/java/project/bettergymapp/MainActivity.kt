package project.bettergymapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.room.Room
import project.bettergymapp.data.Database
import project.bettergymapp.data.repository.IExerciseRepository
import project.bettergymapp.data.repository.IRoutineRepository
import project.bettergymapp.data.repository.ISessionRepository
import project.bettergymapp.data.repository.RoomExerciseRepository
import project.bettergymapp.data.repository.RoomRoutinesRepository
import project.bettergymapp.data.repository.RoomSessionRepository
import project.bettergymapp.ui.screen.MainScreen

class MainActivity : ComponentActivity() {

    companion object{
        lateinit var routineRepository: IRoutineRepository
        lateinit var exerciseRepository: IExerciseRepository
        lateinit var sessionRepository: ISessionRepository

        private lateinit var database : Database
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            database = Room.databaseBuilder(
                applicationContext,
                Database::class.java,
                "gym_database"
            ).fallbackToDestructiveMigration().build()

            routineRepository = RoomRoutinesRepository(database.routineDao())
            exerciseRepository = RoomExerciseRepository(database.exerciseDao())
            sessionRepository = RoomSessionRepository(database.sessionDao())


            //DeleteAllRoutines()

            MainScreen("Adam")
        }
    }

    @Composable
    fun DeleteAllRoutines() {
        LaunchedEffect(Unit) {
            routineRepository.deleteAll()
        }

    }
}

