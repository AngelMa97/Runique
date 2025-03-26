package com.angelme.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DeletedRunsSyncEntity(
    @PrimaryKey(autoGenerate = false)
    val runId: String,
    val userId: String
)
