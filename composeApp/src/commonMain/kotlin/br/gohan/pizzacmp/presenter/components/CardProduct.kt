package br.gohan.pizzacmp.presenter.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.gohan.pizzacmp.Dimens
import presentation.model.PizzaProductUi

@Composable
fun CardProduct(pizzaProductUi: PizzaProductUi, onClick: (PizzaProductUi) -> Unit) =
    with(pizzaProductUi) {
        Card(
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.outlineVariant),
            onClick = {
                onClick(
                    pizzaProductUi
                )
            }
        ) {
            Column(
                Modifier
                    .size(
                        width = 200.dp, height = 300.dp
                    )
                    .background(MaterialTheme.colorScheme.tertiaryContainer)
            ) {
                Box(
                    Modifier
                        .height(height = 180.dp)
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                        .wrapContentWidth()
                ) {
                    ImageLoader(image = image)
                }
                Text(
                    modifier =
                    Modifier.padding(8.dp), text =
                    name, fontWeight = FontWeight.Bold, fontSize = Dimens.fontSmall,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    maxLines = 3,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier.padding(horizontal = 8.dp),
                    fontStyle = FontStyle.Italic,
                    fontSize = Dimens.fontSmaller,
                    text = description,
                    lineHeight = 20.sp,
                    color = MaterialTheme.colorScheme.onTertiaryContainer
                )

                /*       ButtonPrimary(
                           modifier = Modifier
                               .align(Alignment.CenterHorizontally)
                               .width(150.dp)
                               .padding(vertical = 8.dp), label = "Add"
                       ) {
                           onClick(ProductAction.Add(pizzaProductUi))
                       }*/
            }
        }
    }


