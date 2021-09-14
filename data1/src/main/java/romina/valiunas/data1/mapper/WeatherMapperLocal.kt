package romina.valiunas.data1.mapper

import romina.valiunas.data1.database.entity.WeatherRoom
import romina.valiunas.domain1.entities.Weather
import romina.valiunas.domain1.entities.WeatherForecast

class WeatherMapperLocal : BaseMapperRepository<List<WeatherRoom>, WeatherForecast> {

    override fun transform(type: List<WeatherRoom>): WeatherForecast {
        return WeatherForecast(
            type.map { daily ->
                Weather(
                    date = daily.date,
                    temperature = daily.temperature,
                    description = daily.description,
                    temperatureMax = daily.tempMax,
                    temperatureMin = daily.tempMin,
                    image = daily.image,
                    thermalSensation = daily.thermalSensation,
                    humidity = daily.humidity,
                    windSpeed = daily.windSpeed
                )
            }
        )
    }

    override fun transformToRepository(type: WeatherForecast): List<WeatherRoom> {
        return type.daily.map {
            WeatherRoom(
                date = it.date,
                temperature = it.temperature,
                description = it.description,
                tempMax = it.temperatureMax,
                tempMin = it.temperatureMin,
                image = it.image,
                thermalSensation = it.thermalSensation,
                humidity = it.humidity,
                windSpeed = it.windSpeed
            )
        }
    }
}