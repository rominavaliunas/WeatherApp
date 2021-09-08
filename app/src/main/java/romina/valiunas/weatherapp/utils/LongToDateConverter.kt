package romina.valiunas.weatherapp.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class LongToDateConverter {

    companion object {
        fun getTheDay(longDate: Long): String {
            //EEEE dd MMMM yyyy
            return SimpleDateFormat("EEEE",
                Locale.getDefault()).format(Date(longDate*1000))
        }
    }
}