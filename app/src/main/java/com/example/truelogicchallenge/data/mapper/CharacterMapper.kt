package com.example.truelogicchallenge.data.mapper

import com.example.truelogicchallenge.data.model.DCharacter
import com.example.truelogicchallenge.data.utils.BaseMapper
import com.example.truelogicchallenge.domain.model.Character

class CharacterMapper: BaseMapper<DCharacter, Character>() {
    override fun transform(entity: DCharacter): Character {
        return Character(
            charId = entity.charId,
            name = entity.name,
            nickname = entity.nickname,
            image = entity.image
        )
    }
}