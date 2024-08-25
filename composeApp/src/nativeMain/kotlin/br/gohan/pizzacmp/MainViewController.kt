package br.gohan.pizzacmp

import androidx.compose.ui.window.ComposeUIViewController

fun MainViewController() = ComposeUIViewController { PizzaApp(NativeDataStoreManager()) }