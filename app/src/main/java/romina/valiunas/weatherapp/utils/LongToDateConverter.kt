package romina.valiunas.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.*

class LongToDateConverter {

    fun getTheDay(longDate: Long): String {
        //EEEE dd MMMM yyyy
        return SimpleDateFormat(
            "EEEE",
            Locale.getDefault()
        ).format(Date(longDate * 1000))
    }
}