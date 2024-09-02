package presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import data.PizzaRepository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import presentation.model.Message

class ChatViewModel(
    private val repository: PizzaRepository
) : ViewModel() {

    private val _state = MutableSharedFlow<Message>()
    val state = _state.asSharedFlow()

    fun getMessages() {
        viewModelScope.launch {
            repository.getMessages().collectLatest {
                _state.emit(it)
                }
            }
        }
    }
