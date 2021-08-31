package romina.valiunas.domain1.entities

class Weather(
    val date: Long,
    val temperature: Double,
    val description: String,
    val temperatureMax: Double,
    val temperatureMin: Double,
    val image: String,
    val thermalSensation: Double,
    val humidity: Int,
    val windSpeed: Double
)