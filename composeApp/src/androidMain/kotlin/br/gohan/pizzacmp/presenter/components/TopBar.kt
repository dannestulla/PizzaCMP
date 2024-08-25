package br.gohan.pizzacmp.presenter.components

import androidx.compose.foundation.background
import br.gohan.pizzacmp.examples.components.topbar.TopBarAction
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import br.gohan.pizzacmp.Dimens
import br.gohan.pizzacmp.ui.theme.PizzaTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    state: TopBarState,
    action: (TopBarAction) -> Unit
) = with(state) {
    var searchActive by remember {
        mutableStateOf(isSearchActive)
    }
    var search by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current

    TopAppBar(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.surfaceContainer),
        title = {
            if (searchActive) {
                LaunchedEffect(Unit) {
                    focusRequester.requestFocus()
                }
                TextField(
                    modifier = Modifier
                        .focusRequester(focusRequester = focusRequester)
                        .onKeyEvent { keyEvent ->
                            keyEvent.key.keyCode == Key.Enter.keyCode
                        },
                    maxLines = 1,
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.White
                    ),
                    textStyle = TextStyle(
                        //fontSize = Dimens.fontLarge
                    ),
                    value = search,
                    onValueChange = {
                        search = it
                        action(TopBarAction.Search(it))
                    })
            } else {
                Text(
                    title, //fontSize = Dimens.fontLarge,
                    textAlign = TextAlign.Center,
                    fontSize = Dimens.fontHuge,
                    style = TextStyle(
                        fontFamily = MaterialTheme.typography.titleMedium.fontFamily,
                    ),
                )
            }
        },
        navigationIcon = {
            IconButton(onClick = {
                action(TopBarAction.Back)
            }) {
                Icon(Icons.AutoMirrored.Default.KeyboardArrowLeft, contentDescription = "Back")
            }
        },
        actions = {
            if (noSearch) return@TopAppBar
            if (searchActive) {
                IconButton(onClick = { searchActive = !searchActive }) {
                    Icon(Icons.Default.Close, contentDescription = "Close")
                }
            } else {
                search = ""
                focusManager.clearFocus()
                action(TopBarAction.CancelSearch)
                IconButton(onClick = { searchActive = !searchActive }) {
                    Icon(Icons.Default.Search, contentDescription = "Search")
                }
            }
        })
}

@Preview
@Composable
private fun Preview() {
    PizzaTheme {
        TopBar(state = TopBarState(
            title = "Preview",
            isSearchActive = false,
            noSearch = false
        )
        ) {

        }
    }
}

data class TopBarState(
    var title: String,
    val isSearchActive: Boolean = false,
    val noSearch: Boolean = false
)
