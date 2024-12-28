package project.bettergymapp.ui.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import project.bettergymapp.data.Exercise

@Composable
fun ExerciseScreen(
    added: (Exercise) -> Unit = {},
    onNavigateBack: () -> Unit = {}

) {
    var searchQuery by remember { mutableStateOf(TextFieldValue("")) }
    val exercises by remember { mutableStateOf(listOf<Exercise>()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        RoutineEditHeader(onNavigateBack = {
            onNavigateBack()
        })
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = searchQuery,
            onValueChange = { searchQuery = it },
            label = { Text("Search") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search Icon"
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            shape = RoundedCornerShape(16.dp),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = MaterialTheme.colorScheme.primary,
                unfocusedBorderColor = Color.Gray,
                cursorColor = MaterialTheme.colorScheme.primary
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        LazyColumn { // This is a vertically scrolling list.
            items(exercises.size) { exercise ->
                ExerciseCard(exercises[exercise].name)
            }
        }
    }
}

@Preview
@Composable
fun ExerciseScreenPreview() {
    ExerciseScreen()
}