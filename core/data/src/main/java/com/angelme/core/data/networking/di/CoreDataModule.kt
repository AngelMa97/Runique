package com.angelme.core.data.networking.di

import com.angelme.core.data.auth.EncryptedSessionStorage
import com.angelme.core.data.networking.HttpClientFactory
import com.angelme.core.domain.SessionStorage
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreDataModule = module {
    single {
        HttpClientFactory(
            sessionStorage = get()
        ).build()
    }
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
}