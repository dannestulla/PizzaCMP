package br.gohan.pizzacmp.presenter.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import br.gohan.pizzacmp.presenter.components.ButtonSecondary
import br.gohan.pizzacmp.presenter.components.DeliverFab
import br.gohan.pizzacmp.presenter.components.RowDeliver
import br.gohan.pizzacmp.presenter.components.RowInfo
import org.koin.compose.viewmodel.koinViewModel
import presentation.model.DriverState
import presentation.model.MapDirectionsState
import presentation.ui.theme.Dimens
import presentation.viewmodels.DeliverViewModel

@Composable
fun DeliverScreen(
    paddingValues: PaddingValues,
    viewModel: DeliverViewModel = koinViewModel(),
    goToChat: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    if (state.driver == null || state.mapDirections == null) {
        LoadingScreen()
    } else {
        DeliverScreenStateless(state.driver!!, state.mapDirections!!, paddingValues, goToChat)
    }
}

@Composable
fun DeliverScreenStateless(
    driver: DriverState,
    mapDirections: MapDirectionsState,
    paddingValues: PaddingValues,
    goToChat: () -> Unit = {},
) {
    var isExpanded by remember { mutableStateOf(false) }
    Column(
        horizontalAlignment = Alignment.End,
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .fillMaxSize().padding(
                top = paddingValues.calculateTopPadding(),
                bottom = paddingValues.calculateBottomPadding(),
            )
            .animateContentSize()
    ) {
        Box(
            modifier = Modifier
                .then(
                    if (isExpanded) Modifier.fillMaxSize().animateContentSize() else Modifier.size(
                        450.dp
                    ).animateContentSize()
                )
                .background(androidx.compose.ui.graphics.Color.Red),
            contentAlignment = Alignment.BottomEnd
        ) {
            DeliverFab(modifier = Modifier.padding(10.dp), isExpanded) {
                isExpanded = !isExpanded
            }
        }
        if (isExpanded.not()) {
            HorizontalDivider()
            RowDeliver(driver.image, driver.name) {
                goToChat()
            }
            RowInfo("Estimated time", info = mapDirections.estimatedTime)
            Spacer(modifier = Modifier.weight(1f))

            ButtonSecondary(label = "Cancel delivery") {

            }
            Spacer(modifier = Modifier.height(Dimens.paddingInsideLarge))

        }
    }
}

