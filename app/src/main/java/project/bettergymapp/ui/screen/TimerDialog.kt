package project.bettergymapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import project.bettergymapp.R

@Composable
fun TimerDialog(
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier,
    onSwitchChange: (Boolean) -> Unit,
    onTimeChange: (Int) -> Unit,
    initialTime: Int = 120,
    switchState: Boolean = true
) {

    var time by remember { mutableIntStateOf(initialTime) }
    var isSwitchChecked by remember { mutableStateOf(switchState) }

    val minutes = time / 60
    val seconds = time % 60
    val timeFormatted = String.format("%02d:%02d", minutes, seconds)

    Card(
        modifier = modifier
            .wrapContentSize()
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(
            Color.DarkGray // Match Timer background color
        )
    ) {
        Column(
            modifier = Modifier,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            // Header Row with Text and Switch
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Start timer on finish",
                    modifier = Modifier,
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 20.sp),
                    color = Color.White // Match Timer text color
                )
                Switch(
                    checked = isSwitchChecked,
                    onCheckedChange = {
                        onSwitchChange(it)
                        isSwitchChecked = it
                    },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .scale(0.75f), // Adjust the scale to make the switch smaller
                    colors = SwitchDefaults.colors(
                        checkedThumbColor = Color.Gray,
                        checkedTrackColor = Color.Green,
                    )
                )
            }

            // Time Picker Row
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround,
                verticalAlignment = Alignment.CenterVertically
            ) {
                TimerComponent(
                    time = timeFormatted,
                    onDecrement = {
                        time = (time - 15).coerceAtLeast(0)
                        onTimeChange(time)
                    },
                    onIncrement = {
                        time += 15
                        onTimeChange(time)
                    },
                )
            }
            // Dismiss Button
            Button(
                onClick = { onDismissRequest() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                colors = ButtonDefaults.buttonColors(
                    colorResource(id = R.color.happygreen) // Match Timer button color
                )
            ) {
                Text(
                    "Save", color = Color.Black,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                ) // Match Timer text color
            }
        }
    }

}

@Composable
fun TimerComponent(
    time: String = "02:00",
    onDecrement: () -> Unit = {},
    onIncrement: () -> Unit = {},
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(200.dp)
                .background(
                    color = Color.DarkGray, // Match Timer background color
                    shape = CircleShape
                )
                .border(
                    width = 4.dp,
                    color = colorResource(id = R.color.happygreen), // Match Timer border color
                    shape = CircleShape
                )
        ) {
            Text(
                text = time,
                style = MaterialTheme.typography.headlineSmall,
                color = Color.White // Match Timer text color
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            TextButton(onClick = onDecrement) {
                Text(
                    text = "-15s", color = colorResource(id = R.color.happygreen),
                    style = TextStyle(fontSize = 20.sp)
                ) // Match Timer button color
            }

            TextButton(onClick = onIncrement) {
                Text(
                    text = "+15s", color = colorResource(id = R.color.happygreen),
                    style = TextStyle(fontSize = 20.sp)
                ) // Match Timer button color
            }
        }
    }
}

@Preview
@Composable
fun TimerDialogPreview() {
    TimerDialog(
        onDismissRequest = {},
        onSwitchChange = {},
        onTimeChange = {}
    )
}