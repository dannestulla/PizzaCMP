package br.gohan.pizzacmp.presenter.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.ZeroCornerSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import data.model.PizzaSize

@Composable
fun SizeSelector(sizes: (PizzaSize) -> Unit) {
    var selectedOption by remember { mutableIntStateOf(1) }

    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SegmentedButton(
            options = listOf(PizzaSize.Small.name, PizzaSize.Medium.name, PizzaSize.Large.name),
            selectedOption = selectedOption,
            onOptionSelected = {
                selectedOption = it
                sizes(PizzaSize.entries[it])
            }
        )
    }
}

@Composable
fun SegmentedButton(
    options: List<String>,
    selectedOption: Int,
    onOptionSelected: (Int) -> Unit
) {
    Row {
        options.forEachIndexed { index, option ->
            Button(
                onClick = { onOptionSelected(index) },
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (selectedOption == index) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.tertiaryContainer,
                    contentColor = if (selectedOption == index) MaterialTheme.colorScheme.onTertiary else MaterialTheme.colorScheme.onTertiaryContainer
                ),
                shape = when (index) {
                    0 -> MaterialTheme.shapes.small.copy(
                        bottomEnd = ZeroCornerSize,
                        topEnd = ZeroCornerSize
                    )

                    options.size - 1 -> MaterialTheme.shapes.small.copy(
                        bottomStart = ZeroCornerSize,
                        topStart = ZeroCornerSize
                    )

                    else -> RectangleShape
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(option)
            }
        }
    }
}

