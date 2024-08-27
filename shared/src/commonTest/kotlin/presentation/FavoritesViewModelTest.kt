package presentation

import data.PizzaRepository
import dev.mokkery.answering.returns
import dev.mokkery.everySuspend
import dev.mokkery.matcher.any
import dev.mokkery.mock
import dev.mokkery.verify
import dev.mokkery.verify.VerifyMode
import favoriteFixture
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import productsMock
import kotlin.test.BeforeTest
import kotlin.test.Test


class FavoritesViewModelTest {
    private lateinit var viewModel: FavoritesViewModel
    private lateinit var repository: PizzaRepository

    @BeforeTest
    fun setUp() {
        repository = mock<PizzaRepository> {
            everySuspend { getProducts() } returns flowOf(favoriteFixture)
            everySuspend { removeExample(any()) } returns Unit
        }
        viewModel = FavoritesViewModel(repository, CoroutineScope(Dispatchers.Default))
    }

    @Test
    fun `GIVEN screen is loaded WHEN user removes from favorite THEN remove product from favorites`() =
        runBlocking {
            viewModel.removeFavorite(productsMock.first())
            verify(mode = VerifyMode.exactly(1)) { repository.removeExample(any()) }
        }
}
