package nou.net.countriesapp2.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import nou.net.countriesapp2.R

fun getProgressDrawable(context: Context): CircularProgressDrawable{
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

/**
 * Load the Image with a spinner into our layout
 */
fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable){
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)
    Glide.with(this.context)    // Create Glide
        .setDefaultRequestOptions(options)  // Use this options
        .load(uri) // load the uri
        .into(this)
}