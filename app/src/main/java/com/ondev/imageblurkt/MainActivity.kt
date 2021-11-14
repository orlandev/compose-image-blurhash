package com.ondev.imageblurkt

import android.content.res.Resources
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import com.ondev.imageblurkt.ui.theme.ImageBlurKtTheme
import com.ondev.imageblurkt_lib.ImageBlur

@ExperimentalCoilApi
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImageBlurKtTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    TestImageBlur(resources = resources)
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun TestImageBlur(resources: Resources) {

    val imageUrl = "https://blurha.sh/assets/images/img1.jpg"
    val blurhash = "LEHV6nWB2yk8pyo0adR*.7kCMdnj"
    Card(
        modifier = Modifier
            .height(300.dp)
            .fillMaxWidth()
            .padding(8.dp),
        shape = MaterialTheme.shapes.small
    ) {
        ImageBlur(
            modifier = Modifier.fillMaxSize(),
            blurhash = blurhash,
            imageUrl = imageUrl,
            resources = resources,
            contentDescription = "Image Blurhash Used"
        )
    }

}