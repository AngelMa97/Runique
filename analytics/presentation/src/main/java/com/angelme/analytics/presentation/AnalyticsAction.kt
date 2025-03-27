package com.angelme.analytics.presentation

sealed interface AnalyticsAction {
    data object OnBackClick: AnalyticsAction
}