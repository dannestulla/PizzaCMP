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

@Composable
fun SizeSelector() {
    var selectedOption by remember { mutableIntStateOf(0) }

    Row(
        modifier = Modifier.padding(16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        SegmentedButton(
            options = listOf("Opção 1", "Opção 2", "Opção 3"),
            selectedOption = selectedOption,
            onOptionSelected = { selectedOption = it }
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
                    containerColor = if (selectedOption == index) MaterialTheme.colorScheme.tertiary else MaterialTheme.colorScheme.tertiaryContainer
                ),
                shape = when (index) {
                    0 -> MaterialTheme.shapes.small.copy(bottomEnd = ZeroCornerSize, topEnd = ZeroCornerSize)
                    options.size - 1 -> MaterialTheme.shapes.small.copy(bottomStart = ZeroCornerSize, topStart = ZeroCornerSize)
                    else -> RectangleShape
                },
                modifier = Modifier.weight(1f)
            ) {
                Text(option)
            }
        }
    }
}


