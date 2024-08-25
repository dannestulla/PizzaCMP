package br.gohan.pizzacmp.examples.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import br.gohan.pizzacmp.Dimens
import coil3.compose.AsyncImage
import data.model.Categories

@Composable
fun CategoryComponent(categories: Categories, onClick: (String) -> Unit) {
    Box(
        modifier = Modifier
            .wrapContentHeight()
            .width(200.dp)
    ) {
        Card(
            onClick = {
                onClick.invoke(categories.name)
            },
            modifier = Modifier
                .wrapContentHeight()
                .padding(3.dp),
            shape = RoundedCornerShape(Dimens.cornerSmall)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                AsyncImage(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp)
                        .align(Alignment.CenterHorizontally),
                    model = categories.image,
                    contentDescription = categories.name
                )
                Text(
                    style = TextStyle(
                        fontFamily = MaterialTheme.typography.titleMedium.fontFamily
                    ),
                    fontSize = Dimens.fontSmall,
                    text = categories.name,
                    modifier = Modifier
                        .padding(top = Dimens.paddingSmall)
                        .align(Alignment.Start),
                )
            }
        }
    }
}