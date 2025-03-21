package com.angelme.runique

import android.app.Application
import com.angelme.auth.data.di.authDataModule
import com.angelme.auth.presentation.di.authViewModelModule
import com.angelme.core.data.networking.di.coreDataModule
import com.angelme.core.presentation.designsystem.BuildConfig
import com.angelme.run.location.di.locationModule
import com.angelme.run.presentation.di.runPresentationModule
import com.angelme.runique.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class RuniqueApp: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@RuniqueApp)
            modules(
                authDataModule,
                authViewModelModule,
                appModule,
                coreDataModule,
                runPresentationModule,
                locationModule
            )
        }
    }
}