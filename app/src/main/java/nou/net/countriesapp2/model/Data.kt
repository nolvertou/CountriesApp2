package nou.net.countriesapp2

import com.google.gson.annotations.SerializedName

data class Country(
    /**
     * We need a way to map the name we have in json to countryName
     */
    @SerializedName("name")
    val countryName: String?,
    @SerializedName("capital")
    val capital: String?,
    @SerializedName("flagPNG")
    val flag: String?
)
