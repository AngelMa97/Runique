package com.angelme.run.data.di

import com.angelme.core.domain.run.SyncRunScheduler
import com.angelme.run.data.CreateRunWorker
import com.angelme.run.data.DeleteRunWorker
import com.angelme.run.data.FetchRunsWorker
import com.angelme.run.data.SyncRunWorkerScheduler
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val runDataModule = module {
    workerOf(::CreateRunWorker)
    workerOf(::FetchRunsWorker)
    workerOf(::DeleteRunWorker)

    singleOf(::SyncRunWorkerScheduler).bind<SyncRunScheduler>()
}