package com.ondev.imageblurkt_lib


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.core.graphics.drawable.toDrawable
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ondev.blurhashkt.BlurhashDecoder

data class IBlurModel(
    val blurHash: String,
    val imageUrl: String,
)


@ExperimentalCoilApi
@Composable
fun AsyncBlurImage(
    modifier: Modifier = Modifier,
    data: IBlurModel,
    crossFadeAnimDuration: Int = 700,
    @DrawableRes
    notImageFoundRes: Int,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    val bitmap = remember {
        derivedStateOf {
            BlurhashDecoder.decode(data.blurHash, 4, 3)
        }
    }

    val context = LocalContext.current
    val resources = LocalContext.current.resources

    val model = remember {
        derivedStateOf {
            ImageRequest.Builder(context)
                .data(data.imageUrl)
                .placeholder(
                    bitmap.value?.toDrawable(resources)
                )
                .fallback(bitmap.value?.toDrawable(resources))
                .error(notImageFoundRes)
                .crossfade(crossFadeAnimDuration)
                .build()
        }
    }

    AsyncImage(
        modifier = modifier,
        contentScale = contentScale,
        model = model.value,
        contentDescription = contentDescription
    )
}


/***
 *  Show only a blurhash, not load any image from URL
 */
@ExperimentalCoilApi
@Composable
fun BlurImageOnly(
    modifier: Modifier = Modifier,
    data: IBlurModel,
    contentDescription: String? = null
) {
    val bitmap = BlurhashDecoder.decode(data.blurHash, 4, 3)
    if (bitmap != null)
        Image(
            bitmap = bitmap.asImageBitmap(),
            modifier = modifier,
            contentScale = ContentScale.Crop,
            contentDescription = contentDescription
        )
}

