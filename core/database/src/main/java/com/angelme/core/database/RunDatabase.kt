package com.angelme.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.angelme.core.database.dao.AnalyticsDao
import com.angelme.core.database.dao.RunDao
import com.angelme.core.database.dao.RunPendingSyncDao
import com.angelme.core.database.entity.DeletedRunsSyncEntity
import com.angelme.core.database.entity.RunEntity
import com.angelme.core.database.entity.RunPendingSyncEntity

@Database(
    entities = [
        RunEntity::class,
        RunPendingSyncEntity::class,
        DeletedRunsSyncEntity::class
    ],
    version = 1
)
abstract class RunDatabase : RoomDatabase() {

    abstract val runDao: RunDao
    abstract val runPendingSyncDao: RunPendingSyncDao
    abstract val analyticsDao: AnalyticsDao
}