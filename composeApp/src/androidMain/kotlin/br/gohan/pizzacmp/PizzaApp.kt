package br.gohan.pizzacmp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import br.gohan.pizzacmp.presenter.actions.TopBarAction
import br.gohan.pizzacmp.presenter.components.NavBar
import br.gohan.pizzacmp.presenter.components.TopBar
import br.gohan.pizzacmp.ui.theme.PizzaTheme
import presentation.model.TopBarState

@Composable
fun PizzaApp(dataStore: DataStoreManager) {
    PizzaTheme {
        val navController = rememberNavController()
        val snackBar = SnackbarHostState()
        val coroutine = rememberCoroutineScope()

        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = backStackEntry?.destination

        var selectedRoute by remember {
            mutableStateOf(
                (backStackEntry?.destination?.route
                    ?: PizzaRoutes.Products.name).getRoute()
            )
        }

        LaunchedEffect(currentDestination) {
            currentDestination?.let {
                selectedRoute = it.route?.getRoute() ?: PizzaRoutes.Products
            }
        }

        var currentSearch by remember {
            mutableStateOf<String?>(null)
        }

        val topBarState by derivedStateOf {
            TopBarState(
                selectedRoute.name,
                isSearchActive = currentSearch != null,
                noSearch = selectedRoute == PizzaRoutes.Checkout || selectedRoute == PizzaRoutes.Deliver
            )
        }

        Scaffold(
            snackbarHost = { SnackbarHost(snackBar) },
            topBar = {
                TopBar(
                    topBarState
                ) { action ->
                    when (action) {
                        is TopBarAction.Back -> {
                            navController.popBackStack()
                        }

                        is TopBarAction.Search -> {
                            currentSearch = action.search
                        }

                        is TopBarAction.CancelSearch -> {
                            currentSearch = null
                        }
                    }
                }
            },
            bottomBar = {
                NavBar(
                    selectedRoute
                ) {
                    selectedRoute = it
                    navController.navigate(it.name)
                }
            }
        ) { paddingValues ->
            val paddingPlusBorder = PaddingValues(
                start = Dimens.paddingFromBorder,
                end = Dimens.paddingFromBorder,
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding()
            )
            Surface(
                modifier = Modifier.background(MaterialTheme.colorScheme.surfaceContainer),
            ) {
                PizzaNavigation(
                    PizzaParameters(
                        navController,
                        currentSearch,
                        topBarState,
                        dataStore,
                        paddingPlusBorder,
                        snackBar,
                        coroutine
                    )
                )
            }
        }
    }
}

