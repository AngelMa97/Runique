package com.angelme.run.domain

import com.angelme.core.connectivity.domain.DeviceNode
import kotlinx.coroutines.flow.StateFlow

interface WatchConnector {
    val connectedDevice: StateFlow<DeviceNode?>
    fun setIsTrackable(isTrackable: Boolean)
}