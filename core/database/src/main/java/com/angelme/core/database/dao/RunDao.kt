package com.angelme.core.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.angelme.core.database.entity.RunEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RunDao {

    @Upsert
    suspend fun upsertRun(runEntity: RunEntity)

    @Upsert
    suspend fun upsertRuns(runEntities: List<RunEntity>)

    @Query("SELECT * FROM runentity ORDER BY dateTimeUtc DESC")
    fun getRuns(): Flow<List<RunEntity>>

    @Query("DELETE FROM runentity WHERE id = :id")
    suspend fun deleteRun(id: String)

    @Query("DELETE FROM runentity")
    suspend fun deleteRuns()
}