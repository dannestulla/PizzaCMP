package data

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import br.gohan.pizzacmp.database.PizzaDatabase
import org.koin.dsl.module


actual val database = module {
    single<SqlDriver> {
        AndroidSqliteDriver(
            PizzaDatabase.Schema,
            get(),
            "pizza.db"
        )
    }
}
