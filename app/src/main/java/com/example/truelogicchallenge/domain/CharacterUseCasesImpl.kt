package com.example.truelogicchallenge.domain

import com.example.truelogicchallenge.domain.model.Character
import kotlinx.coroutines.flow.Flow

class CharacterUseCasesImpl(
    private val characterRepository: CharacterRepository
): CharacterUseCases {
    override suspend fun getCharacterList(): Flow<List<Character>> = characterRepository.getCharacterList()
}