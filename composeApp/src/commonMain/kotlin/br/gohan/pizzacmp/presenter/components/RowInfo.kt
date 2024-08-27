package br.gohan.pizzacmp.presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.gohan.pizzacmp.Dimens

@Composable
fun RowInfo(title: String, info: String) {
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.tertiaryContainer)
            .padding(horizontal = Dimens.paddingFromBorder)
            .height(64.dp)
            .fillMaxWidth()
            .wrapContentHeight()) {
        Text(text = title, fontSize = Dimens.fontLarge)
        Spacer(modifier = Modifier.weight(1f))
        Text(text = info, fontSize = Dimens.fontLarge, fontWeight = FontWeight.Bold)
    }
}