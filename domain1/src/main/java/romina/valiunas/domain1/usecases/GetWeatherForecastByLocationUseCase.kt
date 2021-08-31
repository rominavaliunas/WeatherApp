package romina.valiunas.domain1.usecases

import romina.valiunas.domain1.repositories.WeatherRepository

class GetWeatherForecastByLocationUseCase {
    lateinit var weatherReportRepository: WeatherRepository
    operator fun invoke(getFromRemote: Boolean) =
        weatherReportRepository.getWeatherByUrl(getFromRemote)
}