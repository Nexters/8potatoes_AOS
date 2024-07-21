package com.eight_potato.designsystem.input

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
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
    cursorColor: Color = Colors.Gray500,
    imeAction: ImeAction = ImeAction.Done,
    hint: String?= null,
    value: String,
    onValueChanged: (String) -> Unit,
    onClear: () -> Unit
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

    Row (
        modifier = modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Colors.Gray450,
                shape = RoundedCornerShape(5.dp)
            ),
        verticalAlignment = Alignment.CenterVertically
    ){
        TextField(
            modifier = Modifier.weight(1f),
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
            colors = TextFieldDefaults.textFieldColors(
                cursorColor = cursorColor,
                backgroundColor = Color.Unspecified,
                focusedIndicatorColor = Color.Unspecified,
                errorIndicatorColor = Color.Unspecified,
                disabledIndicatorColor = Color.Unspecified,
                unfocusedIndicatorColor = Color.Unspecified
            ),
            keyboardOptions = KeyboardOptions(imeAction = imeAction),
            value = value,
            onValueChange = onValueChanged
        )
        Spacer(modifier = Modifier.width(4.dp))
        if (value.isNotEmpty()) {
            Icon(
                modifier = Modifier.clickable { onClear() },
                imageVector = Icons.Default.Clear,
                contentDescription = "검색 키워드 지우기"
            )
        }
    }
}