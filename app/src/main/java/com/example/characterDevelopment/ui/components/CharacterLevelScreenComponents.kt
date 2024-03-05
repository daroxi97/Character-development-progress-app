package com.example.characterDevelopment.ui.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * Generic component used by the character level screen. Shows a circle filled depending on the relation between the circleValue and the maxValue. Then add custom rows
 */
@Composable
fun CharacterInformationBody(
    modifier: Modifier = Modifier,
    circleValue: Int,
    colorsCircle: List<Color>,
    circleMaxNumber: Float,
    circleLabel: String,
    rows: @Composable () -> Unit

) {
    //calculate the completed % of the circle
    val value = circleValue / circleMaxNumber
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        Box(Modifier.padding(16.dp)) {
            AnimatedCircle(
                listOf(1 - value, value),
                colorsCircle,
                Modifier
                    .height(300.dp)
                    .align(Alignment.Center)
                    .fillMaxWidth()
            )
            Column(modifier = Modifier.align(Alignment.Center)) {
                //The title of the circle
                Text(
                    text = circleLabel,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)

                )
                //The numerical value that represents the circle
                Text(
                    text = circleValue.toString(),
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
        }
        Spacer(Modifier.height(10.dp))
        Card(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
            )
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                //All the content you want to show after the circle
                rows()
            }
        }
    }
}
