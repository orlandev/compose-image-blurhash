package com.ondev.imageblurkt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.ondev.imageblurkt.ui.theme.ImageBlurKtTheme
import com.ondev.imageblurkt_lib.AsyncImageBlur
import com.ondev.imageblurkt_lib.ImageBlur
import com.ondev.imageblurkt_lib.ImageOnlyBlur
import kotlin.random.Random

@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageBlurKtTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val sizeList = Random.nextInt(10, 200)
                    TestImageBlur(sizeList)
                }
            }
        }
    }
}

data class ImageData(val imgUrl: String, val imgBlurhash: String)

val imagesData = listOf(
    ImageData(
        "https://blurha.sh/assets/images/img1.jpg", "LEHV6nWB2yk8pyo0adR*.7kCMdnj"
    ), ImageData(
        "https://blurha.sh/assets/images/img2.jpg", "LGF5]+Yk^6#M@-5c,1J5@[or[Q6."
    ), ImageData(
        "https://blurha.sh/assets/images/img3.jpg", "L6Pj0^i_.AyE_3t7t7R**0o#DgR4"
    ), ImageData(
        "https://blurha.sh/assets/images/img4.jpg", "LKO2?U%2Tw=w]~RBVZRi};RPxuwH"
    )
)

@ExperimentalCoilApi
@Composable
fun TestImageBlur(sizeList: Int) {

    val resources = LocalContext.current.resources
    val cardHeight=200.dp

    LazyVerticalGrid(
        columns = GridCells.Adaptive(100.dp)
    ){

        item {
            Card(
                modifier = Modifier
                    .height(cardHeight)
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = MaterialTheme.shapes.small
            ) {
                ImageBlur(
                    modifier = Modifier.fillMaxSize(),
                    blurhash = imagesData[0].imgBlurhash,
                    imageUrl = imagesData[0].imgUrl,
                    resources = resources,
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
                ImageBlur(
                    modifier = Modifier.fillMaxSize(),
                    blurhash = imagesData[1].imgBlurhash,
                    imageUrl = imagesData[1].imgUrl,
                    notImageFoundRes = R.drawable.ic_no_image,
                    resources = resources,
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
                AsyncImageBlur(
                    modifier = Modifier.fillMaxSize(),
                    blurHash = imagesData[2].imgBlurhash,
                    imageUrl = imagesData[2].imgUrl,
                    notImageFoundRes = R.drawable.ic_no_image,
                    resources = resources,
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
                AsyncImageBlur(
                    modifier = Modifier.fillMaxSize(),
                    blurHash = imagesData[3].imgBlurhash,
                    imageUrl = imagesData[3].imgUrl,
                    notImageFoundRes = R.drawable.ic_no_image,
                    resources = resources,
                    contentDescription = "Image Blurhash Used"
                )
            }
        }


        items(sizeList) {
            val itemIndex = Random.nextInt(0, 4)
            Card(
                modifier = Modifier
                    .height(cardHeight)
                    .fillMaxWidth()
                    .padding(8.dp),
                shape = MaterialTheme.shapes.small
            ) {
                AsyncImageBlur(
                    modifier = Modifier.fillMaxSize(),
                    blurHash = imagesData[itemIndex].imgBlurhash,
                    imageUrl = imagesData[itemIndex].imgUrl,
                    notImageFoundRes = R.drawable.ic_no_image,
                    resources = resources,
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
                ImageOnlyBlur(
                    modifier = Modifier.fillMaxSize(),
                    blurhash = imagesData[0].imgBlurhash,
                    contentDescription = "Image Blurhash Used"
                )
            }
        }
    }

}