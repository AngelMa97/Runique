package com.angelme.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.angelme.core.database.dao.RunDao
import com.angelme.core.database.entity.RunEntity

@Database(
    entities = [RunEntity::class],
    version = 1
)
abstract class RunDatabase: RoomDatabase() {

    abstract val runDao: RunDao
}