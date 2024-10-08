package br.gohan.pizzacmp


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import br.gohan.pizzacmp.database.AndroidDataStoreManager


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataStore = AndroidDataStoreManager(applicationContext)
        enableEdgeToEdge()

        setContent {
            PizzaApp(dataStore)
        }
    }
}


