package br.gohan.pizzacmp.presenter.components

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import pizzacmp.composeapp.generated.resources.Res
import pizzacmp.composeapp.generated.resources.resize_down
import pizzacmp.composeapp.generated.resources.resize_up

@Composable
fun MapFab(modifier: Modifier = Modifier, onClick: () -> Unit) {
    var isExpanded by remember { mutableStateOf(false) }
    FloatingActionButton(modifier = modifier, onClick = onClick) {
        if (isExpanded) {
            Icon(
                painter = painterResource(Res.drawable.resize_down),
                contentDescription = "Collapse"
            )
            isExpanded = false
        } else {
            Icon(painter = painterResource(Res.drawable.resize_up), contentDescription = "Expand")
            isExpanded = true
        }
    }
}

