# Driver App — Design Spec
**Date:** 2026-03-11
**Status:** Approved

---

## Overview

A second Compose Multiplatform app targeting Android and iOS for the delivery driver. The driver app auto-accepts orders and immediately presents a combined map + chat screen. No login, no order management flow — simple and focused.

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

`settings.gradle.kts` includes `:driverApp`. The module declares a dependency on `:shared` for all business logic — no duplication between apps.

---

## Screen Design

One screen: `DriverScreen`. Vertical split layout:

```
┌─────────────────────────┐
│                         │
│      MAP / ROUTE        │  ← top half
│   (streets + ETA)       │
│                         │
├─────────────────────────┤
│  Chat with customer     │  ← bottom half
│  [msg] [msg] [msg]      │
│  [_____________] [Send] │
└─────────────────────────┘
```

- **Top half** — route information from `MapDirections` (list of streets + estimated time), powered by `DeliverViewModel`
- **Bottom half** — real-time chat powered by `ChatViewModel`

Both halves share the screen with no separate navigation between them.

---

## Reused from `:shared` (zero changes)

| Asset | Purpose |
|---|---|
| `DeliverViewModel` | Fetches `/driver` and `/directions`, exposes `MapDirections` |
| `ChatViewModel` | Connects to `/messages`, sends/receives chat |
| `RemoteDataSource` | All HTTP calls already implemented |
| `Driver` model | Driver name and image |
| `MapDirections` model | Routes list and ETA |
| `Message` model | Chat messages |
| `SharedDI.kt` | Koin module setup, reused as-is |

---

## App Startup Flow

No login or order acceptance step. On launch:

```
App launches
  → initKoin (SharedDI)
  → DriverScreen
      → DeliverViewModel.init() fetches /driver + /directions
      → ChatViewModel.init() connects to /messages stream
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
| `POST /order` | (not used in driver app) |

---

## Out of Scope (v1)

- Driver authentication / login
- Order accept / reject flow
- Driver location broadcasting
- Push notifications
- Delivery confirmation
