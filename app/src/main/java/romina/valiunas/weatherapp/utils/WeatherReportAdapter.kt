package romina.valiunas.weatherapp.utils

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import romina.valiunas.domain1.entities.Weather
import romina.valiunas.weatherapp.databinding.ItemBinding

class WeatherReportAdapter(private val briefWeatherReports: List<Weather>) :
    RecyclerView.Adapter<WeatherReportAdapter.ReportViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ReportViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val reportItem = briefWeatherReports[position]
        holder.binding.run {
            textDayOfTheWeek.text = LongToDateConverter().getTheDay(reportItem.date)
            textTemperature.text = reportItem.temperature.toString()
            textDescription.text = reportItem.description
            Glide.with(holder.itemView.context).load(reportItem.image).into(imageWeather)
        }
    }

    override fun getItemCount(): Int {
        return briefWeatherReports.size
    }

    inner class ReportViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)
}