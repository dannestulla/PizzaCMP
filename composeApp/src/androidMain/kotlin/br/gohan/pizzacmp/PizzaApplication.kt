package br.gohan.pizzacmp

import android.app.Application
import initKoin
import org.koin.android.ext.koin.androidContext

class PizzaApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@PizzaApplication)
        }
    }
}