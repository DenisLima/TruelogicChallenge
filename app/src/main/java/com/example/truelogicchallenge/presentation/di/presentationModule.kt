package com.example.truelogicchallenge.presentation.di

import com.example.truelogicchallenge.presentation.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {

    viewModel {
        MainViewModel(get())
    }
}