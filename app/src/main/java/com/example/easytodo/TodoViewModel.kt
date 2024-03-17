package cc.narui.easytodo

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel


class TodoViewModel( application: Application) : AndroidViewModel(application) {
    private val sharedPreferences: SharedPreferences by lazy {
        application.getSharedPreferences("todo_prefs", Context.MODE_PRIVATE)
    }

    val todoItems: MutableState<MutableList<TodoItem>> = mutableStateOf(loadTodoItems())

    fun addTodoItem(todoItem: TodoItem) {
        todoItems.value.add(todoItem)
        println(todoItems.value)
        saveTodoItems(todoItems.value)
    }

    fun deleteTodoItem(index: Int) {
        todoItems.value.removeAt(index)
        saveTodoItems(todoItems.value)
    }

    fun editTodoItem(index: Int, newTask: String) {
        todoItems.value[index].task = newTask
        saveTodoItems(todoItems.value)
    }

    fun moveTodoItem(oldIndex: Int, newIndex: Int) {
        val todoItem = todoItems.value.removeAt(oldIndex)
        todoItems.value.add(newIndex, todoItem)
        saveTodoItems(todoItems.value)
    }

    private fun loadTodoItems(): MutableList<TodoItem> {
        val tasks = sharedPreferences.getStringSet("tasks", setOf()) ?: setOf()
        return tasks.mapIndexed { index, task ->
            TodoItem(index, task)
        }.toMutableList()
    }

    private fun saveTodoItems(todoItems: List<TodoItem>) {
        val tasks = todoItems.map { it.task }.toSet()
        sharedPreferences.edit().putStringSet("tasks", tasks).apply()
    }
}