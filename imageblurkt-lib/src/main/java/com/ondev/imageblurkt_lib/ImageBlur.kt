package com.ondev.imageblurkt_lib


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import coil.annotation.ExperimentalCoilApi
import coil.compose.AsyncImage
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.ondev.blurhashkt.BlurhashDecoder

data class BlurModel(
    val blurHash: String,
    val imageUrl: String,
)


@ExperimentalCoilApi
@Composable
fun AsyncBlurImage(
    modifier: Modifier = Modifier,
    data: BlurModel,
    crossFadeAnimDuration: Int = 700,
    notImageFoundRes: Any?,
    contentDescription: String? = null,
    contentScale: ContentScale = ContentScale.Crop
) {
    val bitmap = remember {
        derivedStateOf {
            BlurhashDecoder.decode(data.blurHash, 4, 3)
        }
    }

    val context = LocalContext.current

    val model = remember {
        derivedStateOf {
            ImageRequest.Builder(context).data(data.imageUrl).crossfade(crossFadeAnimDuration)
                .build()
        }
    }

    SubcomposeAsyncImage(modifier = modifier,
        contentScale = contentScale,
        model = model.value,
        contentDescription = contentDescription,
        loading = {
            Box(modifier = Modifier.fillMaxSize()) {
                bitmap.value?.let {
                    Image(
                        modifier = Modifier.fillMaxSize(),
                        bitmap = it.asImageBitmap(),
                        contentScale = ContentScale.Crop,
                        contentDescription = "Loading Image"
                    )
                } ?: InternalBox()
            }
        },
        error = {
            Box(modifier = Modifier.fillMaxSize()) {
                notImageFoundRes?.let {
                    AsyncImage(
                        model = it, contentDescription = null, modifier = Modifier.fillMaxSize(),
                    )
                } ?: InternalBox()
            }
        })
}

@Composable
internal fun InternalBox() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray)
    )
}

/***
 *  Show only a blurhash, not load any image from URL
 */
@ExperimentalCoilApi
@Composable
fun BlurImageOnly(
    modifier: Modifier = Modifier, data: BlurModel, contentDescription: String? = null
) {
    val bitmap = BlurhashDecoder.decode(data.blurHash, 4, 3)
    if (bitmap != null) Image(
        bitmap = bitmap.asImageBitmap(),
        modifier = modifier,
        contentScale = ContentScale.Crop,
        contentDescription = contentDescription
    )
}

