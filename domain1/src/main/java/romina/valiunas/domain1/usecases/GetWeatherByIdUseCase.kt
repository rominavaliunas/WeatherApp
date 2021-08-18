package romina.valiunas.domain1.usecases

import romina.valiunas.domain1.repositories.WeatherRepository

class GetWeatherByIdUseCase {
    lateinit var weatherReportRepository: WeatherRepository
    operator fun invoke(id: Int, getFromRemote: Boolean) =
        weatherReportRepository.getWeatherById(id, getFromRemote)
}