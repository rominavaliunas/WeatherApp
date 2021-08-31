package romina.valiunas.data1

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherRequestGenerator(context: Context) {

    companion object {
        private const val API_KEY_ARG_VALUE = "8ebd379149d419fdfdfa2690f2e930f8"
        private const val API_KEY_ARG = "apikey"
        private const val WEATHER_BASE_URL = "https://api.openweathermap.org/data/2.5/"
        private const val MAX_TRYOUTS = 3
        private const val INIT_TRYOUT = 1
    }

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(
            HttpLoggingInterceptor().apply {
                this.level = HttpLoggingInterceptor.Level.BODY
            }
        )
        .addInterceptor(ChuckerInterceptor(context))
        .addInterceptor { chain ->
            val defaultRequest = chain.request()

            val defaultHttpUrl = defaultRequest.url

            val httpUrl = defaultHttpUrl.newBuilder()
                .addQueryParameter(API_KEY_ARG, API_KEY_ARG_VALUE)
                .build()

            val requestBuilder = defaultRequest.newBuilder()
                .url(httpUrl)

            chain.proceed(requestBuilder.build())
        }
        .addInterceptor { chain ->
            val request = chain.request()
            var response = chain.proceed(request)
            var tryOuts = INIT_TRYOUT

            while (!response.isSuccessful && tryOuts < MAX_TRYOUTS) {
                Log.d(
                    this@WeatherRequestGenerator.javaClass.simpleName,
                    "intercept: timeout/connection failure" +
                            "performing automatic retry ${(tryOuts + 1)}"
                )
                tryOuts ++
                response = chain.proceed(request)
            }

            response
        }

    private val builder = Retrofit.Builder()
        .baseUrl(WEATHER_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    fun <S> createService(serviceClass: Class<S>): S {
        val retrofit = builder.client(httpClient.build()).build()
        return retrofit.create(serviceClass)
    }
}
