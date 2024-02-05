package com.foss.core_ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp

@Composable
fun MQLKSocialCard(painter: Painter, onClick: () -> Unit) {
    Surface(
        modifier = Modifier.clickable {
            onClick.invoke()
        },
        shape = RoundedCornerShape(15),
        color = MaterialTheme.colorScheme.surface,
        shadowElevation = 10.dp
    ) {
        Image(
            modifier = Modifier
                .padding(10.dp)
                .size(30.dp),
            painter = painter,
            contentDescription = null,
            contentScale = ContentScale.FillHeight,
        )
    }
}