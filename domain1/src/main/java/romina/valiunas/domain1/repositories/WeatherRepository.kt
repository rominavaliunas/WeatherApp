package romina.valiunas.domain1.repositories

import romina.valiunas.domain1.entities.Weather
import romina.valiunas.domain1.utils.Result

interface WeatherRepository {
    fun getWeatherById(id: Int, getFromRemote: Boolean): Result<Weather>
}