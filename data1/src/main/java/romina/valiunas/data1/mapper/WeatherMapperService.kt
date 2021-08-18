package romina.valiunas.data1.mapper

import romina.valiunas.data1.service.response.WeatherResponse
import romina.valiunas.domain1.entities.Weather

open class WeatherMapperService : BaseMapperRepository<WeatherResponse, Weather> {

    override fun transform(type: WeatherResponse): Weather = Weather(
        type.id,
        type.day,
        type.description
    )

    override fun transformToRepository(type: Weather): WeatherResponse = WeatherResponse(
        type.id,
        type.day,
        type.temperature
    )
}