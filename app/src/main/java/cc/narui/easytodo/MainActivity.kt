package cc.narui.easytodo


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider

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

