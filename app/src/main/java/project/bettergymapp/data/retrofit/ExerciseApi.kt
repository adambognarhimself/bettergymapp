package project.bettergymapp.data.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ExerciseApi {
    @GET("api/exercise")
    fun getExercises(): Call<List<ExerciseFromApi>>
    @GET("api/exercise")
    fun getExercisesByFilters(
        @Query("name") name: String?,
        @Query("muscleGroup") muscleGroup: String?
    ): Call<List<ExerciseFromApi>>

}