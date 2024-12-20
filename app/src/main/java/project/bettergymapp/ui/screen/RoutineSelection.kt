package project.bettergymapp.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import project.bettergymapp.R
import project.bettergymapp.data.Routine
import project.bettergymapp.data.viewmodel.RoutineViewModel


@Composable
fun RoutineSelection(
    viewModel: RoutineViewModel = viewModel(factory = RoutineViewModel.Factory),
    onStart: (Routine) -> Unit = {}
) {
    val list = viewModel.list.collectAsStateWithLifecycle().value
    val colors = List(list.size) { index ->
        val colorResources = listOf(
            R.color.happyblue,
            R.color.happyyellow,
            R.color.happygreen,
            R.color.happypink,
            R.color.happyorange,
            R.color.happypurple
        )
        colorResource(id = colorResources[index % colorResources.size])
    }
    val addRoutine = remember { mutableStateOf(false) }

    Column(modifier = Modifier.padding(start = 10.dp, top = 20.dp, end = 10.dp)) {
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Text(
                text = stringResource(R.string.your_routines),
                color = Color.Black,
                modifier = Modifier.padding(top = 10.dp),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    fontSize = 25.sp
                )
            )
            TextButton(
                onClick = { addRoutine.value = true }
            ) {
                Text(text = "+", style = TextStyle(fontSize = 30.sp, color = Color.Black))
            }
        }

        if (list.isEmpty()) {
            EmptyRoutineList()
        } else {
            RoutineList(list = list, colors = colors, onStart = onStart)
        }

        if (addRoutine.value) {
            AddRoutineDialog(
                onDismissRequest = { addRoutine.value = false },
                onConfirmation = { name ->
                    if (name != "") {
                        viewModel.insert(Routine(name = name, description = ""))
                    }
                    addRoutine.value = false
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewEmptyRoutineList() {
    EmptyRoutineList()
}

