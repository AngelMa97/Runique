package com.angelme.auth.data.di

import com.angelme.auth.data.AuthRepositoryImpl
import com.angelme.auth.data.EmailPatternValidator
import com.angelme.auth.domain.AuthRepository
import com.angelme.auth.domain.PatternValidator
import com.angelme.auth.domain.UserDataValidator
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)
    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}