package com.ondev.imageblurkt_lib


import android.content.res.Resources
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toDrawable
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.ondev.blurhashkt.BlurhashDecoder

@Deprecated("This functions is deprecated... instead use AsyncImageBlur ")
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

@Deprecated("This functions is deprecated... instead use AsyncImageBlur ")
@ExperimentalCoilApi
@Composable
fun ImageBlur(
    modifier: Modifier = Modifier,
    blurhash: String,
    imageUrl: String,
    crossFadeAnimDuration: Int = 700,
    resources: Resources,
    @DrawableRes
    notImageFoundRes: Int,
    contentDescription: String? = null
) {
    val bitmap = BlurhashDecoder.decode(blurhash, 4, 3)
    Image(
        modifier = modifier,
        contentScale = ContentScale.Crop,
        painter = rememberImagePainter(imageUrl, builder = {
            when {
                bitmap != null -> placeholder(bitmap.toDrawable(resources))
                else -> placeholder(notImageFoundRes)
            }
            error(notImageFoundRes)
            crossfade(crossFadeAnimDuration)
        }),
        contentDescription = contentDescription
    )
}

@ExperimentalCoilApi
@Composable
fun AsyncImageBlur(
    modifier: Modifier = Modifier,
    blurHash: String,
    imageUrl: String,
    crossFadeAnimDuration: Int = 700,
    resources: Resources,
    @DrawableRes
    notImageFoundRes: Int,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    val bitmap = BlurhashDecoder.decode(blurHash, 4, 3)
    AsyncImage(
        modifier = modifier,
        contentScale = contentScale,
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUrl)
            .placeholder(
                bitmap?.toDrawable(resources)
            )
            .fallback(bitmap?.toDrawable(resources))
            .error(notImageFoundRes)
            .crossfade(crossFadeAnimDuration)
            .build(),
        contentDescription = contentDescription
    )
}

@ExperimentalCoilApi
@Composable
fun ImageOnlyBlur(
    modifier: Modifier = Modifier,
    blurhash: String,
    contentDescription: String? = null
) {
    val bitmap = BlurhashDecoder.decode(blurhash, 4, 3)
    if (bitmap != null)
        Image(
            bitmap = bitmap.asImageBitmap(),
            modifier = modifier,
            contentScale = ContentScale.Crop,
            contentDescription = contentDescription
        )
}