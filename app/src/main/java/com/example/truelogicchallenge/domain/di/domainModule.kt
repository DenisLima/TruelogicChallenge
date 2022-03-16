package com.example.truelogicchallenge.domain.di

import com.example.truelogicchallenge.domain.CharacterUseCases
import com.example.truelogicchallenge.domain.CharacterUseCasesImpl
import org.koin.dsl.module

val domainModule = module {

    single<CharacterUseCases> {
        CharacterUseCasesImpl(get())
    }
}