package romina.valiunas.weatherapp.activities

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import romina.valiunas.domain1.entities.Weather
import romina.valiunas.weatherapp.R
import romina.valiunas.weatherapp.databinding.MainActivityBinding
import romina.valiunas.weatherapp.utils.Data
import romina.valiunas.weatherapp.utils.Event
import romina.valiunas.weatherapp.utils.MINUS_ONE
import romina.valiunas.weatherapp.utils.Status
import romina.valiunas.weatherapp.viewmodels.AppViewModelProvider
import romina.valiunas.weatherapp.viewmodels.WeatherViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        AppViewModelProvider(this).get(WeatherViewModel::class.java)
    }

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var report = binding.rclWeatherReport
        report.setHasFixedSize(true)

        viewModel.mainState.observe(::getLifecycle, ::updateUI)

        report.setOnClickListener { onSearchRemoteClicked() }
        report.setOnClickListener { onSearchLocalClicked() }
    }

    private fun updateUI(weatherData: Event<Data<Weather>>) {
        when (weatherData.peekContent().responseType) {
            Status.ERROR -> {
                hideProgress()
                weatherData.peekContent().error?.message?.let { showMessage(it) }
                binding.textCityName.text = getString(R.string.no_weather)
            }
            Status.LOADING -> {
                showProgress()
            }
            Status.SUCCESSFUL -> {
                hideProgress()
                weatherData.peekContent().data?.let { setWeather(it) }
            }
        }
    }

    private fun getCurrentData() {
        showProgress()

    }

    private fun showProgress() {
        binding.progressBar.visibility = View.VISIBLE
        binding.textCityName.visibility = View.INVISIBLE
        //ToDo add fragment dialog later and set visibility attributes here
    }

    private fun hideProgress() {
        binding.progressBar.visibility = View.INVISIBLE
        binding.textCityName.visibility = View.VISIBLE
        //ToDo add fragment dialog later and set visibility attributes here
    }

    private fun setWeather(weather: Weather) {
        //binding with card view set the weather.description value to the text resource
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message,Toast.LENGTH_LONG).show()
    }

    private fun onSearchRemoteClicked() {
        val id = if(binding.weatherID.text.toString().isNotEmpty()) {
            binding.weatherID.text.toString().toInt()
        } else {
            MINUS_ONE
        }
        viewModel.onSearchRemoteClicked(id)
    }

    private fun onSearchLocalClicked() {
        val id = if (binding.weatherID.text.toString().isNotEmpty()) {
            binding.weatherID.text.toString().toInt()
        } else {
            MINUS_ONE
        }
        viewModel.onSearchLocalClicked(id)
    }
}