package com.foss.core_ui.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.foss.core_ui.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MQLKOutlinedTextField(
    placeHolder: String,
    onChange: (String) -> Unit,
    value: String,
    isError: Boolean,
    errorText: String,
    isMask: Boolean = false,
    passwordVisibilityState: Boolean = true,
    leadingIcon: @Composable() () -> Unit,

    ) {
    var passwordVisibility by rememberSaveable { mutableStateOf(passwordVisibilityState) }

    Column(
        modifier = Modifier
            .padding(
                bottom = if (isError) {
                    0.dp
                } else {
                    8.dp
                }
            )
    ) {
        OutlinedTextField(
            value = value,
            onValueChange = onChange,
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.colorScheme.onPrimary
            ),
            visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(15),
            placeholder = {
                Text(
                    placeHolder,
                    style = MaterialTheme.typography.bodyLarge.copy(
                        color = MaterialTheme.colorScheme.onTertiary
                    ),
                )
            },
            leadingIcon = {
                leadingIcon()
            },
            trailingIcon = {
                if (isMask) {
                    if (passwordVisibility) {
                        IconButton(
                            onClick = {
                                passwordVisibility = !passwordVisibility

                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_visibility_off),
                                null
                            )
                        }

                    } else {
                        IconButton(
                            onClick = {
                                passwordVisibility = !passwordVisibility

                            }
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_visibility),
                                null
                            )
                        }


                    }

                }
            },
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                unfocusedLeadingIconColor = MaterialTheme.colorScheme.onTertiary,
            ),
            isError = isError,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),

            )
        if (isError) {
            Text(
                text = errorText,
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelMedium,
                modifier = Modifier.padding(start = 16.dp, top = 8.dp)
            )
        }
    }
}


@Preview(backgroundColor = 0xffffff, showBackground = true, widthDp = 300, heightDp = 100)
@Composable
fun PreviewMFMKTextField() {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) {
        MQLKOutlinedTextField(
            "Demo test",
            value = "sdfsdf",
            leadingIcon = { Icon(Icons.Default.Person, null) },
            onChange = {},
            isError = true,
            errorText = "Fdsfdsfsdfsd",
        )
    }

}