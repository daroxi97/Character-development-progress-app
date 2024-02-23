/*
 * Copyright 2022 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.compose.rally.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.Interaction
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.AlertDialog
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.example.characterDevelopment.R
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import java.text.SimpleDateFormat
import java.util.Date

/**
 * A row representing the basic information of an Account.
 */


@Composable
fun SimpleRow(
    modifier: Modifier = Modifier,
    name: String,
    amount: String,
) {
    BaseSimpleRow(
        modifier = modifier,
        title = name,
        content = amount,
    )
}

/**
 * A row representing the basic information of a Bill.
 */
@Composable
fun CharacterRow(
    modifier: Modifier = Modifier,
    name: String,
    date: String,
    level: Int,
    color: Color,
    removeCharacter: () -> Unit = {}
) {
    BaseRow(
        modifier = modifier,
        color = color,
        title = stringResource(id = R.string.characterName) + ": $name",
        subtitle = stringResource(id = R.string.dateTitle) + ": $date",
        amount = stringResource(id = R.string.characterLevel) + ": ${level.toString()}",
        removeCharacter = removeCharacter
    )
}


@Composable
private fun BaseRow(
    modifier: Modifier = Modifier,
    color: Color,
    title: String,
    subtitle: String,
    amount: String,
    removeCharacter: () -> Unit = {}

) {
    Row(
        modifier = modifier
            .height(68.dp)
            .verticalScroll(rememberScrollState())
            .clearAndSetSemantics {
                contentDescription =
                    "$title name in $subtitle, value:  ${amount.toString()}"
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        val typography = MaterialTheme.typography
        AccountIndicator(
            color = color,
            modifier = Modifier
        )
        Spacer(Modifier.width(12.dp))
        Column(Modifier) {
            Text(
                text = title,
                style = typography.body1,
                modifier = Modifier.width(180.dp),
                maxLines = 2
            )
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = subtitle, style = typography.subtitle1)
            }
        }
        Spacer(Modifier.weight(1f))
        Row(
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = amount,
                style = typography.body1,
                modifier = Modifier.align(Alignment.CenterVertically)
            )
        }
        Spacer(Modifier.width(16.dp))

        CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
            Icon(
                imageVector = Icons.Filled.Delete,
                contentDescription = "Delete character button",
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(24.dp)
                    .clickable {
                        removeCharacter()
                    }
            )
        }
    }
    RallyDivider()
}


@Composable
private fun BaseSimpleRow(
    modifier: Modifier = Modifier,
    title: String,
    content: String,
) {
    Row(
        modifier = modifier
            .height(68.dp)
            .verticalScroll(rememberScrollState())
            .clearAndSetSemantics {
                contentDescription =
                    "$title : $content"
            },
        verticalAlignment = Alignment.CenterVertically
    ) {
        val typography = MaterialTheme.typography

        Spacer(Modifier.width(12.dp))
        Text(text = title, style = typography.body1)

        Spacer(Modifier.weight(1f))
        Text(
            text = content,
            style = typography.h6,
            modifier = Modifier.align(Alignment.CenterVertically)
        )

        Spacer(Modifier.width(16.dp))

    }
    RallyDivider()
}

@Composable
fun BaseSimpleIntegersField(
    title: String,
    modifier: Modifier = Modifier,
    rowHeight: Dp = 68.dp

): String {
    var text by remember { mutableStateOf(TextFieldValue()) }
    val typography = MaterialTheme.typography

    Row(
        modifier = modifier
            .height(rowHeight)
            .verticalScroll(rememberScrollState())
            .clearAndSetSemantics {
                contentDescription =
                    "$title textfield"
            },
        verticalAlignment = Alignment.CenterVertically
    ) {


        OutlinedTextField(value = text, onValueChange = {
            text = it
        },

            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier.fillMaxWidth(),


            label = { Text(text = title) })


    }

    return text.text

    RallyDivider()
}


@Composable
fun BaseSimpleTextField(
    title: String,
    modifier: Modifier = Modifier,
    rowHeight: Dp = 68.dp
): String {
    var text by remember { mutableStateOf(TextFieldValue()) }

    Row(
        modifier = Modifier
            .height(rowHeight)
            .clickable { }
            .verticalScroll(rememberScrollState())
            .clearAndSetSemantics {
                contentDescription =
                    "$title textfield"
            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        OutlinedTextField(modifier = modifier
            .fillMaxWidth()
            .height(rowHeight),
            value = text,
            onValueChange = {
                text = it
            },
            singleLine = true,
            label = { Text(text = title) })

    }
    return text.text

    RallyDivider()
}


@Composable
fun ClickableSimpleTextField(
    title: String,
    text: String,
    clickAction: () -> Unit,
    icon: ImageVector? = null,
    switch: Boolean = false,
    modifier: Modifier = Modifier,
    rowHeight: Dp = 68.dp,


    ) {
    var textfieldSize by remember { mutableStateOf(Size.Zero) }
    var selectedText by remember { mutableStateOf("") }
    var switchValue by remember { mutableStateOf(false) }


    val interactionSource = remember {
        object : MutableInteractionSource {
            override val interactions = MutableSharedFlow<Interaction>(
                extraBufferCapacity = 16,
                onBufferOverflow = BufferOverflow.DROP_OLDEST,
            )

            override suspend fun emit(interaction: Interaction) {
                if (interaction is PressInteraction.Release) {
                    clickAction()
                    switchValue = !switchValue                }

                interactions.emit(interaction)
            }

            override fun tryEmit(interaction: Interaction): Boolean {
                return interactions.tryEmit(interaction)
            }
        }
    }



    Row(
        modifier = Modifier
            .height(rowHeight)
            .verticalScroll(rememberScrollState())
            .clearAndSetSemantics {
                contentDescription =
                    "$title textfield"
            },
        verticalAlignment = Alignment.CenterVertically
    ) {


        CompositionLocalProvider(LocalTextInputService provides null) {

            OutlinedTextField(interactionSource = interactionSource, value = text, onValueChange = {
                selectedText = it
            },
                modifier = modifier
                    .fillMaxWidth()

                    .clickable(interactionSource = interactionSource, indication = null, onClick = {

                    })
                    .onGloballyPositioned { coordinates ->
                        //This value is used to assign to the DropDown the same width
                        textfieldSize = coordinates.size.toSize()
                    },
readOnly = true,
                trailingIcon = {
                    icon?.let {
                        Icon(icon, "contentDescription",
                            Modifier.clickable { clickAction() })
                    }
                    if (switch) {

                        Switch(
                            checked = switchValue,
                            onCheckedChange = {
                                switchValue = it
                                clickAction()
                            },
                        )
                    }
                },
                label = { Text(text = title) })
        }
    }
    RallyDivider()
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BaseDatePickerDialog(
    onDateSelected: (String) -> Unit,
    onDismiss: () -> Unit
) {
    val initialDate: Long = System.currentTimeMillis()

    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = initialDate,
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis <= System.currentTimeMillis()
            }
        })

    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            Button(onClick = {
                onDateSelected(selectedDate)
                onDismiss()
            }

            ) {
                Text(text = stringResource(id = R.string.confirm), color = Color.White)
            }
        },
        dismissButton = {
            Button(onClick = {
                onDismiss()
            }) {
                Text(text = stringResource(id = R.string.cancel), color = Color.White)
            }
        }
    ) {
        DatePicker(
            state = datePickerState
        )
    }

}


@Composable
fun BaseSwitch(title: String): Boolean {

    var selectedBoolean by remember { mutableStateOf(false) }



    Column() {

        ClickableSimpleTextField(
            title = title,
            text = "",
            clickAction = {
                selectedBoolean = !selectedBoolean
            },
            switch = true,
            modifier = Modifier
        )


        RallyDivider()


    }
    return selectedBoolean
}

@Composable
fun BaseDatePickerDialog(): String {

    var date by remember {
        mutableStateOf("")
    }

    var showDatePicker by remember {
        mutableStateOf(false)
    }

    ClickableSimpleTextField(
        title = stringResource(id = R.string.dateTitle),
        text = date,
        clickAction = {
            showDatePicker = true
        }
    )



    if (showDatePicker) {
        BaseDatePickerDialog(
            onDateSelected = { date = it },
            onDismiss = { showDatePicker = false }
        )
    }
    return date.toString()
}


@Composable
fun ConfirmationDialog(
    title: String,
    subtitle: String,
    showDialog: Boolean,
    onDismiss: () -> Unit,
    onConfirm: () -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {
                TextButton(onClick = onConfirm) {
                    Text(
                        text = stringResource(id = R.string.confirm)
                    )
                }
            },
            dismissButton = {
                TextButton(onClick = onDismiss) {
                    Text(text = stringResource(id = R.string.cancel))
                }
            },
            title = { Text(title) },
            text = { Text(subtitle) }
        )
    }
}


/**
 * A vertical colored line that is used in a [BaseRow] to differentiate accounts.
 */
@Composable
private fun AccountIndicator(color: Color, modifier: Modifier = Modifier) {
    Spacer(
        modifier
            .size(4.dp, 36.dp)
            .background(color = color)
    )
}

@Composable
fun RallyDivider(modifier: Modifier = Modifier) {
    Divider(color = MaterialTheme.colors.background, thickness = 1.dp, modifier = modifier)
}


private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy")
    return formatter.format(Date(millis))
}
