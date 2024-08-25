package br.gohan.pizzacmp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val preferencesManager = AndroidDataStoreManager(applicationContext)
        enableEdgeToEdge()

        setContent {
            PizzaApp(preferencesManager)
        }
    }
}

