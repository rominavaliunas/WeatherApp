package romina.valiunas.data1.service

import android.content.Context
import romina.valiunas.data1.*
import romina.valiunas.data1.mapper.WeatherMapperService
import romina.valiunas.data1.service.api.WeatherApi
import romina.valiunas.domain1.entities.WeatherForecast
import romina.valiunas.domain1.utils.Result
import java.io.IOException

class WeatherService(context: Context) {

    companion object {
        private const val PUBLIC_LON = "-56.1"
        private const val PUBLIC_LAT = "-34.9"
        private const val API_EXCLUDE_VALUE = "hourly,minutely,current,alerts"
        private const val API_UNITS_METRIC = "metric"
    }
    private val api: WeatherRequestGenerator = WeatherRequestGenerator(context)
    private val mapper: WeatherMapperService = WeatherMapperService()

    fun getWeatherReport(): Result<WeatherForecast> {
        val callResponse = api.createService(WeatherApi::class.java)
            .getWeatherByLatAndLon(
                PUBLIC_LAT,
                PUBLIC_LON,
                API_EXCLUDE_VALUE,
                API_UNITS_METRIC)
        try {
            val response = callResponse.execute()
            if(response.isSuccessful) {
                response.body()?.let {
                    mapper.transform(it)
               }?.let {
                    return Result.Success(it)
               }
            }
            return Result.Failure(Exception(response.message()))
        } catch (e: RuntimeException) {
            return Result.Failure(Exception("Bad request/response"))
        } catch (e: IOException) {
            return Result.Failure(Exception("Bad request/response"))
        }
    }

}