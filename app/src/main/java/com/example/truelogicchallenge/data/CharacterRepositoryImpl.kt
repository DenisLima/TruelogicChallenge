package com.example.truelogicchallenge.data

import com.example.truelogicchallenge.data.mapper.CharacterMapper
import com.example.truelogicchallenge.data.remotesource.CharacterRemoteSource
import com.example.truelogicchallenge.domain.CharacterRepository
import com.example.truelogicchallenge.domain.model.Character
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CharacterRepositoryImpl(
    private val characterRemoteSource: CharacterRemoteSource,
    private val characterMapper: CharacterMapper
) : CharacterRepository {
    override suspend fun getCharacterList(): Flow<List<Character>> = flow {
        val characterList = characterRemoteSource.getCharacterList()
        emit(characterMapper.transform(characterList))
    }
}