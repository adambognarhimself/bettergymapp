package project.bettergymapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ExerciseCard(
    name: String,
) {
    Box(
        modifier = Modifier
            .padding(8.dp)
            .height(60.dp)
            .fillMaxWidth().
            clip(RoundedCornerShape(8.dp))
            .background(color = androidx.compose.ui.graphics.Color(0xFFE0E0E0)),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = name,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            ),
            modifier = Modifier.padding(start = 20.dp)
        )

    }
}


@Preview(showBackground = true)
@Composable
fun ExerciseCardPreview() {
    ExerciseCard("Bench Press")
}