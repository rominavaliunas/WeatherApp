package romina.valiunas.domain1.repositories

import romina.valiunas.domain1.entities.WeatherForecast
import romina.valiunas.domain1.utils.Result

interface WeatherRepository {
    fun getWeatherByUrl(getFromRemote: Boolean): Result<WeatherForecast>?
}