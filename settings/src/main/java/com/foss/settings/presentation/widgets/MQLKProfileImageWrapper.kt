package com.foss.settings.presentation.widgets

import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun MQLKProfileImageWrapper(uri: Uri?, width: Dp, onClick: () -> Unit) {

    Box(modifier = Modifier.clickable {
        onClick()
    }) {

        if (uri == null) {
            Surface(Modifier.size(width * .5f),
                shape = CircleShape,
                color = Color.Red,
                content = {})
        } else {
            AsyncImage(
                modifier = Modifier
                    .size(width * .5f)
                    .clip(CircleShape),
                model = uri,
                contentDescription = null,
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop,
            )
        }

        Surface(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .offset(x = (-10).dp),
            color = Color.White,
            shape = CircleShape,
            border = BorderStroke(
                width = 1.dp, color = Color.Black.copy(alpha = .5f)
            )
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(Icons.Default.Edit, null)
            }
        }
    }
}

