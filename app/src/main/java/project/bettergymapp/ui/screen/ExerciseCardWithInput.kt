package project.bettergymapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExerciseCardWithInput(
    initialText: String,
    focusRequester: FocusRequester,
    onTick: (String) -> Unit
) {
    val textState = remember { mutableStateOf(initialText) }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .height(60.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(color = Color(0xFFE0E0E0)),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = textState.value,
            onValueChange = { textState.value = it },
            textStyle = TextStyle(
                fontSize = 18.sp
            ),
            modifier = Modifier
                .padding(start = 20.dp, bottom = 8.dp)
                .weight(1f)
                .focusRequester(focusRequester),
            colors = OutlinedTextFieldDefaults.colors(unfocusedContainerColor = Color.Transparent)
        )
        Spacer(modifier = Modifier.weight(0.1f))
        IconButton(
            onClick = { onTick(textState.value) },
            modifier = Modifier
                .padding(end = 8.dp)
                .size(40.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Tick",
                tint = Color.Green
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExerciseCardWithInputPreview() {
    ExerciseCardWithInput(
        "Bench Press",
        focusRequester = FocusRequester(),
    ) {
        // Handle tick action
    }
}