package romina.valiunas.data1.database.response

import com.google.gson.annotations.SerializedName
import romina.valiunas.data1.service.response.OneCallResponse

class DataBaseResponse<T> (
    @SerializedName("results") val weather: List<OneCallResponse>,
    val offset: Int,
    val limit: Int,
    val total: Int
)