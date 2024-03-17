package cc.narui.easytodo


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import cc.narui.easytodo.EasyToDoApp
import cc.narui.easytodo.TodoViewModel

class MainActivity : ComponentActivity() {
    private val todoViewModel: TodoViewModel by lazy {
        ViewModelProvider(this).get(TodoViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            EasyToDoApp(todoViewModel = todoViewModel)
        }
    }
}

