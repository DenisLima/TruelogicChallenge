package com.example.truelogicchallenge.domain

import com.example.truelogicchallenge.domain.model.Character
import kotlinx.coroutines.flow.Flow

interface CharacterRepository {
    suspend fun getCharacterList(): Flow<List<Character>>
}