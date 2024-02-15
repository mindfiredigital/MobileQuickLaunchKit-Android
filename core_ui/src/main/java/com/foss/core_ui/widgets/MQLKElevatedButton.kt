package com.foss.core_ui.widgets

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MQLKElevatedButton(
    name: String, onClick: () -> Unit,
) {

    ElevatedButton(
        onClick = onClick,
        elevation = ButtonDefaults.elevatedButtonElevation(
            defaultElevation = 0.dp,
            pressedElevation = 10.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable( // Add clickable modifier
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(
                    color = Color.DarkGray
                ) // Use rememberRipple() for ripple effect
            ) {},
        shape = RoundedCornerShape(15),
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = Color.White
        ),
    ) {
        Text(
            name,
            color = Color.White,
            style = TextStyle(fontSize = 15.sp, fontWeight = FontWeight.W700)
        )
    }

}


@Preview(backgroundColor = 0xffffff, showBackground = true, widthDp = 300, heightDp = 100)
@Composable
fun PreviewMFMCElevatedButton() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) {
        MQLKElevatedButton(
            name = "Login",
            onClick = {}

        )
    }

}