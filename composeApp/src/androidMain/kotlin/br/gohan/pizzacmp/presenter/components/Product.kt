package br.gohan.pizzacmp.presenter.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.gohan.pizzacmp.Dimens
import br.gohan.pizzacmp.R
import br.gohan.pizzacmp.presenter.models.Product
import br.gohan.pizzacmp.ui.theme.PizzaTheme
import coil3.compose.AsyncImage

@Composable
fun CardProduct(product: Product, onClick: (Product) -> Unit) = with(product) {
    Card {
        Column(Modifier.size(
            width = 170.dp, height = 300.dp
        )) {
            Box(
                Modifier
                    .height(height = 137.dp)
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .wrapContentWidth()
            ) {
                if (image.isNullOrEmpty()) {
                    Image(
                        painter = painterResource(R.drawable.pizza),
                        contentDescription = "Deliver picture"
                    )
                } else {
                    AsyncImage(model = image, contentDescription = "Deliver picture")
                }
            }
            Text(modifier =
                Modifier.padding(8.dp),text =
                name, fontWeight = FontWeight.Bold
                , fontSize = Dimens.fontSmall
            )
            Text(
               modifier =  Modifier.padding(horizontal = 8.dp),
                fontStyle = FontStyle.Italic,
                fontSize = Dimens.fontSmaller,
                text = description)
            ButtonPrimary(
                modifier = Modifier.width(200.dp).padding(vertical = 8.dp)

                , label = "Add") {
                onClick(product)
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewCardComponent() {
    PizzaTheme {
        CardProduct(Product("", "Pepperoni", "Pepperoni, queijo, azeitona, pimentão e molho vermelho.")) {

        }
    }
}