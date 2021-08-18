package romina.valiunas.data1.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import romina.valiunas.data1.DEFAULT_ID
import romina.valiunas.data1.EMPTY_STRING

@Entity(tableName = "weather")
data class WeatherRoom(
    @PrimaryKey
    var id: Int = DEFAULT_ID,
    var day: String = EMPTY_STRING,
    var description: String = EMPTY_STRING
)