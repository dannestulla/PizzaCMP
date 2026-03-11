# Driver App — Design Spec
**Date:** 2026-03-11
**Status:** Approved

---

## Overview

A second Compose Multiplatform app targeting Android and iOS for the delivery driver. The driver app auto-accepts orders and immediately presents a combined route + chat screen. No login, no order management flow — simple and focused.

---

## Module Structure

New Gradle module `:driverApp` added alongside `:composeApp` at the project root. Mirrors the existing composeApp skeleton exactly.

```
driverApp/
├── src/
│   ├── androidMain/kotlin/         ← Android MainActivity entrypoint
│   ├── nativeMain/kotlin/          ← iOS entrypoint
│   └── commonMain/kotlin/br/gohan/pizzadriver/
│       ├── presenter/
│       │   ├── screens/
│       │   │   └── DriverScreen.kt
│       │   └── components/
│       ├── ui/theme/
│       ├── DriverNavigation.kt
│       └── DriverRoutes.kt
```

**Implementation action:** Add `include(":driverApp")` to `settings.gradle.kts`.

The module declares a dependency on `:shared` for all business logic — no duplication between apps.

---

## Screen Design

One screen: `DriverScreen`. Vertical split layout:

```
┌─────────────────────────┐
│                         │
│    ROUTE INFO (text)    │  ← top half: streets list + ETA as text
│   Street 1, Street 2…   │    no map SDK — text-based display only (v1)
│   ETA: 10 min           │
│                         │
├─────────────────────────┤
│  Chat with customer     │  ← bottom half
│  [msg] [msg] [msg]      │
│  [_____________] [Send] │
└─────────────────────────┘
```

- **Top half** — text list of route streets and ETA from `MapDirections`, powered by `DeliverViewModel`. No map SDK is used in v1 — this is a plain text route display.
- **Bottom half** — real-time chat powered by `ChatViewModel`. The screen must call `chatViewModel.getMessages()` inside a `LaunchedEffect` on composition to start the message stream.

---

## Prerequisites — Fixes Required in `:shared` Before Implementation

These issues exist in the current codebase and must be resolved before the driver app can be built:

### 1. Define `Message` data class
`shared/src/commonMain/kotlin/presentation/model/Message.kt` exists but is empty. The `Message` data class must be defined with at minimum the fields needed for chat (e.g., `sender`, `content`, `timestamp`).

### 2. Fix `ChatViewModel` — Android-only `ViewModel` inheritance
`ChatViewModel` currently extends `androidx.lifecycle.ViewModel`, which is Android-only. This breaks iOS compilation. It must be updated to match the rest of the project's pattern (plain class with `KoinComponent`, same as `DeliverViewModel`) or the `lifecycle-viewmodel` KMP artifact must be added to `:shared`.

### 3. Platform-specific base URL for iOS
`SharedDI.kt` hardcodes the base URL to `http://10.0.2.2:8080` (Android emulator localhost). This does not resolve on iOS. An `expect`/`actual` constant for the base URL must be added — consistent with the project's existing `expect`/`actual` pattern — so iOS uses the correct host.

---

## Reused from `:shared` (after prerequisite fixes)

| Asset | Purpose |
|---|---|
| `DeliverViewModel` | Fetches `/driver` and `/directions`, exposes `MapDirections` |
| `ChatViewModel` | Sends/receives chat messages (after Android-ViewModel fix) |
| `RemoteDataSource` | All HTTP calls already implemented |
| `Driver` model | Driver name and image |
| `MapDirections` model | Routes list (`List<String>`) and ETA (`String`) |
| `Message` model | Chat messages (must be defined first) |
| `SharedDI.kt` | Koin module setup (after base URL fix) |

---

## App Startup Flow

No login or order acceptance step. On launch:

```
App launches
  → initKoin (SharedDI)
  → DriverScreen
      → DeliverViewModel fetches /driver + /directions on init
      → LaunchedEffect calls chatViewModel.getMessages() to start stream
```

---

## Navigation

Single screen — no `NavHost` needed. `DriverNavigation.kt` and `DriverRoutes.kt` are scaffolded for future expansion but start minimal.

---

## Server

No server changes required. All endpoints used by the driver app already exist:

| Endpoint | Used for |
|---|---|
| `GET /driver` | Driver name + image |
| `GET /directions` | Route streets + ETA |
| `GET /messages` | Real-time chat stream |

---

## Out of Scope (v1)

- Driver authentication / login
- Order accept / reject flow
- Driver location broadcasting
- Push notifications
- Delivery confirmation
- Interactive map / map SDK integration
