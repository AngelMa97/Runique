package com.angelme.wear.run.domain

import com.angelme.core.connectivity.domain.DeviceNode
import com.angelme.core.connectivity.domain.messaging.MessagingAction
import com.angelme.core.connectivity.domain.messaging.MessagingError
import com.angelme.core.domain.util.EmptyResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

interface PhoneConnector {
    val connectedNode: StateFlow<DeviceNode?>
    val messagingActions: Flow<MessagingAction>

    suspend fun sendActionToPhone(action: MessagingAction): EmptyResult<MessagingError>
}