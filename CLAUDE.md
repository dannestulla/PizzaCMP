# PizzaCMP — Claude Configuration

## Project Overview

Kotlin Multiplatform (KMP) pizza ordering app using Compose Multiplatform.
Targets: Android, iOS, Desktop (JVM).

## Modules

- **composeApp** — UI layer (Compose screens, components, actions). Platform-specific entrypoints
  under `androidMain` / `nativeMain`.
- **shared** — Business logic shared across platforms: data, domain, presentation (ViewModels,
  states). Package root has no prefix (e.g., `presentation.CheckoutViewModel`).
- **server** — Ktor backend server (runs locally on port 8080).

## Package

`br.gohan.pizzacmp` for composeApp. Shared module uses top-level packages: `data`, `domain`,
`presentation`.

## Architecture: MVI

- **State** — immutable data class in `presentation/model/` (e.g., `CheckoutState`)
- **Action** — sealed class/interface in `presenter/actions/` (e.g., `CheckoutAction`)
- **ViewModel** — in `shared/src/commonMain/kotlin/presentation/`, takes `PizzaRepository` +
  `CoroutineScope` via Koin, exposes `MutableStateFlow<State>`
- **Screen** — `@Composable` in `composeApp/.../presenter/screens/`, injects ViewModel via
  `koinInject()`
- **Component** — stateless `@Composable` in `presenter/components/`

Screens should have a stateless variant (e.g., `ProductsScreenStateless`) for previews/testing.

## Tech Stack

| Concern           | Library                            |
|-------------------|------------------------------------|
| UI                | Compose Multiplatform              |
| Networking        | Ktor Client (CIO engine)           |
| Local DB          | SqlDelight (`PizzaDatabase`)       |
| DI                | Koin (`initKoin` in `SharedDI.kt`) |
| Serialization     | kotlinx.serialization              |
| Image loading     | Coil                               |
| Animations        | Kottie                             |
| Cross-screen data | DataStore (`DataStoreManager`)     |

## DI Conventions

- Modules defined in `SharedDI.kt`: `api`, `database`, `core`
- ViewModels registered as `factory` (except `ChatViewModel` which is `single`)
- `PizzaRepository` registered as `single<PizzaRepository>`

## Build Commands

```bash
# Android
./gradlew :composeApp:assembleDebug

# Run server
./gradlew :server:run

# Tests (shared module)
./gradlew :shared:test
```

## Key Files

- `SharedDI.kt` — Koin module setup
- `ShopNavigation.kt` / `ShopRoutes.kt` — Navigation graph
- `DataStoreManager.kt` — Cross-screen product passing
- `Dimens.kt` — Spacing constants
- `Constants.kt` — App-wide constants (two copies: shared + server)

## Conventions

- Stateless composables preferred for testability
- ViewModels live in `shared` (not `composeApp`) for KMP reuse
- `expect`/`actual` pattern for platform-specific implementations (e.g., `Util.kt`, `Database.kt`,
  `DataStore.kt`)
- Server base URL: `http://10.0.2.2:8080` (Android emulator localhost)
