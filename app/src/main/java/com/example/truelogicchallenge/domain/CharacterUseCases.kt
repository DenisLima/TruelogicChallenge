package com.example.truelogicchallenge.domain

import com.example.truelogicchallenge.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterUseCases {
    suspend fun getCharacterList(): Flow<List<Character>>
}