package romina.valiunas.data1.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import romina.valiunas.data1.database.entity.WeatherRoom

@Dao
interface WeatherDao {

    @Query("SELECT * FROM weather WHERE id = :id")
    fun findById(id: Int): WeatherRoom?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(weather: WeatherRoom)
}