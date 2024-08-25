package br.gohan.pizzacmp.presenter.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.gohan.pizzacmp.Dimens
import br.gohan.pizzacmp.R
import br.gohan.pizzacmp.presenter.models.Selection
import br.gohan.pizzacmp.ui.theme.PizzaTheme
import coil3.compose.AsyncImage

@Composable
fun CardCheckout(selection: Selection) {
        Row(
            Modifier
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .fillMaxWidth()
                .height(120.dp)
                .padding(horizontal = Dimens.paddingFromBorder)
        ) {
            Card(
                modifier = Modifier.padding(vertical = 6.dp),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (selection.image.isNullOrEmpty()) {
                    Image(
                        modifier = Modifier.size(120.dp),
                        painter = painterResource(R.drawable.pizza),
                        contentDescription = "Deliver picture"
                    )
                } else {
                    AsyncImage(
                        modifier = Modifier.size(120.dp),
                        model = selection.image, contentDescription = "Deliver picture"
                    )
                }
            }
            Column(Modifier.padding(start = 16.dp, bottom = 6.dp, top = 6.dp)) {
                Text(selection.title, fontSize = Dimens.fontHuge)
                Text(selection.toppings, fontSize = Dimens.fontSmall)
                Spacer(modifier = Modifier.weight(1f))
                Row {
                    Text(selection.price, fontSize = Dimens.fontLarge, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.weight(1f))
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = "ShoppingCard Icon",
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        }
    }


@Preview(showSystemUi = true)
@Composable
private fun PreviewCardCheckout() {
    PizzaTheme {
        CardCheckout(
            Selection(
                "Pizza",
                null,
                "Cheese, Onion, tomato",
                "$ 10,00"
            )
        )
    }
}