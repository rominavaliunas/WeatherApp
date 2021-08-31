package romina.valiunas.data1.repositories

import romina.valiunas.data1.service.WeatherService
import romina.valiunas.domain1.entities.WeatherForecast
import romina.valiunas.domain1.repositories.WeatherRepository
import romina.valiunas.domain1.utils.Result

class WeatherRepositoryImpl(
    private val weatherService: WeatherService
) : WeatherRepository {

    override fun getWeatherByUrl(
        getFromRemote: Boolean
    ): Result<WeatherForecast>? =
        if (getFromRemote) {
            val weatherResult = weatherService.getWeatherReport()
            weatherResult
        } else null
}