package project.bettergymapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.room.Room
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore
import project.bettergymapp.data.Database
import project.bettergymapp.data.repository.IExerciseRepository
import project.bettergymapp.data.repository.IRoutineRepository
import project.bettergymapp.data.repository.ISessionRepository
import project.bettergymapp.data.repository.RoomExerciseRepository
import project.bettergymapp.data.repository.RoomRoutinesRepository
import project.bettergymapp.data.repository.RoomSessionRepository
import project.bettergymapp.data.service.AccountService
import project.bettergymapp.data.service.AccountServiceImpl
import project.bettergymapp.ui.screen.NavGraph


class MainActivity : ComponentActivity() {

    companion object{
        lateinit var routineRepository: IRoutineRepository
        lateinit var exerciseRepository: IExerciseRepository
        lateinit var sessionRepository: ISessionRepository
        lateinit var accountService: AccountService

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
            accountService = AccountServiceImpl()

            //DeleteAllRoutines()

            val db = Firebase.firestore
            val auth = FirebaseAuth.getInstance().currentUser




//            val user = hashMapOf(
//                "first" to "Ada",
//                "last" to "Lovelace",
//                "born" to 1815,
//            )
//
//            db.collection("users")
//                .add(user)
//                .addOnSuccessListener { documentReference ->
//                    Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
//                }
//                .addOnFailureListener { e ->
//                    Log.w(TAG, "Error adding document", e)
//                }

            NavGraph()
        }
    }



    @Composable
    fun DeleteAllRoutines() {
        LaunchedEffect(Unit) {
            routineRepository.deleteAll()
        }

    }
}

