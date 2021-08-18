package romina.valiunas.data1.service.response

class WeatherBaseResponse<T> (

    var code: Int,
    var status: String,
    var data: T?
)