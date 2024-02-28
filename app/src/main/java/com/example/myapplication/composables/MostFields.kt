package com.example.myapplication.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Preview(showSystemUi = true, showBackground = true)
@Composable
fun MostFields() {
    var name by remember {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center
    ) {
        BasicTextView(
            modifier = Modifier.fillMaxWidth(),
            value = name,
            onValueChange = { name = it }
        )
    }
}


@Composable
fun BasicTextView(
    value: String,
    onValueChange: (String) -> Unit,
    textAlign: TextAlign = TextAlign.Start,
    modifier: Modifier = Modifier,
    maxLength: Int = 35,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    isEditable: Boolean = true
) {

    Box(modifier = modifier) {
        CustomTextField(
            textAlign = textAlign,
            maxLength = maxLength,
            keyboardOptions = keyboardOptions,
            text = value,
            onValueChange = onValueChange,
            trailingIcon = null,
            placeholderText = "",
            modifier = Modifier
                .background(
                    Color.Gray,
                    RoundedCornerShape(percent = 12)
                )
                .padding(vertical = 4.dp, horizontal = 18.dp)
                .height(30.dp),
            fontSize = 14.sp,
            isEditable = isEditable
        )
    }
}



@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CustomTextField(
    maxLength: Int = 20,
    text: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    leadingIcon: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null,
    placeholderText: String = "Placeholder",
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    fontSize: TextUnit = MaterialTheme.typography.bodySmall.fontSize,
    textAlign: TextAlign = TextAlign.Start,
    isEditable: Boolean = true
) {

    val (focusRequester) = FocusRequester.createRefs()
    val keyboardController = LocalSoftwareKeyboardController.current
    BasicTextField(
        readOnly = !isEditable,
        textStyle = TextStyle(textAlign = textAlign),
        keyboardOptions = keyboardOptions,
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
        ),
        value = text,
        onValueChange = {
            if (isEditable) {
                if (it.length <= maxLength) onValueChange(it)
            }
        },
        modifier = Modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .background(
                MaterialTheme.colorScheme.surface,
                MaterialTheme.shapes.small,
            ),
        singleLine = true,
        decorationBox = { innerTextField ->
            Row(
                modifier,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (leadingIcon != null) leadingIcon()
                Box(Modifier.weight(1f)) {
                    if (text.isEmpty()) Text(
                        placeholderText,
                        style = LocalTextStyle.current.copy(
                            color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.3f),
                            fontSize = fontSize
                        )
                    )
                    innerTextField()
                }
                if (trailingIcon != null) trailingIcon()
            }
        }
    )
}