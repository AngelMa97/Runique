package com.angelme.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.angelme.core.database.entity.DeletedRunsSyncEntity
import com.angelme.core.database.entity.RunPendingSyncEntity

@Dao
interface RunPendingSyncDao {

    // Created runs

    @Query("SELECT * FROM RunPendingSyncEntity WHERE userId = :userId")
    suspend fun getAllRunPendingSyncEntities(userId: String): List<RunPendingSyncEntity>

    @Query("SELECT * FROM RunPendingSyncEntity WHERE runId = :runId")
    suspend fun getRunPendingSyncEntity(runId: String): RunPendingSyncEntity?

    @Upsert
    suspend fun upsertRunPendingSyncEntity(runPendingSyncEntity: RunPendingSyncEntity)

    @Query("DELETE FROM runpendingsyncentity WHERE runId = :runId")
    suspend fun deleteRunPendingSyncEntity(runId: String)

    // Deleted runs

    @Query("SELECT * FROM deletedrunssyncentity WHERE userId = :userId")
    suspend fun getAllDeletedRunsSyncEntities(userId: String): List<DeletedRunsSyncEntity>

    @Upsert
    suspend fun upsertDeletedRunsSyncEntity(deletedRunsSyncEntity: DeletedRunsSyncEntity)

    @Query("DELETE FROM deletedrunssyncentity WHERE runId = :runId")
    suspend fun deleteDeletedRunsSyncEntity(runId: String)
}