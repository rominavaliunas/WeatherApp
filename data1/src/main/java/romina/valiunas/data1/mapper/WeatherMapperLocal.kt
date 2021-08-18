package romina.valiunas.data1.mapper

import romina.valiunas.data1.database.entity.WeatherRoom
import romina.valiunas.domain1.entities.Weather

class WeatherMapperLocal : BaseMapperRepository<WeatherRoom, Weather> {

    override fun transform(type: WeatherRoom): Weather = Weather(
        type.id,
        type.day,
        type.description
        )

    override fun transformToRepository(type: Weather): WeatherRoom = WeatherRoom(
        type.id,
        type.day,
        type.temperature
    )

}