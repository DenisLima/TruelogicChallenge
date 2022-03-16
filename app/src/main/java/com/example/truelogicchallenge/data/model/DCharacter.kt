package com.example.truelogicchallenge.data.model

import com.google.gson.annotations.SerializedName

data class DCharacter(
    @SerializedName("char_id") val charId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("nickname") val nickname: String,
    @SerializedName("img") val image: String
)
