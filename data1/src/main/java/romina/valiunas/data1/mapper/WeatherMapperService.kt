package romina.valiunas.data1.mapper

import romina.valiunas.data1.service.response.OneCallResponse
import romina.valiunas.domain1.entities.Weather
import romina.valiunas.domain1.entities.WeatherForecast

open class WeatherMapperService : BaseMapperRepository<OneCallResponse, WeatherForecast> {

    companion object {
        private const val IMAGE_URL = "https://openweathermap.org/img/wn/%s@2x.png"
    }

    override fun transform(type: OneCallResponse): WeatherForecast {
        return WeatherForecast(
            type.daily.map { daily ->

                val dailyWeather = daily.weather.first()
                    Weather(
                        date = daily.dt,
                        temperature = daily.temp.day,
                        description = dailyWeather.description,
                        temperatureMax = daily.temp.max,
                        temperatureMin = daily.temp.min,
                        image = String.format(IMAGE_URL, dailyWeather.icon),
                        thermalSensation = daily.feels_like.day,
                        humidity = daily.humidity,
                        windSpeed = daily.wind_speed
                    )
            }
        )
    }
}