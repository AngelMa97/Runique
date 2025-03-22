package com.angelme.run.location

import android.location.Location
import com.angelme.core.domain.location.LocationWithAltitude

fun Location.toLocationWithAltitude(): LocationWithAltitude {
    return LocationWithAltitude(
        location = com.angelme.core.domain.location.Location(
            lat = latitude,
            lng = longitude
        ),
        altitude = altitude
    )
}