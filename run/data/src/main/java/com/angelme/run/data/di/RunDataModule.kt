package com.angelme.run.data.di

import com.angelme.run.data.CreateRunWorker
import com.angelme.run.data.DeleteRunWorker
import com.angelme.run.data.FetchRunsWorker
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.dsl.module

val runDataModule = module {
    workerOf(::CreateRunWorker)
    workerOf(::FetchRunsWorker)
    workerOf(::DeleteRunWorker)
}