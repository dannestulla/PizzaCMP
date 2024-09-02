/*
package presentation

import app.cash.turbine.test
import categoriesMock
import data.PizzaRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.mock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals


class CategoriesViewModelTest {
    private lateinit var viewModel: CategoriesViewModel

    @BeforeTest
    fun setUp() {
        val repository = mock<PizzaRepository> {
            everySuspend { getCategories() } returns categoriesMock
        }
        viewModel = CategoriesViewModel(repository, CoroutineScope(Dispatchers.Default))
    }

    @Test
    fun `GIVEN viewModel is created WHEN screen is starting THEN fetch all categories`() =
        runBlocking {
            // Test items loaded
            viewModel.state.test {
                val item = awaitItem()
                assertEquals(item.categories, categoriesMock)
            }
        }
}*/
