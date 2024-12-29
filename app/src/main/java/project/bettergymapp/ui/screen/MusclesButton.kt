package project.bettergymapp.ui.screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import project.bettergymapp.R

@Composable
fun MusclesButton(
    name: String,
    onClick: () -> Unit = {},
    isSelected: Boolean = false
) {
    var color by remember { mutableStateOf(if (isSelected) Color.Green else Color(0xFFE0E0E0)) }

    ElevatedButton(
        onClick = {
            color = if (color == Color.Green) Color(0xFFE0E0E0) else Color.Green
            onClick()
        },
        colors = ButtonDefaults.elevatedButtonColors(
            containerColor = color
        ),
        contentPadding = PaddingValues(0.dp), // Remove padding
        modifier = Modifier.padding(4.dp)
    ) {
        Text(
            name,
            style = TextStyle(
                fontSize = 12.sp
            ),
            color = colorResource(id = R.color.happyblue),
            modifier = Modifier.padding(0.dp) // Remove padding
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MusclesButtonPreview() {
    MusclesButton(
        "Chest"
    )
}