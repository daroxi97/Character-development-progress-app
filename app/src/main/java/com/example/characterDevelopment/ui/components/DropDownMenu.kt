package com.example.characterDevelopment.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


//A row with a title and content. If presses appears a dropdown with different elements. The selected one will be the content
@Composable
fun dropDownMenuRow(
    title: String, elements: List<String>, rowHeight: Dp = 68.dp
): String {

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }

    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column() {

        ClickableSimpleTextField(
            title = title,
            text = selectedText,
            clickAction = { expanded = !expanded },
            icon = icon,
            rowHeight = rowHeight,
            modifier = Modifier.height(rowHeight)
        )


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            elements.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
        AppUiDivider()


    }
    return selectedText

}

//Same as the last one, but this time will have a confirmation dialog.
@Composable
fun dropDownMenuRowWithConfirmation(
    title: String,
    elements: List<String>,
    confirmationTitle: String,
    confirmationSubtitle: String,
    rowHeight: Dp = 68.dp,
    clickAction: () -> Unit = {},
    selectedLabel: String = ""
): String {

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(selectedLabel) }
    var confirmDialog: Boolean by remember { mutableStateOf(false) }

    var savedLabel: String by remember { mutableStateOf("") }


    val icon = if (expanded)
        Icons.Filled.KeyboardArrowUp
    else
        Icons.Filled.KeyboardArrowDown


    Column() {

        ClickableSimpleTextField(
            title = title,
            text = selectedLabel,
            clickAction = { expanded = !expanded },
            icon = icon,
            rowHeight = rowHeight,
            modifier = Modifier.height(rowHeight)
        )

        ConfirmationDialog(
            confirmationTitle,
            confirmationSubtitle,
            confirmDialog,
            { confirmDialog = false },
            {
                selectedText = savedLabel
                clickAction()
                confirmDialog = false
            })


        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
        ) {
            elements.forEach { label ->
                DropdownMenuItem(onClick = {
                    confirmDialog = true
                    savedLabel = label
                    expanded = false
                }) {
                    Text(text = label)
                }
            }
        }
        AppUiDivider()


    }
    return selectedText

}

//An image that if is pressed show a dropdown with different options
@Composable
fun dropDownMenuIcon(
    image: ImageVector,
    elements: List<String>,
    clickAction: (value: String) -> Unit
): String {

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }



    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Top
    ) {
        Box {

            Icon(
                imageVector = image,
                contentDescription = null,
                modifier = Modifier
                    .padding(end = 12.dp)
                    .size(24.dp)
                    .clickable {
                        expanded = !expanded
                    }
            )

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
                modifier = Modifier.width(IntrinsicSize.Min)


            ) {
                elements.forEach { label ->
                    DropdownMenuItem(onClick = {
                        selectedText = label
                        expanded = false
                        clickAction(selectedText)
                    }) {
                        Text(text = label)
                    }

                }
            }
        }

    }
    return selectedText

}
