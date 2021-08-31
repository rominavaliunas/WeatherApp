package romina.valiunas.data1.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "weather")
data class WeatherRoom(
    @PrimaryKey
    var lat: String = EMPTY_STRING,
    var lon: String = EMPTY_STRING,
) {
    companion object {
        private const val EMPTY_STRING = ""
    }
}