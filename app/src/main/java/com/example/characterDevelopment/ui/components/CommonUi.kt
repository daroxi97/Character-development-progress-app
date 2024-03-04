package com.example.characterDevelopment.ui.components

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
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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

/**
 * Basic row with a title and content according to that title
 */
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
    AppUiDivider()
}

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

/**row with a title,  date, a number on the right (amount) and a set color. At the right has a delete icon
if pressed the icon calls removeCharacter function
 */

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
                    "$title name in $subtitle, value: $amount"
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
    AppUiDivider()
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

/**
 * Textfield row, can only set numbers
 */
@Composable
fun baseSimpleIntegersField(
    title: String,
    modifier: Modifier = Modifier,
    rowHeight: Dp = 68.dp

): String {
    var text by remember { mutableStateOf(TextFieldValue()) }
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
    AppUiDivider()
    return text.text
}

/**
 * Textfield standard  row
 */
@Composable
fun baseSimpleTextField(
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
    AppUiDivider()
    return text.text

}

/**
 *Textfield that can be clicked without calling the keyboard. If clicked use the clickAction function
 *instead of invoking keyboard. Can have or not an icon or a switch to the right
 */
@Composable
fun ClickableSimpleTextField(
    title: String,
    text: String,
    clickAction: () -> Unit,
    modifier: Modifier = Modifier,
    icon: ImageVector? = null,
    switch: Boolean = false,
    rowHeight: Dp = 68.dp,


    ) {
    var textFieldSize by remember { mutableStateOf(Size.Zero) }
    var selectedText by remember { mutableStateOf("") }
    var switchValue by remember { mutableStateOf(false) }

    //applies click action instead of call the keyboard
    val interactionSource = remember {
        object : MutableInteractionSource {
            override val interactions = MutableSharedFlow<Interaction>(
                extraBufferCapacity = 16,
                onBufferOverflow = BufferOverflow.DROP_OLDEST,
            )

            override suspend fun emit(interaction: Interaction) {
                if (interaction is PressInteraction.Release) {
                    clickAction()
                    switchValue = !switchValue
                }

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

        //removes the call to keyboard
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
                        textFieldSize = coordinates.size.toSize()
                    },
                readOnly = true,
                trailingIcon = {
                    icon?.let {
                        Icon(icon, "rowIcon",
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
    AppUiDivider()
}

/**
*Basic switch of the app. Uses ClickableSimpleTextField, but with this one will be easier to call.
*/
@Composable
fun baseSwitch(title: String): Boolean {

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
        AppUiDivider()
    }
    return selectedBoolean
}
/**
 * Basic confirmationDialog of the app. With a title, a subtitle and confirmation and dismiss actions.
 */
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
 * Basic rows divider of the app
 */
@Composable
fun AppUiDivider(modifier: Modifier = Modifier) {
    Divider(color = MaterialTheme.colors.background, thickness = 1.dp, modifier = modifier)
}

/**
 * Same as cliclableSimpleTextField, but when used, calls a confirmationDialog to apply or not the custom action
 * Has an arrow to the right, up or down depending to if the row is selected or not
 */
@Composable
fun ClickableSimpleTextFieldWithConfirmation(
    title: String,
    text: String,
    confirmationTitle: String,
    confirmationSubtitle: String,
    rowHeight: Dp = 68.dp,
    clickAction: () -> Unit = {},
) {

    var confirmDialog: Boolean by remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column() {

        ClickableSimpleTextField(
            title = title,
            text = text,
            clickAction = {
                confirmDialog = true
                expanded = !expanded
            },
            icon = icon,
            rowHeight = rowHeight,
            modifier = Modifier.height(rowHeight)
        )

        ConfirmationDialog(
            confirmationTitle,
            confirmationSubtitle,
            confirmDialog,
            {
                confirmDialog = false
                expanded = false
            },
            {
                clickAction()
                confirmDialog = false
                expanded = false

            })

        AppUiDivider()

    }
}