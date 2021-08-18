package romina.valiunas.data1.repositories

import romina.valiunas.data1.database.WeatherDatabase
import romina.valiunas.data1.mapper.WeatherMapperLocal
import romina.valiunas.data1.service.WeatherService
import romina.valiunas.domain1.entities.Weather
import romina.valiunas.domain1.repositories.WeatherRepository
import romina.valiunas.domain1.utils.Result

class WeatherRepositoryImpl(
    private val weatherService: WeatherService,
    private val weatherDatabase: WeatherDatabase
) : WeatherRepository {

    private val mapper = WeatherMapperLocal()

    override fun getWeatherById(id: Int, getFromRemote: Boolean): Result<Weather> =
        if (getFromRemote) {
            val weatherResult = weatherService.getWeatherById(id)
            if (weatherResult is Result.Success) {
                insertOrUpdateWeather(weatherResult.data)
            }
            weatherResult
        } else {
            weatherDatabase.weatherDao().findById(id)?.let {
                return Result.Success(mapper.transform(it))
            } ?: Result.Failure(Exception("Weather not found"))
        }

    private fun insertOrUpdateWeather(weather: Weather) {
        weatherDatabase.weatherDao().insert(mapper.transformToRepository(weather))
    }
}