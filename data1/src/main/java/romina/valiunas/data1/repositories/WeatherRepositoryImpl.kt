package romina.valiunas.data1.repositories

import romina.valiunas.data1.database.WeatherDatabase
import romina.valiunas.data1.mapper.WeatherMapperLocal
import romina.valiunas.data1.service.WeatherService
import romina.valiunas.domain1.entities.WeatherForecast
import romina.valiunas.domain1.repositories.WeatherRepository
import romina.valiunas.domain1.utils.Result

class WeatherRepositoryImpl(
    private val weatherService: WeatherService,
    private val weatherDatabase: WeatherDatabase
) : WeatherRepository {

    private val mapper = WeatherMapperLocal()

    override fun getWeatherByLatAndLon(
        getFromRemote: Boolean
    ): Result<WeatherForecast> =
        if (getFromRemote) {
            val weatherResult = weatherService.getWeatherReport()
            if (weatherResult is Result.Success) {
                insertOrUpdateWeather(weatherResult.data)
            }
            weatherResult
        } else {
            weatherDatabase.weatherDao().findAll()?.let {
                return Result.Success(mapper.transform(it))
            } ?: Result.Failure(Exception("Weather Forecast was not found"))
        }

    private fun insertOrUpdateWeather(weatherForecast: WeatherForecast) {
        weatherDatabase.weatherDao().deleteAll()
        weatherDatabase.weatherDao().insertAll(mapper.transformToRepository(weatherForecast))
    }
}