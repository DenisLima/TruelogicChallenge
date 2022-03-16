package com.example.truelogicchallenge.data.remotesource

import com.example.truelogicchallenge.data.model.DCharacter
import retrofit2.http.GET

interface CharacterRemoteSource {

    @GET("characters?limit=100")
    suspend fun getCharacterList(): List<DCharacter>
}