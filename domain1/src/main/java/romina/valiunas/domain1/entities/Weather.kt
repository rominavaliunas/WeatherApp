package romina.valiunas.domain1.entities

const val NOT_FOUND = "NOT FOUND"
const val DEFAULT_ID = 0

class Weather(
    val id: Int = DEFAULT_ID,
    val day: String = NOT_FOUND,
    val temperature: String = NOT_FOUND
)