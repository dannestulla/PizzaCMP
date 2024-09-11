package br.gohan.pizzacmp

import app.cash.sqldelight.db.SqlDriver
import br.gohan.pizzacmp.database.IOSDatabaseDriverFactory
import br.gohan.pizzacmp.database.PizzaDatabase
import org.koin.dsl.module

actual val database = module {
    single<SqlDriver> {
        IOSDatabaseDriverFactory().createDriver()
    }

    single {
        PizzaDatabase(get())
    }
}