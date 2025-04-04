package com.angelme.core.connectivity.data

import com.angelme.core.connectivity.domain.DeviceNode
import com.google.android.gms.wearable.Node

fun Node.toDeviceNode(): DeviceNode =
    DeviceNode(
        id = id,
        displayName = displayName,
        isNearBy = isNearby
    )