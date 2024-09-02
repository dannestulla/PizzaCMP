package br.gohan.pizzacmp

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import br.gohan.pizzacmp.database.PizzaDatabase
import data.DatabaseDriverFactory
import org.koin.dsl.module

class AndroidDatabaseDriverFactory(private val context: Context) : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(PizzaDatabase.Schema, context, "pizza.db")
    }
}

actual val database = module {
    single<SqlDriver> {
        AndroidDatabaseDriverFactory(get()).createDriver()
    }

    single {
        PizzaDatabase(get())
    }
}