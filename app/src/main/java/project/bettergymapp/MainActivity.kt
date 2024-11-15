package project.bettergymapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import project.bettergymapp.data.repository.IRoutineRepository
import project.bettergymapp.data.repository.MemoryRoutineRepository
import project.bettergymapp.ui.screen.MainScreen

class MainActivity : ComponentActivity() {

    companion object{
        lateinit var repository: IRoutineRepository
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            repository = MemoryRoutineRepository()

            MainScreen("Adam")
        }
    }
}
