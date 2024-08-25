package br.gohan.pizzacmp.examples.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.gohan.pizzacmp.Dimens
import coil3.compose.AsyncImage
import coil3.compose.rememberAsyncImagePainter
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation.model.ExampleUi

@Composable
fun FavoriteComponent(product: ExampleUi) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .width(200.dp)
    ) {
        Card(
            modifier = Modifier
                .wrapContentHeight()
                .width(200.dp)
        ) {
            Column(modifier = Modifier.padding(Dimens.paddingLarge)) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .align(Alignment.CenterHorizontally),
                    placeholder = rememberAsyncImagePainter(
                        Brush.linearGradient(
                            listOf(
                                Color.White,
                                Color(0xFFDDDDDD)
                            )
                        )
                    ),
                    model = product.images.first(),
                    contentDescription = product.title
                )
                Text(
                    text = product.title,
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .align(Alignment.Start),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = product.category,
                    fontSize = 10.sp,
                    color = Color.Gray,
                    modifier = Modifier.padding(top = Dimens.paddingSmall)
                )
                Row(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = product.newPrice,
                        fontSize = Dimens.fontNormal,
                        fontWeight = FontWeight.Bold,
                    )
                    Text(
                        textDecoration = TextDecoration.LineThrough,
                        text = product.oldPrice,
                        fontSize = Dimens.fontNormal,
                        color = Color.Gray,
                    )
                }
                Text(
                    modifier = Modifier.padding(top = 6.dp),
                    text = product.discount,
                    fontSize = Dimens.fontNormal,
                )
            }
        }
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(40.dp)
                .align(Alignment.TopEnd) // Alinha o Box no canto superior direito
        ) {
            Icon(
                imageVector = Icons.Default.Favorite,
                contentDescription = "Favorite",
            )
        }
    }
}

