package romina.valiunas.data1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import romina.valiunas.data1.database.dao.WeatherDao
import romina.valiunas.data1.database.entity.WeatherRoom

@Database(entities = [WeatherRoom::class], version = 1)
abstract class WeatherDatabase : RoomDatabase() {

    companion object {

        private const val DATABASE_NAME = "weather_database"
        private lateinit var instance: WeatherDatabase

        @Synchronized
        fun getInstance(context: Context): WeatherDatabase {
            if (!this::instance.isInitialized) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    WeatherDatabase::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration()
                    .build()
            }
            return instance
        }
    }

    abstract fun weatherDao(): WeatherDao
}