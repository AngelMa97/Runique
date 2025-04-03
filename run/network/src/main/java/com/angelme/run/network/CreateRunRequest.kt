package com.angelme.run.network

import android.annotation.SuppressLint
import kotlinx.serialization.Serializable

@SuppressLint("UnsafeOptInUsageError")
@Serializable
data class CreateRunRequest(
    val durationMillis: Long,
    val distanceMeters: Int,
    val epochMillis: Long,
    val lat: Double,
    val long: Double,
    val avgSpeedKmh: Double,
    val maxSpeedKmh: Double,
    val totalElevationMeters: Int,
    val avgHeartRate: Int?,
    val maxHeartRate: Int?,
    val id: String
)
