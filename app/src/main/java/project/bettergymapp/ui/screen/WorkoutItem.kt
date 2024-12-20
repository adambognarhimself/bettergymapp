package project.bettergymapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import project.bettergymapp.R
import project.bettergymapp.data.Exercise
import project.bettergymapp.data.ExerciseLog

@Composable
fun WorkoutItem(
    exercise: Exercise,
    onUpdate: (Exercise) -> Unit = {}
) {

    var logs by remember { mutableStateOf(exercise.lastLog?.sets?.toList() ?: listOf()) }
    val newLogs by remember { mutableStateOf(mutableListOf<Pair<Int, Int>>()) }

    if (logs.isEmpty()) {
        logs = List(3) { 0 to 0 }
    } else if (logs.size < 3) {
        logs = logs + List(3 - logs.size) { 0 to 0 }
    }


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 10.dp, start = 10.dp, end = 10.dp)
            .clip(shape = RoundedCornerShape(20.dp))
            .background(colorResource(id = R.color.top_app_bar))

    ) {
        Text(
            text = exercise.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, bottom = 10.dp),
            style = TextStyle(
                fontSize = 20.sp,
                fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                color = Color.Black
            ),
            textAlign = TextAlign.Center
        )



        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding( bottom = 18.dp)
        ) {
            Text(text = "SET", modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text(text = "Previous", modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text(text = "KG", modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text(text = "REPS", modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
            Text(text = "tick", modifier = Modifier.weight(1f), textAlign = TextAlign.Center)
        }

        logs.forEachIndexed { index, log ->
            DataRow(
                setNumber = index + 1,
                previous = log,
                onTick = { kg, reps, isChecked ->
                    if (isChecked) {
                        val kgValue = if (kg.isEmpty()) log.second else kg.toInt()
                        val repsValue = if (reps.isEmpty()) log.first else reps.toInt()
                        newLogs.add(repsValue to kgValue)
                    } else {
                        newLogs.removeIf { it.first == log.first && it.second == log.second }
                    }
                    val updatedExercise = exercise.copy(lastLog = ExerciseLog(sets = newLogs.toList()))
                    onUpdate(updatedExercise)
                }
            )
        }


        TextButton(
            onClick = {
                logs = logs + List(1) { 0 to 0 }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Add Set")
        }
    }
}

@Composable
private fun DataRow(
    setNumber: Int,
    previous: Pair<Int, Int>,
    onTick: (String, String, Boolean) -> Unit,
) {

    var kgInput by remember { mutableStateOf("") }
    var repsInput by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = setNumber.toString(),
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center
        )
        Text(
            text = "${previous.second}kg x ${previous.first}",
            modifier = Modifier.weight(1f),
            textAlign = TextAlign.Center,
            style = TextStyle(color = Color.Gray)

        )
        PlaceholderTextField(
            value = kgInput,
            onValueChange = { kgInput = it },
            placeholder = previous.second.toString(),
            modifier = Modifier.weight(1f)
        )
        PlaceholderTextField(
            value = repsInput,
            onValueChange = { repsInput = it },
            placeholder = previous.first.toString(),
            modifier = Modifier.weight(1f)
        )
        Checkbox(
            checked = isChecked,
            onCheckedChange = {
                isChecked = it
                onTick(kgInput, repsInput, isChecked)

            },
            colors = CheckboxDefaults.colors(
                checkedColor = Color.Green,
                uncheckedColor = Color.Gray
            ),
            modifier = Modifier
                .weight(1f)
                .height(24.dp)
        )
    }
}

@Composable
fun PlaceholderTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle(fontSize = 16.sp, textAlign = TextAlign.Center)
) {
    Box(modifier = modifier.wrapContentSize(),
        contentAlignment = Alignment.Center) {
        if (value.isEmpty()) {
            Text(
                text = placeholder,
                style = textStyle.copy(color = Color.Gray),

            )
        }
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = textStyle,
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WorkoutItemPreview() {
    WorkoutItem(
        exercise = Exercise(
            name = "Exercise 1",
            lastLog = ExerciseLog(sets = listOf(10 to 100, 9 to 80, 8 to 70))))
}
