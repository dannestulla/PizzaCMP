package br.gohan.pizzacmp.examples.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.gohan.pizzacmp.Dimens
import coil3.compose.AsyncImage
import presentation.model.ExampleUi

@Composable
fun ProductComponent(
    product: ExampleUi,
    isFavorited: Boolean = false,
    onClick: (ProductAction) -> Unit
) {
    var favorited by remember {
        mutableStateOf(isFavorited)
    }

    Box(
        modifier = Modifier
            .wrapContentSize()
    ) {
        Card(
            onClick = { onClick(ProductAction.Navigate(product)) },
            modifier = Modifier
                .fillMaxWidth()
                .size(300.dp)
                .padding(3.dp),
            shape = RoundedCornerShape(Dimens.cornerSmall)
        ) {

            Column(modifier = Modifier.padding(16.dp)) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .align(Alignment.CenterHorizontally),
                    model = product.images.first(),
                    contentDescription = product.title,
                )
                Text(
                    text = product.title,
                    style = TextStyle(
                        fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    ),
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = Dimens.fontSmall,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.Start)
                )
                Text(
                    text = product.category,
                    style = TextStyle(
                        fontFamily = MaterialTheme.typography.bodySmall.fontFamily,
                    ),
                    color = Color.Gray,
                    modifier = Modifier.padding(top = 6.dp)
                )
                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                ) {
                    Text(
                        text = product.newPrice,
                        style = TextStyle(
                            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                        ),
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        textDecoration = TextDecoration.LineThrough,
                        text = product.oldPrice,
                        style = TextStyle(
                            fontFamily = MaterialTheme.typography.bodyMedium.fontFamily,
                        ),
                        color = Color.Gray,
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
        ) {
            Card(
                Modifier
                    .padding(end = 10.dp, top = 10.dp)
                    .size(25.dp)
                .clickable {
                    favorited = !favorited
                    val message =
                        if (favorited) "Product added to favorites" else "Product removed from favorites"
                    val result = if (favorited) ProductAction.Favorite(
                        product,
                        message
                    ) else ProductAction.RemoveFavorite(
                        product, message
                    )
                    onClick(result)
                },
                shape = RoundedCornerShape(100)
            ) {
                Icon(
                    tint = MaterialTheme.colorScheme.secondaryContainer,
                    modifier = Modifier.fillMaxSize().sizeIn(35.dp),
                    imageVector = if (favorited) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = "Favorite",
                )
            }
        }
    }
}


sealed class ProductAction {
    data class Favorite(val product: ExampleUi, val message: String) : ProductAction()
    data class RemoveFavorite(val product: ExampleUi, val message: String) : ProductAction()
    data class Navigate(val product: ExampleUi) : ProductAction()
    data class AddToCart(val product: ExampleUi) : ProductAction()
}

