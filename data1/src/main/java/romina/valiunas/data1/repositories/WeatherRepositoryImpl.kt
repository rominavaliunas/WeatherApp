package romina.valiunas.data1.repositories

import romina.valiunas.data1.service.WeatherService
import romina.valiunas.domain1.entities.WeatherForecast
import romina.valiunas.domain1.repositories.WeatherRepository
import romina.valiunas.domain1.utils.Result

class WeatherRepositoryImpl(
    private val weatherService: WeatherService,
    //private val weatherDatabase: WeatherDatabase
) : WeatherRepository {

    //private val mapper = WeatherMapperLocal()

    override fun getWeatherByUrl (
        getFromRemote: Boolean
    ): Result<WeatherForecast>? =
        if (getFromRemote) {
            val weatherResult = weatherService.getWeatherReport()
            if (weatherResult is Result.Success) {
                //insertOrUpdateWeather(weatherResult.data)
            }
            weatherResult
        }else null
/*
        } else {
            Log.d("TAG", "")
        } else {
            weatherDatabase.weatherDao().findById(lat)?.let {
                return Result.Success()
            } ?: Result.Failure(Exception("Weather not found"))
        }*/
/*
    private fun insertOrUpdateWeather(weather: WeatherResponse) {
        weatherDatabase.weatherDao().insert(mapper.transformToRepository(weather))
    }*/
}