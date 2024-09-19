package br.gohan.deliveryapp

import android.app.Application
import org.koin.android.ext.koin.androidContext


class DeliveryApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidContext(this@DeliveryApplication)
        }
    }
}