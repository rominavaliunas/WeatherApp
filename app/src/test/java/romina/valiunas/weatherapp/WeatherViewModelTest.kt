package romina.valiunas.weatherapp

import androidx.lifecycle.Observer
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.LiveData
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertNotNull
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.kotlin.whenever
import romina.valiunas.domain1.entities.WeatherForecast
import romina.valiunas.domain1.usecases.GetWeatherForecastByLocationUseCase
import romina.valiunas.domain1.utils.Result
import romina.valiunas.weatherapp.utils.Status
import romina.valiunas.weatherapp.viewmodels.WeatherViewModel
import java.lang.Exception

class WeatherViewModelTest {

    class TestObserver<T> : Observer<T> {
        val observedValues = mutableListOf<T?>()
        override fun onChanged(value: T?) {
            observedValues.add(value)
        }
    }

    private fun <T> LiveData<T>.testObserver() = TestObserver<T>().also {
        observeForever(it)
    }

    @ObsoleteCoroutinesApi
    private var mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var subject: WeatherViewModel
    @Mock lateinit var weatherValidResult: Result.Success<WeatherForecast>
    @Mock lateinit var weatherInvalidResult: Result.Failure
    @Mock lateinit var weatherForecast: WeatherForecast
    @Mock lateinit var exception: Exception
    @Mock lateinit var getWeatherForecastByLocationUseCase: GetWeatherForecastByLocationUseCase

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @Before
    fun setup() {
        Dispatchers.setMain(mainThreadSurrogate)
        MockitoAnnotations.openMocks(this)
        subject = WeatherViewModel(getWeatherForecastByLocationUseCase)
    }

    @ExperimentalCoroutinesApi
    @ObsoleteCoroutinesApi
    @After
    fun after() {
        mainThreadSurrogate.close()
        Dispatchers.resetMain()
    }

    @Test
    fun getWeatherForecastSuccessful() {
        val liveDataUnderTest = subject.mainState.testObserver()
        whenever(getWeatherForecastByLocationUseCase.invoke(true)).thenReturn(weatherValidResult)
        whenever(weatherValidResult.data).thenReturn(weatherForecast)
        runBlocking {
            subject.getWeatherForecast().join()
        }
        liveDataUnderTest.observedValues.run {
            assertEquals(Status.LOADING, first()?.peekContent()?.responseType)
            assertNotNull(last()?.peekContent())
            last()?.peekContent()?.run {
                assertEquals(Status.SUCCESSFUL, responseType)
                assertEquals(weatherForecast, data)
            }
        }
    }

    @Test
    fun getWeatherForecastFails() {
        val liveDataUnderTest = subject.mainState.testObserver()
        whenever(getWeatherForecastByLocationUseCase.invoke(true)).thenReturn(weatherInvalidResult)
        whenever(weatherInvalidResult.exception).thenReturn(exception)
        runBlocking {
            subject.getWeatherForecast().join()
        }
        liveDataUnderTest.observedValues.run {
            assertEquals(Status.LOADING, first()?.peekContent()?.responseType)
            assertNotNull(last()?.peekContent())
            last()?.peekContent()?.run {
                assertEquals(Status.ERROR, responseType)
                assertEquals(exception, error)
            }
        }
    }
}