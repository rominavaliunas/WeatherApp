package romina.valiunas.weatherapp.utils

open class Event<out T>(private val content: T) {
    fun peekContent(): T = content
}