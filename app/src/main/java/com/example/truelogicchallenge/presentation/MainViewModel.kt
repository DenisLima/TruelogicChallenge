package com.example.truelogicchallenge.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.truelogicchallenge.domain.CharacterUseCases
import com.example.truelogicchallenge.domain.model.Character
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel(
    private val characterUseCases: CharacterUseCases
): ViewModel() {

    private val viewStateLv = MutableLiveData<MainViewState>()
    fun getViewState(): LiveData<MainViewState> = viewStateLv

    fun initEvent(event: MainEvent) {
        when(event) {
            is MainEvent.FetchCharacterList -> getCharacterList()
        }
    }

    private fun getCharacterList() {
        viewModelScope.launch {
            characterUseCases.getCharacterList()
                .collect { characterList ->
                    viewStateLv.postValue(MainViewState.GetCharacterList(characterList = characterList))
                }
        }
    }

    sealed class MainEvent {
        object FetchCharacterList : MainEvent()
    }

    sealed class MainViewState {
        data class GetCharacterList(val characterList: List<Character>) : MainViewState()
    }
}