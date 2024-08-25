package br.gohan.pizzacmp

import androidx.compose.runtime.Composable

@Composable
fun PizzaApp(dataStoreManager: DataStoreManager) {
   /* PizzaTheme {
        val navController = rememberNavController()
        val snackBar = SnackbarHostState()
        val coroutine = rememberCoroutineScope()

        val backStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = backStackEntry?.destination

        var showBottomSheet by remember { mutableStateOf(false) }

        if (showBottomSheet) {
            BottomSheet {
                showBottomSheet = it
            }
        }

        var selectedRoute by remember {
            mutableStateOf(
                (backStackEntry?.destination?.route
                    ?: AppRoutes.CATEGORIES.name).getRoute()
            )
        }

        LaunchedEffect(currentDestination) {
            currentDestination?.let {
                selectedRoute = it.route?.getRoute() ?: AppRoutes.CATEGORIES
            }
        }

        var currentSearch by remember {
            mutableStateOf<String?>(null)
        }

        val topBarState by derivedStateOf {
            TopBarState(
                setTopTitle(selectedRoute.name),
                isSearchActive = currentSearch != null,
                noSearch = selectedRoute == AppRoutes.PRODUCT || selectedRoute == AppRoutes.CHECKOUT
            )
        }

        Scaffold(
            floatingActionButton = {
                if (selectedRoute == AppRoutes.CHECKOUT) {
                    FloatingActionButton(
                        modifier = Modifier.size(60.dp),
                        containerColor = MaterialTheme.colorScheme.secondaryContainer,
                        onClick = {
                            coroutine.launch {
                                showBottomSheet = true
                            }
                        }) {
                        Image(
                            painterResource(Res.drawable.shopping_cart_checkout),
                            "Finish checkout",
                            contentScale = ContentScale.FillBounds,
                        )
                    }
                }
            },
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
                BottomNavBar(
                    selectedRoute
                ) {
                    selectedRoute = it
                    navController.navigate(it.name)
                }
            }
        ) { paddingValues ->
            Surface(
                modifier = Modifier.padding(horizontal = Dimens.paddingLarge),
            ) {
                ShopNavigation(
                    ShopParameters(
                        navController,
                        currentSearch,
                        topBarState,
                        dataStoreManager,
                        paddingValues,
                        snackBar,
                        coroutine
                    )
                )
            }
        }
    }*/
}
