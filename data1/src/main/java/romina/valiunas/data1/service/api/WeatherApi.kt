package romina.valiunas.data1.service.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import romina.valiunas.data1.EXCLUDE
import romina.valiunas.data1.PUBLIC_LAT
import romina.valiunas.data1.PUBLIC_LON
import romina.valiunas.data1.database.response.DataBaseResponse
import romina.valiunas.data1.service.response.WeatherBaseResponse
import romina.valiunas.data1.service.response.WeatherResponse

interface WeatherApi {
    @GET("onecall?lat=${PUBLIC_LAT}&lon=${PUBLIC_LON}&exclude=${EXCLUDE}&appid={apikey}")
    fun getWeatherByLatAndLon(@Path("weatherId")id: Int): Call<WeatherBaseResponse<DataBaseResponse<ArrayList<WeatherResponse>>>>
}