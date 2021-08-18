package romina.valiunas.data1.database.response

import com.google.gson.annotations.SerializedName
import romina.valiunas.data1.service.response.WeatherResponse

class DataBaseResponse<T>(
    @SerializedName("results") val weatherReports: List<WeatherResponse>,
    val offset: Int,
    val limit: Int,
    val total: Int
)