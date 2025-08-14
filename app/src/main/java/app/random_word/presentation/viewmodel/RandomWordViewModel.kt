package app.random_word.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.random_word.domain.usecase.GetRandomWordUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RandomWordViewModel @Inject constructor(
    private val getRandomWordUseCase: GetRandomWordUseCase
): ViewModel() {

    private val _word = MutableStateFlow("")
    val word = _word.asStateFlow()

    private val _error = MutableStateFlow<String?>(null)
    val error = _error.asStateFlow()


    fun fetchWord() {
        viewModelScope.launch {
            getRandomWordUseCase().onSuccess {
                _word.value = it
                _error.value = null
            }.onFailure {
                _error.value = it.message
            }
        }
    }


}