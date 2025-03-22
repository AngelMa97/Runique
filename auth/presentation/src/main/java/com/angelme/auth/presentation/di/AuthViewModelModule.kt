package com.angelme.auth.presentation.di

import com.angelme.auth.presentation.login.LoginViewModel
import com.angelme.auth.presentation.register.RegisterViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::RegisterViewModel)
    viewModelOf(::LoginViewModel)
}