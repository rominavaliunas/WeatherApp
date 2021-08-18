package romina.valiunas.data1.service

import android.content.Context
import romina.valiunas.data1.WeatherRequestGenerator
import romina.valiunas.data1.mapper.WeatherMapperService
import romina.valiunas.data1.service.api.WeatherApi
import romina.valiunas.domain1.entities.Weather
import romina.valiunas.domain1.utils.Result
import java.io.IOException

class WeatherService(context: Context) {

    private val api: WeatherRequestGenerator = WeatherRequestGenerator(context)
    private val mapper: WeatherMapperService = WeatherMapperService()

    fun getWeatherById(id: Int): Result<Weather> {
        val callResponse = api.createService(WeatherApi::class.java).getWeatherByLatAndLon(id)
        try {
            val response = callResponse.execute()
            if(response.isSuccessful) {
                response.body()?.data?.weatherReports?.firstOrNull()?.let {
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