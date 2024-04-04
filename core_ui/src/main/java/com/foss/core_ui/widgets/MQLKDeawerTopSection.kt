package com.foss.core_ui.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter

@Composable
fun MQLKDrawerTopSection(onCloseButtonClick: () -> Unit, imageUrl: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Surface(content = {})
        IconButton(
            modifier = Modifier.border(
                1.dp, Color.Black.copy(alpha = 0.1f), shape = CircleShape
            ), onClick = onCloseButtonClick
        ) {
            Icon(Icons.Default.Close, null)
        }
    }
    Spacer(modifier = Modifier.height(16.dp))

    Row {
        Image(
            painter = rememberAsyncImagePainter(imageUrl),
            contentDescription = null,
            modifier = Modifier
                .clip(CircleShape)
                .size(48.dp),
            contentScale = ContentScale.Crop

        )
        Spacer(modifier = Modifier.width(12.dp))
        Column {
            Text(
                "Demo user",
                style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.W600)
            )
            Text(
                "demouser@mail.com",
                style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.W500)

            )
        }
    }
    Spacer(modifier = Modifier.height(32.dp))
}