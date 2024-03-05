package com.example.characterDevelopment.ui.components

import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.example.characterDevelopment.R
import java.text.SimpleDateFormat
import java.util.Date
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BasicDatePickerDialog(
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

/**
 * Row that if is pressed calls a datePickerDialog to set a date
 */
@Composable
fun baseClickableRowDatePickerDialog(): String {

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
        BasicDatePickerDialog(
            onDateSelected = { date = it },
            onDismiss = { showDatePicker = false }
        )
    }
    return date
}

private fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}