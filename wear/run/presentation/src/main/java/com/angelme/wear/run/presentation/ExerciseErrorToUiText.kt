package com.angelme.wear.run.presentation

import com.angelme.core.presentation.ui.UiText
import com.angelme.wear.run.domain.ExerciseError

fun ExerciseError.toUiText(): UiText? {
    return when(this) {
        ExerciseError.TRACKING_NOT_SUPPORTED -> null
        ExerciseError.ONGOING_OWN_EXERCISE,
        ExerciseError.ONGOING_OTHER_EXERCISE-> {
            UiText.StringResource(R.string.error_ongoing_exercise)
        }
        ExerciseError.EXERCISE_ALREADY_ENDED -> {
            UiText.StringResource(R.string.error_exercise_already_ended)
        }
        ExerciseError.UNKNOWN -> UiText.StringResource(com.angelme.core.presentation.ui.R.string.error_unknown)
    }
}