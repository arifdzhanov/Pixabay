package com.marlen.pixabay.presentation.search.component

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.text.input.VisualTransformation
import com.marlen.pixabay.presentation.components.GifIcons
import com.marlen.pixabay.presentation.components.IcClose
import com.marlen.pixabay.presentation.components.IcSearch
import com.marlen.pixabay.presentation.res.Dimens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchView(
    modifier: Modifier,
    placeholder: String,
    value: String,
    onValueChange: (filterText: String) -> Unit,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    clearClick: (() -> Unit)? = null,
) {
    val focusState = remember { mutableStateOf(false) }

    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .onFocusChanged {
                focusState.value = it.isFocused
            },
        keyboardOptions = keyboardOptions.copy(
            capitalization = KeyboardCapitalization.Words,
        ),
        keyboardActions = keyboardActions,
        textStyle = TextStyle.Default
            .copy(
                color = MaterialTheme.colorScheme.onBackground
            ),
        singleLine = true,
        decorationBox = { innerTextField ->
            TextFieldDefaults.DecorationBox(
                value = value,
                visualTransformation = VisualTransformation.None,
                innerTextField = innerTextField,
                singleLine = true,
                enabled = true,
                placeholder = {
                    Text(
                        text = placeholder,
                        modifier = Modifier.fillMaxWidth(),
                    )
                },
                interactionSource = remember { MutableInteractionSource() },
                trailingIcon = {
                    AnimatedContent(
                        targetState = value.isNotEmpty(),
                        transitionSpec = {
                            fadeIn(animationSpec = tween(durationMillis = 300)) togetherWith
                                    fadeOut(animationSpec = tween(durationMillis = 300))
                        }, label = ""
                    ) {
                        if (it) {
                            IconButton(
                                onClick = {
                                    clearClick?.let { clearClick() }
                                },
                            ) {
                                GifIcons.IcClose(
                                    modifier = Modifier.size(Dimens.iconNormal),
                                    tint = MaterialTheme.colorScheme.primary,
                                )
                            }
                        } else {
                            GifIcons.IcSearch(
                                modifier = Modifier.size(Dimens.iconNormal),
                                tint = if (!focusState.value) MaterialTheme.colorScheme.primary.copy(alpha = 0.4f) else MaterialTheme.colorScheme.primary,
                            )
                        }
                    }

                },
                shape = RoundedCornerShape(50f),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    unfocusedContainerColor = MaterialTheme.colorScheme.secondaryContainer,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    focusedTextColor = MaterialTheme.colorScheme.onPrimary,
                    unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
                ),
//                contentPadding = contentPadding,
            )
        },
    )
}