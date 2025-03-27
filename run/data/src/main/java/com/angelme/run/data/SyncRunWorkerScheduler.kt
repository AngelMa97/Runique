package com.angelme.run.data

import android.content.Context
import androidx.work.Constraints
import androidx.work.Data
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.await
import com.angelme.core.database.dao.RunPendingSyncDao
import com.angelme.core.database.entity.DeletedRunsSyncEntity
import com.angelme.core.database.entity.RunPendingSyncEntity
import com.angelme.core.database.mappers.toRunEntity
import com.angelme.core.domain.SessionStorage
import com.angelme.core.domain.run.Run
import com.angelme.core.domain.run.RunId
import com.angelme.run.data.CreateRunWorker.Companion.RUN_ID
import com.angelme.core.domain.run.SyncRunScheduler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.TimeUnit
import kotlin.time.Duration
import kotlin.time.toJavaDuration

class SyncRunWorkerScheduler(
    private val context: Context,
    private val pendingSyncDao: RunPendingSyncDao,
    private val sessionStorage: SessionStorage,
    private val applicationScope: CoroutineScope
): SyncRunScheduler {

    val workManager = WorkManager.getInstance(context)

    override suspend fun scheduleSync(type: SyncRunScheduler.SyncType) {
        when(type) {
            is SyncRunScheduler.SyncType.CreateRun -> scheduleCreateRunWorker(type.run, type.mapPictureBytes)
            is SyncRunScheduler.SyncType.DeleteRun -> scheduleDeleteRunWorker(type.runId)
            is SyncRunScheduler.SyncType.FetchRuns -> scheduleFetchRunsWorker(type.interval)
        }
    }

    private suspend fun scheduleDeleteRunWorker(runId: RunId) {
        val userId = sessionStorage.get()?.userId ?: return
        val entity = DeletedRunsSyncEntity(
            runId = runId,
            userId = userId
        )
        pendingSyncDao.upsertDeletedRunsSyncEntity(entity)

        val workRequest = OneTimeWorkRequestBuilder<DeleteRunWorker>()
            .addTag("delete_work")
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .setBackoffCriteria(
                backoffPolicy = androidx.work.BackoffPolicy.EXPONENTIAL,
                backoffDelay = 2000L,
                timeUnit = TimeUnit.MILLISECONDS
            )
            .setInputData(
                Data.Builder()
                    .putString(DeleteRunWorker.RUN_ID, entity.runId)
                    .build()
            )
            .build()

        applicationScope.launch {
            workManager.enqueue(workRequest).await()
        }.join()
    }

    private suspend fun scheduleCreateRunWorker(run: Run, mapPictureBytes: ByteArray) {
        val userId = sessionStorage.get()?.userId ?: return
        val pendingRun = RunPendingSyncEntity(
            run = run.toRunEntity(),
            mapPictureBytes = mapPictureBytes,
            userId = userId
        )
        pendingSyncDao.upsertRunPendingSyncEntity(pendingRun)

        val workRequest = OneTimeWorkRequestBuilder<CreateRunWorker>()
            .addTag("create_work")
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .setBackoffCriteria(
                backoffPolicy = androidx.work.BackoffPolicy.EXPONENTIAL,
                backoffDelay = 2000L,
                timeUnit = TimeUnit.MILLISECONDS
            )
            .setInputData(
                Data.Builder()
                    .putString(RUN_ID, pendingRun.runId)
                    .build()
            )
            .build()

        applicationScope.launch {
            workManager.enqueue(workRequest).await()
        }.join()
    }

    private suspend fun scheduleFetchRunsWorker(interval: Duration) {
        val isSyncScheduled = withContext(Dispatchers.IO) {
            workManager
                .getWorkInfosByTag("sync_work")
                .get()
                .isNotEmpty()
        }

        if (isSyncScheduled) return

        val workRequest = PeriodicWorkRequestBuilder<FetchRunsWorker>(
            repeatInterval = interval.toJavaDuration()
        )
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(NetworkType.CONNECTED)
                    .build()
            )
            .setBackoffCriteria(
                backoffPolicy = androidx.work.BackoffPolicy.EXPONENTIAL,
                backoffDelay = 2000L,
                timeUnit = TimeUnit.MILLISECONDS
            )
            .setInitialDelay(
                duration = 30,
                timeUnit = TimeUnit.MINUTES
            )
            .addTag("sync_work")
            .build()

        workManager.enqueue(workRequest).await()

    }

    override suspend fun cancelAllSyncs() {
        WorkManager.getInstance(context)
            .cancelAllWork()
            .await()
    }
}