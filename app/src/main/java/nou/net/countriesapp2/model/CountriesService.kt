package nou.net.countriesapp2.model

import io.reactivex.Single
import nou.net.countriesapp2.Country
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * This class will use the interface to call the backend endpoint and
 * get the information
 */
class CountriesService {

    private val BASE_URL = "https://raw.githubusercontent.com"
    private val api: CountriesApi

    init{

        /**
         * This creates the framework for retrofit which will help us to get the information
         * from the back end.
         * We'll get the information in Json format.
         * We're going to use GsonConverterFactory to transform the code that we received from the
         * backend into out objects.
         * The adapter is basically going to be an rxjava adapter that will transform the data that
         * we have here in the type Country into an observable variable that other elements in our code
         * can observe too similar in a way to what we've done for the ViewModel
         */
        api = Retrofit.Builder()    // Creates the framework for retrofit
            .baseUrl(BASE_URL)      // Get the information from BASE_URL
            .addConverterFactory(GsonConverterFactory.create()) // Converts Json format into Kotlin Code
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // Transform the country data class into a variable similar to countries (MutableLiveData<List<Country>>)
            .build()    // We need to build this retrofit system
            .create(CountriesApi::class.java)   // We need to give it the type of information that it can return
    }

    fun getCountries(): Single<List<Country>>{
        return api.getCountries()
    }
}