package br.gohan.pizzacmp.presenter.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.compose.AsyncImage
import org.jetbrains.compose.resources.painterResource
import pizzacmp.composeapp.generated.resources.Res
import pizzacmp.composeapp.generated.resources.pizza

@Composable
fun ImageLoader(modifier: Modifier = Modifier, image: String? = null) {
    if (image.isNullOrEmpty()) {
        Image(
            modifier = modifier,
            painter = painterResource(Res.drawable.pizza),
            contentDescription = "Deliver picture"
        )
    } else {
        AsyncImage(
            modifier = modifier,
            model = image, contentDescription = "Deliver picture"
        )
    }
}
