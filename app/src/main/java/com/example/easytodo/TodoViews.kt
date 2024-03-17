package cc.narui.easytodo

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EasyToDoApp(todoViewModel: TodoViewModel) {
    val todoItems by todoViewModel.todoItems

    var text by remember { mutableStateOf("") }
    var selectedIndex by remember { mutableStateOf(-1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = text,
            onValueChange = { text = it },
            label = { Text("Enter your task") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        )

        Row(
            horizontalArrangement = Arrangement.End,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(
                onClick = {
                    if (text.isNotEmpty()) {
                        todoViewModel.addTodoItem(TodoItem(todoItems.size, text))
                        text = ""
                    }
                },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text("Add Task")
            }

            Button(
                onClick = {
                    if (selectedIndex != -1) {
                        todoViewModel.deleteTodoItem(selectedIndex)
                        selectedIndex = -1
                    }
                },
                modifier = Modifier.padding(end = 8.dp)
            ) {
                Text("Delete Task")
            }

            Button(
                onClick = {
                    if (selectedIndex != -1 && text.isNotEmpty()) {
                        todoViewModel.editTodoItem(selectedIndex, text)
                        text = ""
                    }
                }
            ) {
                Text("Edit Task")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Column {
            todoItems.forEachIndexed { index,  todo ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable {
                            selectedIndex = index
                            text = todo.task
                        }
                ) {
                    RadioButton(
                        selected = index == selectedIndex,
                        onClick = null,
                        modifier = Modifier.padding(end = 8.dp)
                    )
                    Text(todo.task)
                }
            }
        }
    }
}
