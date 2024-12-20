package project.bettergymapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun AddExerciseDialog(
    onDismissRequest: () -> Unit,
    onConfirmation: (String) -> Unit,
) {
    val nameState = remember { mutableStateOf("") }

    Dialog(onDismissRequest = { onDismissRequest() }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .padding(16.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Add New Exercise",
                    modifier = Modifier.padding(10.dp),
                    style = TextStyle(fontSize = 23.sp),
                )
                TextField(
                    value = nameState.value,
                    onValueChange = { nameState.value = it },
                    modifier = Modifier.padding(16.dp),
                    label = { Text("Exercise Name") },
                    textStyle = TextStyle(fontSize = 20.sp) // Set the desired text size here
                )
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    TextButton(
                        onClick = { onDismissRequest() },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text(text = "Dismiss", style = TextStyle(fontSize = 16.sp), color = Color.Red)
                    }
                    TextButton(
                        onClick = { onConfirmation(nameState.value) },
                        modifier = Modifier.padding(8.dp),
                    ) {
                        Text(text = "Confirm", style = TextStyle(fontSize = 16.sp))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun AddExerciseDialogPreview() {
    AddExerciseDialog(
        onDismissRequest = {},
        onConfirmation = { _ -> },
    )
}