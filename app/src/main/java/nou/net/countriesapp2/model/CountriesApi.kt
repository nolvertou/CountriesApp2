package nou.net.countriesapp2.model

import io.reactivex.Single
import nou.net.countriesapp2.Country
import retrofit2.http.GET

interface CountriesApi {
    /**
     * We need to specify the end point, this is a static file on GitHub to get out information
     * - Single means that it's an observable that emits one variable and then closes
     */
    @GET("DevTides/countries/master/countriesV2.json")
    fun getCountries(): Single<List<Country>>
}