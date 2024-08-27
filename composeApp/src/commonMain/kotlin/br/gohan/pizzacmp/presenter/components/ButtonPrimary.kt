package br.gohan.pizzacmp.presenter.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.gohan.pizzacmp.Dimens

@Composable
fun ButtonPrimary(modifier: Modifier = Modifier, label:String, onClick: () -> Unit) {
    Button(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(horizontal = Dimens.paddingFromBorder),
        onClick = onClick
    ) {
        Text(text = label, fontSize = 16.sp)
    }
}

