package com.eight_potato.designsystem.input

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.theme.Colors
import kotlinx.coroutines.delay

@Composable
@OptIn(ExperimentalComposeUiApi::class)
fun HyusikOutlinedTextField(
    modifier: Modifier = Modifier,
    autoFocus: Boolean = false,
    isSingleLine: Boolean = true,
    maxLength: Int = 1,
    textStyle: TextStyle = MaterialTheme.typography.body1,
    cursorColor: Color = Colors.Sub2100,
    imeAction: ImeAction = ImeAction.Done,
    hint: String?= null,
    value: String,
    onValueChanged: (String) -> Unit,
    trailingIcon: @Composable () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequest = remember { FocusRequester() }

    LaunchedEffect(key1 = autoFocus) {
        if (autoFocus) {
            focusRequest.requestFocus()
            delay(200)
            keyboardController?.show()
        }
    }

    OutlinedTextField(
        modifier = modifier
            .focusRequester(focusRequest)
            .fillMaxWidth()
            .padding(12.dp),
        shape = RoundedCornerShape(12.dp),
        maxLines = maxLength,
        singleLine = isSingleLine,
        textStyle = textStyle,
        placeholder = {
            hint?.let {
                Text(
                    text = it,
                    color = Colors.Gray600
                )
            }
        },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            cursorColor = cursorColor,
            backgroundColor = Color.Unspecified,
            focusedBorderColor = Colors.Sub2100,
            unfocusedBorderColor = Colors.Sub2100
        ),
        keyboardOptions = KeyboardOptions(imeAction = imeAction),
        value = value,
        onValueChange = onValueChanged,
        trailingIcon = trailingIcon,
        leadingIcon = {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Default.Search,
                contentDescription = "",
                tint = Colors.Blk60
            )
        }
    )
}