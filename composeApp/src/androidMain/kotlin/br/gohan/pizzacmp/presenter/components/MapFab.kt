package br.gohan.pizzacmp.presenter.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import br.gohan.pizzacmp.R
import br.gohan.pizzacmp.ui.theme.PizzaTheme

@Composable
fun MapFab(onClick: () -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }
    FloatingActionButton(onClick = onClick) {
        if (isExpanded) {
            Icon(painter = painterResource(R.drawable.resize_down), contentDescription = "Collapse")
            isExpanded = false
        } else {
            Icon(painter = painterResource(R.drawable.resize_up), contentDescription = "Expand")
            isExpanded = true

        }
    }
}

@Preview
@Composable
private fun PreviewMapFab() {
    PizzaTheme {
        MapFab() {

        }
    }

}