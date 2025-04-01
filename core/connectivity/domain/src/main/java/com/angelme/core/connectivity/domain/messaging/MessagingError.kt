package com.angelme.core.connectivity.domain.messaging

import com.angelme.core.domain.util.Error

enum class MessagingError: Error {
    CONNECTION_INTERRUPTED,
    DISCONNECTED,
    UNKNOWN
}