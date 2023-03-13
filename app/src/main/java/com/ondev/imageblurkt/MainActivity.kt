package com.ondev.imageblurkt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.ondev.imageblurkt.ui.theme.ImageBlurKtTheme
import com.ondev.imageblurkt_lib.AsyncBlurImage
import com.ondev.imageblurkt_lib.BlurImageOnly
import com.ondev.imageblurkt_lib.ImageBlurHashModel
import kotlin.random.Random

@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageBlurKtTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val sizeList = Random.nextInt(4, 10)
                    TestImageBlur(sizeList)
                }
            }
        }
    }
}

val imagesData = listOf(
    ImageBlurHashModel(
        data = "https://blurha.sh/12c2aca29ea896a628be.jpg",
        blurHash = "LEHV6nWB2yk8pyo0adR*.7kCMdnj"
    ), ImageBlurHashModel(
        data = R.drawable.img,
        blurHash = "LGF5]+Yk^6#M@-5c,1J5@[or[Q6."
    ), ImageBlurHashModel(
        data = "https://blurha.sh/a355ab362a07a267e078.jpg",
        blurHash = "L6Pj0^i_.AyE_3t7t7R**0o#DgR4"
    ), ImageBlurHashModel(
        data = "https://blurha.sh/ea9e499f8080ce9956a8.jpg",
        blurHash = "LKO2?U%2Tw=w]~RBVZRi};RPxuwH"
    )
)

@ExperimentalCoilApi
@Composable
fun TestImageBlur(sizeList: Int) {

    val cardHeight = 260.dp

    LazyVerticalGrid(
        columns = GridCells.Adaptive(200.dp)
    ) {


        items(sizeList) {
            val itemIndex = Random.nextInt(0, 4)
            Card(
                modifier = Modifier
                    .height(cardHeight)
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = MaterialTheme.shapes.small
            ) {
                AsyncBlurImage(
                    modifier = Modifier.fillMaxSize(),
                    model = imagesData[itemIndex],
                    notImageFoundRes = R.drawable.ic_no_image,
                    contentDescription = "Image Blurhash Used"
                )
            }
        }

        item {
            Card(
                modifier = Modifier
                    .height(cardHeight)
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = MaterialTheme.shapes.small
            ) {
                //This is only for show a blur section.
                BlurImageOnly(
                    modifier = Modifier.fillMaxSize(),
                    data = imagesData[0],
                    contentDescription = "Image Blurhash Used"
                )
            }
        }
    }
}