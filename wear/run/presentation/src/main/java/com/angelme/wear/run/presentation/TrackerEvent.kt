package com.angelme.wear.run.presentation

sealed interface TrackerEvent {
    data object RunFinished: TrackerEvent
}