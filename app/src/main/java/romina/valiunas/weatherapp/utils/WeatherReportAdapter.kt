package romina.valiunas.weatherapp.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import romina.valiunas.domain1.entities.Weather
import romina.valiunas.weatherapp.databinding.ItemBinding

class WeatherReportAdapter(private val briefWeatherReports: List<Weather>) :
    RecyclerView.Adapter<WeatherReportAdapter.ReportViewHolder>() {

    inner class ReportViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(weather: Weather) {
            binding.textDayOfTheWeek.text = LongToDateConverter.getTheDay(weather.date)
            binding.textTemperature.text = weather.temperature.toString()
            binding.textDescription.text = weather.description
            Glide.with(itemView.context).load(weather.image).into(binding.imageWeather)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReportViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val reportItem = briefWeatherReports[position]
        holder.bind(reportItem)
    }

    override fun getItemCount(): Int {
        return briefWeatherReports.size
    }
}