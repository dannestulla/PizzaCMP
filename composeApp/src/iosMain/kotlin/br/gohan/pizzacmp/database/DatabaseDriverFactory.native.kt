package br.gohan.pizzacmp.database

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import data.local.DatabaseDriverFactory


class IOSDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(PizzaDatabase.Schema, "pizza.db")
    }
}