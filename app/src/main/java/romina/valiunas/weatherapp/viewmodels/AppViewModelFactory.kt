package romina.valiunas.weatherapp.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.NewInstanceFactory
import romina.valiunas.data1.database.WeatherDatabase
import romina.valiunas.data1.repositories.WeatherRepositoryImpl
import romina.valiunas.data1.service.WeatherService
import romina.valiunas.domain1.usecases.GetWeatherByIdUseCase

class AppViewModelFactory(private val context: Context) : NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return if (modelClass == WeatherViewModel::class.java) {
            WeatherViewModel(GetWeatherByIdUseCase().apply {
                weatherReportRepository = WeatherRepositoryImpl(
                    WeatherService(context),
                    WeatherDatabase.getInstance(context)
                )
            }) as T
        } else {
            super.create(modelClass)
        }
    }
}