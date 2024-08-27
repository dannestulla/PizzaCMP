package br.gohan.pizzacmp.presenter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import br.gohan.pizzacmp.PizzaRoutes

@Composable
fun NavBar(selected: PizzaRoutes, callback: (PizzaRoutes) -> Unit) {
    return Column(
        verticalArrangement = Arrangement.Bottom,
    ) {
        BottomAppBar(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceVariant),
        ) {
            NavigationBarItem(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                colors = NavigationBarItemDefaults.colors(indicatorColor = MaterialTheme.colorScheme.primary),

                selected = selected == PizzaRoutes.Products,
                onClick = {
                    callback.invoke(PizzaRoutes.Products)
                },
                label = {
                    val bold = if (selected == PizzaRoutes.Products) FontWeight.Bold else FontWeight.Normal
                    Text(
                        text = "Products", fontWeight = bold
                    )
                },
                icon = {
                    if (selected == PizzaRoutes.Products) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Products Icon",
                            tint = Color.White

                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "Products Icon",
                        )
                    }
                }
            )
            NavigationBarItem(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                colors = NavigationBarItemDefaults.colors(indicatorColor = MaterialTheme.colorScheme.primary),

                selected = selected == PizzaRoutes.Checkout,
                onClick = { callback.invoke(PizzaRoutes.Checkout) },
                label = {
                    val bold =
                        if (selected == PizzaRoutes.Checkout) FontWeight.Bold else FontWeight.Normal
                    Text(
                        text = "Checkout", fontWeight = bold
                    )
                },
                icon = {
                    if (selected == PizzaRoutes.Checkout) {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Checkout",
                            tint = Color.White
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.ShoppingCart,
                            contentDescription = "Checkout",
                        )
                    }
                }
            )
            NavigationBarItem(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant),
                colors = NavigationBarItemDefaults.colors(indicatorColor = MaterialTheme.colorScheme.primary),

                selected = selected == PizzaRoutes.Map,
                onClick = { callback.invoke(PizzaRoutes.Map) },
                label = {
                    val bold =
                        if (selected == PizzaRoutes.Map) FontWeight.Bold else FontWeight.Normal
                    Text(
                        text = "Delivery", fontWeight = bold
                    )
                },
                icon = {
                    if (selected == PizzaRoutes.Map) {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = "ShoppingCard Icon",
                            tint = Color.White
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.Place,
                            contentDescription = "ShoppingCard Icon",
                        )
                    }
                }
            )
        }
    }
}