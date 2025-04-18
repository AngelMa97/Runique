package com.angelme.wear.run.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.wear.compose.material3.MaterialTheme
import androidx.wear.compose.material3.Text
import androidx.wear.compose.ui.tooling.preview.WearPreviewDevices
import com.angelme.core.presentation.designsystem_wear.RuniqueTheme

@Composable
fun RunDataCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier,
    valueTextColor: Color = MaterialTheme.colorScheme.onSurface
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(15.dp))
            .background(MaterialTheme.colorScheme.surfaceContainer)
            .padding(8.dp)
    ) {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            fontSize = 10.sp
        )
        Text(
            text = value,
            color = valueTextColor,
            fontSize = 12.sp,
            overflow = Ellipsis,
            maxLines = 1
        )
    }
}