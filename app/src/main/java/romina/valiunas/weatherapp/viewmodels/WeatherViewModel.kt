package romina.valiunas.weatherapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import romina.valiunas.domain1.entities.WeatherForecast
import romina.valiunas.domain1.usecases.GetWeatherForecastByLocationUseCase
import romina.valiunas.domain1.utils.Result
import romina.valiunas.weatherapp.utils.Data
import romina.valiunas.weatherapp.utils.Event
import romina.valiunas.weatherapp.utils.Status

class WeatherViewModel(val getWeatherForecastByLocation: GetWeatherForecastByLocationUseCase) :
    ViewModel() {

    private var mutableMainState: MutableLiveData<Event<Data<WeatherForecast>>> = MutableLiveData()
    val mainState: LiveData<Event<Data<WeatherForecast>>>
        get() {
            return mutableMainState
        }

    fun getWeatherForecast() = viewModelScope.launch {
        mutableMainState.value = Event(Data(responseType = Status.LOADING))
        when (val result = withContext(Dispatchers.IO) {
            getWeatherForecastByLocation(true)
        }) {
            is Result.Failure -> {
                mutableMainState.value =
                    Event(Data(responseType = Status.ERROR, error = result.exception))
            }
            is Result.Success -> {
                mutableMainState.value =
                    Event(Data(responseType = Status.SUCCESSFUL, data = result.data))
            }
        }
    }

    fun getWeatherForecastLocal() = viewModelScope.launch {
        when (val result = withContext(Dispatchers.IO) {
            getWeatherForecastByLocation(getFromRemote = false)
        }) {
            is Result.Failure -> {
                mutableMainState.value =
                    Event(Data(responseType = Status.ERROR, error = result.exception))
                getWeatherForecast()
            }
            is Result.Success -> {
                mutableMainState.value =
                    Event(Data(responseType = Status.SUCCESSFUL, data = result.data))
                getWeatherForecast()
            }
        }
    }
}