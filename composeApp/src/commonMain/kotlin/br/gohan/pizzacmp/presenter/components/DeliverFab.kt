package br.gohan.pizzacmp.presenter.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import pizzacmp.composeapp.generated.resources.Res
import pizzacmp.composeapp.generated.resources.resize_down
import pizzacmp.composeapp.generated.resources.resize_up

@Composable
fun DeliverFab(modifier: Modifier = Modifier, expanded: Boolean, onClick: () -> Unit) {
    FloatingActionButton(modifier = modifier, onClick = onClick) {
        if (expanded) {
            Icon(
                painter = painterResource(Res.drawable.resize_down),
                contentDescription = "Collapse"
            )
        } else {
            Icon(painter = painterResource(Res.drawable.resize_up), contentDescription = "Expand")
        }
    }
}

