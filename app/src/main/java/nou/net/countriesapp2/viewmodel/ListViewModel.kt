package nou.net.countriesapp2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import nou.net.countriesapp2.Country

class ListViewModel: ViewModel() {

    // MutableLiveData
    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    // Methods
    fun refresh(){
        fetchCountries()
    }

    private fun fetchCountries() {

    }
}