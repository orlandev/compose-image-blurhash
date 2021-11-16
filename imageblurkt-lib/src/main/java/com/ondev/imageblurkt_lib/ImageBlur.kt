package com.ondev.imageblurkt_lib


import android.content.res.Resources
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.core.graphics.drawable.toDrawable
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.ondev.blurhashkt.BlurhashDecoder

@ExperimentalCoilApi
@Composable
fun ImageBlur(
    modifier: Modifier = Modifier,
    blurhash: String,
    imageUrl: String,
    crossFadeAnimDuration: Int = 700,
    resources: Resources,
    contentDescription: String? = null
) {
    val bitmap = BlurhashDecoder.decode(blurhash, 4, 3)
    val errorNoImage = R.drawable.no_image
    Image(
        modifier = modifier,
        contentScale = ContentScale.Crop,
        painter = rememberImagePainter(imageUrl, builder = {
            when {
                bitmap != null -> placeholder(bitmap.toDrawable(resources))
                else -> placeholder(errorNoImage)
            }
            error(errorNoImage)
            crossfade(crossFadeAnimDuration)
        }),
        contentDescription = contentDescription
    )
}