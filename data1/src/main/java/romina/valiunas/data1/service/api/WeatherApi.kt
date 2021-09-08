package romina.valiunas.data1.service.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import romina.valiunas.data1.service.response.OneCallResponse

interface WeatherApi {

    @GET("onecall")
    fun getWeatherByLatAndLon(
        @Query("lat") lat: String,
        @Query("lon") lon: String,
        @Query("exclude") exclude: String,
        @Query("units") units: String
    ): Call<OneCallResponse>
}