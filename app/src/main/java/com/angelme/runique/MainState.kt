package com.angelme.runique

data class MainState(
    val isLoggedIn: Boolean = false,
    val isCheckingAuth: Boolean = true,
    val showAnalyticsInstallDialog: Boolean = false
)
