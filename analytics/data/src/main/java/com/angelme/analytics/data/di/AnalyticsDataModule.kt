package com.angelme.analytics.data.di

import com.angelme.analytics.data.RoomAnalyticsRepository
import com.angelme.analytics.domain.AnalyticsRepository
import com.angelme.core.database.RunDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val analyticsModule = module {
    singleOf(::RoomAnalyticsRepository).bind<AnalyticsRepository>()
    single {
        get<RunDatabase>().analyticsDao
    }
}