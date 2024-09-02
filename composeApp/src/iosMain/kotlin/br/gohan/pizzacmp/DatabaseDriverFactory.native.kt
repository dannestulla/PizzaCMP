package br.gohan.pizzacmp

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import br.gohan.pizzacmp.database.PizzaDatabase
import data.DatabaseDriverFactory


class IOSDatabaseDriverFactory : DatabaseDriverFactory {
    override fun createDriver(): SqlDriver {
        return NativeSqliteDriver(PizzaDatabase.Schema, "pizza.db")
    }
}