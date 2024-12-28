package project.bettergymapp

import android.os.Bundle
import android.widget.Toast
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
import project.bettergymapp.data.retrofit.ExerciseFromApi
import project.bettergymapp.data.retrofit.RetrofitClient
import project.bettergymapp.ui.screen.NavGraph
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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


            fetchProducts()
            NavGraph()
        }
    }

    private fun fetchProducts() {
        val call = RetrofitClient.instance.getExercises()
        call.enqueue(object : Callback<List<ExerciseFromApi>> {
            override fun onResponse(call: Call<List<ExerciseFromApi>>, response: Response<List<ExerciseFromApi>>) {
                if (response.isSuccessful) {
                    val products = response.body()
                    products?.let {
                        // Handle the product list here
                        Toast.makeText(this@MainActivity, "Fetched ${it.size} products", Toast.LENGTH_LONG).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Error: ${response.code()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<List<ExerciseFromApi>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Failed: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    @Composable
    fun DeleteAllRoutines() {
        LaunchedEffect(Unit) {
            routineRepository.deleteAll()
        }

    }
}

