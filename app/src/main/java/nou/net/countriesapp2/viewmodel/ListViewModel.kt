package nou.net.countriesapp2.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import nou.net.countriesapp2.Country
import nou.net.countriesapp2.model.CountriesService

class ListViewModel: ViewModel() {

    // Service
    private val countriesService = CountriesService()

    /**
     * - disposable basically sends this ListViewModel, it's using RxJava to get the information
     *   from the service.
     * - When this ViewModel is closed, we need to close or clear that connection, so the way we do
     *   that os with disposable variable.
     */

    //
    private val disposable = CompositeDisposable()

    // MutableLiveData
    val countries = MutableLiveData<List<Country>>()
    val countryLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    // Methods
    fun refresh(){
        fetchCountries()
    }

    private fun fetchCountries() {
        loading.value = true

        disposable.add(
            countriesService.getCountries()
                .subscribeOn(Schedulers.newThread()) // Do the processing in a separated thread
                .observeOn(AndroidSchedulers.mainThread())  // Get the result on the main thread
                .subscribeWith(object: DisposableSingleObserver<List<Country>>(){ // Do the following
                    /**
                     * Notifies the SingleObserver with a single item and that the [Single] has finished sending
                     * push-based notifications.
                     *
                     *
                     * The [Single] will not call this method if it calls [.onError].
                     *
                     * @param value
                     * the item emitted by the Single
                     */
                    override fun onSuccess(value: List<Country>?) {
                        countries.value = value
                        countryLoadError.value = false
                        loading.value = false
                    }

                    /**
                     * Notifies the SingleObserver that the [Single] has experienced an error condition.
                     *
                     *
                     * If the [Single] calls this method, it will not thereafter call [.onSuccess].
                     *
                     * @param e
                     * the exception encountered by the Single
                     */
                    override fun onError(e: Throwable?) {
                        countryLoadError.value = true
                        loading.value = false
                    }

                })
        )
    }

    /**
     * If we do clos this ViewModel, we have to get rid of this connection
     */
    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}