package romina.valiunas.weatherapp.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import romina.valiunas.domain1.entities.Weather
import romina.valiunas.domain1.entities.WeatherForecast
import romina.valiunas.weatherapp.R
import romina.valiunas.weatherapp.databinding.MainActivityBinding
import romina.valiunas.weatherapp.utils.*
import romina.valiunas.weatherapp.viewmodels.AppViewModelProvider
import romina.valiunas.weatherapp.viewmodels.WeatherViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by lazy {
        AppViewModelProvider(this).get(WeatherViewModel::class.java)
    }

    private lateinit var binding: MainActivityBinding
    private lateinit var adapter: WeatherReportAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //ToDo reports =

        viewModel.mainState.observe(::getLifecycle, ::updateUI)
        viewModel.getWeatherForecast()
    }

    private fun initRecycler(weatherList: List<Weather>) {
        binding.rclWeatherReport.setHasFixedSize(true)
        binding.rclWeatherReport.layoutManager = LinearLayoutManager(this)
        adapter = WeatherReportAdapter(weatherList)
        binding.rclWeatherReport.adapter = adapter
    }

    private fun updateUI(weatherData: Event<Data<WeatherForecast>>) {
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
                weatherData.peekContent().data?.let {
                    initRecycler(it.daily)
                }
            }
        }
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

    private fun showMessage(message: String) {
        Toast.makeText(this, message,Toast.LENGTH_LONG).show()
    }
}