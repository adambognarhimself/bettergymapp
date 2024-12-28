package project.bettergymapp.data.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface ExerciseApi {
    @GET("api/exercise")
    fun getExercises(): Call<List<ExerciseFromApi>>
}