package romina.valiunas.weatherapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import romina.valiunas.domain1.entities.Weather
import romina.valiunas.domain1.usecases.GetWeatherByIdUseCase
import romina.valiunas.weatherapp.utils.Data
import romina.valiunas.weatherapp.utils.Event
import romina.valiunas.weatherapp.utils.Status
import romina.valiunas.domain1.utils.Result

class WeatherViewModel(val getWeatherById: GetWeatherByIdUseCase): ViewModel() {

    private var mutableMainState: MutableLiveData<Event<Data<Weather>>> = MutableLiveData()
    val mainState: LiveData<Event<Data<Weather>>>
        get() {
            return mutableMainState
        }

    fun onSearchRemoteClicked(id:Int) = viewModelScope.launch {
        mutableMainState.value = Event(Data(responseType = Status.LOADING))
        when (val result = withContext(Dispatchers.IO) {getWeatherById(id, true)}) {
            is Result.Failure -> {
                mutableMainState.value = Event(Data(responseType = Status.ERROR, error = result.exception))
            }
            is Result.Success -> {
                mutableMainState.value = Event(Data(responseType = Status.SUCCESSFUL, data = result.data))
            }
        }
    }

    fun onSearchLocalClicked(id: Int) = viewModelScope.launch {
        mutableMainState.value = Event(Data(responseType = Status.LOADING))
        when (val result = withContext(Dispatchers.IO) {
            getWeatherById(id, false) }) {
            is Result.Failure -> {
                mutableMainState.value = Event(Data(responseType = Status.ERROR, error = result.exception))
            }
            is Result.Success -> {
                mutableMainState.value = Event(Data(responseType = Status.SUCCESSFUL, data = result.data))
            }
        }
    }
}