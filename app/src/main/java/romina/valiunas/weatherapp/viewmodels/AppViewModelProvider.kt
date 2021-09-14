package romina.valiunas.weatherapp.viewmodels

import android.app.Activity
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import romina.valiunas.data1.database.WeatherDatabase
import romina.valiunas.data1.repositories.WeatherRepositoryImpl
import romina.valiunas.data1.service.WeatherService
import romina.valiunas.domain1.usecases.GetWeatherForecastByLocationUseCase

class AppViewModelProvider(activity: Activity) : ViewModelProvider(
    (activity as ViewModelStoreOwner), AppViewModelFactory(activity.applicationContext)
)