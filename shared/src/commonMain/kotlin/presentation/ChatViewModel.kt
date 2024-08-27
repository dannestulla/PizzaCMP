package presentation

import data.PizzaRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import presentation.model.ChatState

class ChatViewModel(
    private val repository: PizzaRepository,
    private val viewModelScope: CoroutineScope
) : KoinComponent {

    private val _state = MutableStateFlow(ChatState())
    val state = _state.asStateFlow()

    init {
        getMessages()
    }

    private fun getMessages() {
        viewModelScope.launch {
            repository.getMessages().collect {
                _state.update {
                    it
                }
            }
        }
    }
}