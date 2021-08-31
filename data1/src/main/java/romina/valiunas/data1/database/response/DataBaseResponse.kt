package romina.valiunas.data1.database.response

import com.google.gson.annotations.SerializedName
import romina.valiunas.domain1.entities.WeatherForecast

class DataBaseResponse<T>(
    @SerializedName("results") val weatherReports: List<WeatherForecast>,
    val offset: Int,
    val limit: Int,
    val total: Int
)