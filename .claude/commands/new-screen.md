Create a new MVI screen for the PizzaCMP project named "$ARGUMENTS".

Follow the existing conventions exactly. Create all files in order:

---

## 1. State — `shared/src/commonMain/kotlin/presentation/model/<Name>State.kt`

Use a `@JvmInline value class` wrapping the main data (nullable for loading), matching the pattern
of `CheckoutState`:

```kotlin
package presentation.model

import kotlin.jvm.JvmInline

@JvmInline
value class <Name>State(val /* main data */ = null)
```

---

## 2. Action —
`composeApp/src/commonMain/kotlin/br/gohan/pizzacmp/presenter/actions/<Name>Action.kt`

Sealed class with data classes for each user intent:

```kotlin
package br.gohan.pizzacmp.presenter.actions

sealed class <Name>Action {
    // data class <ActionName>(...) : <Name>Action()
}
```

---

## 3. ViewModel — `shared/src/commonMain/kotlin/presentation/<Name>ViewModel.kt`

- Takes `PizzaRepository` and `CoroutineScope` as constructor params
- Implements `KoinComponent`
- Exposes `state` as `StateFlow` (private `_state` + public `asStateFlow()`)
- Loads data in `init` block via `viewModelScope.launch`

```kotlin
package presentation

import data.PizzaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import presentation.model.<Name > State

class <Name>ViewModel(
private val repository: PizzaRepository,
private val viewModelScope: CoroutineScope,
) : KoinComponent {

    private val _state = MutableStateFlow(< Name > State ())
    val state = _state.asStateFlow()

    init {
        load<Name>()
    }

    private fun load<Name>() {
        viewModelScope.launch {
            _state.update {
                <Name > State(/* repository call */)
            }
        }
    }
}
```

---

## 4. Screen —
`composeApp/src/commonMain/kotlin/br/gohan/pizzacmp/presenter/screens/<Name>Screen.kt`

Two composables:

- **`<Name>Screen`** — stateful, injects ViewModel via `koinInject()`, shows `LoadingScreen()` while
  state is null, delegates to stateless
- **`<Name>ScreenStateless`** — receives state + `(Action) -> Unit` lambda, no ViewModel dependency

Pattern:

```kotlin
package br.gohan.pizzacmp.presenter.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import br.gohan.pizzacmp.presenter.actions.<Name > Action
import org . koin . compose . koinInject
        import presentation .<Name> ViewModel

        @Composable
        fun <Name> Screen(
            paddingValues: PaddingValues,
            viewModel: <Name> ViewModel = koinInject (),
// navigation callbacks
) {
    val state by viewModel.state.collectAsState()

    if (state./* data */ == null) {
    LoadingScreen()
} else {
    <Name > ScreenStateless(paddingValues, state./* data */!!) { action ->
    when (action) {
        // delegate to viewModel methods
    }
}
}
}

@Composable
fun <Name> ScreenStateless(
    paddingValues: PaddingValues,
    // data params,
    action: (<Name>Action
) -> Unit
) {
    // UI implementation
}
```

---

## 5. Register ViewModel in Koin — `shared/src/commonMain/kotlin/SharedDI.kt`

Add to the `core` module as `factory`:

```kotlin
factory { <Name > ViewModel(get(), get()) }
```

---

## 6. Add route — `composeApp/src/commonMain/kotlin/br/gohan/pizzacmp/ShopRoutes.kt`

Add to `PizzaRoutes` enum and `getRoute()` when block.

---

## 7. Wire navigation — `composeApp/src/commonMain/kotlin/br/gohan/pizzacmp/ShopNavigation.kt`

Add `composable(PizzaRoutes.<Name>.name)` entry in `PizzaNavigation`, import the new screen.

---

Ask for clarification on the screen's purpose and data it needs before creating files if
`$ARGUMENTS` is empty or unclear.
