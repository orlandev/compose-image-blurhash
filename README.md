# compose-image-blurhash [![](https://jitpack.io/v/orlando-dev-code/compose-image-blurhash.svg)](https://jitpack.io/#orlando-dev-code/compose-image-blurhash)

Compose ImageBlurhash is a Jetpack Compose component with the necessary implementation to display a blurred image while the real image is loaded from the internet.
Use blurhash and coil to ensure good performance.

## Coil
The Coil library is used to load the images asynchronously

You can find more information here [github](https://github.com/coil-kt/coil)!

## More from Blurhash
BlurHash is a compact representation of a placeholder for an image.

<img src="media/blurhash_cover.jpg" width="600">

You can also see nice examples and try it out yourself at 
[blurha.sh](http://blurha.sh/)!

All blurhash code is here 
[github](https://github.com/woltapp/blurhash)!

## Users
Who uses ComposeImageBlurhash? :

Inmersoft - At Immersoft we use ComposeImageBlurhash for a better user experience when loading and displaying images to the end user.

#How to use

- In the AndroidManifest add the permissions to use the Internet

        <uses-permission android:name="android.permission.INTERNET" />
        
- In the build.gradle add the necessary dependencies

        implementation 'com.github.orlando-dev-code:compose-image-blurhash:1.0.0-alpha02'

- Add component as required

        class MainActivity : ComponentActivity() {
        @ExperimentalCoilApi
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContent {
                TestImageBlurLibraryTheme {
                    // A surface container using the 'background' color from the theme
                      Surface(color = MaterialTheme.colors.background) {
                         TestImageBlurhash(resources)
                      }
                  }
              }
          }
       }

        @ExperimentalCoilApi
        @Composable
        fun TestImageBlurhash(resources: Resources) {
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
                      contentDescription = "Image content description"
                  )
              }
        }
