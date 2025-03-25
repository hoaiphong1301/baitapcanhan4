package com.example.bai2


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.TextStyle

//import com.example.smarttasks.R

// Task data class

data class Task(
    val title: String,
    val description: String,
    val status: String,
    val category: String,
    var isCompleted: Boolean = false,
    val subtasks: MutableList<Subtask> = mutableListOf()
)

data class Subtask(var text: String, var isChecked: Boolean = false)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TaskApp()
        }
    }
}
//@Preview(showBackground = true)
@Composable
fun TaskApp() {
    var tasks by remember { mutableStateOf(listOf<Task>()) }
    var selectedTask by remember { mutableStateOf<Task?>(null) }

    if (selectedTask != null) {
        TaskDetailScreen(task = selectedTask!!, onBack = { selectedTask = null })
    } else {
        if (tasks.isEmpty()) {
            EmptyScreen { tasks = tasks + Task("Complete Android Project", "Finish UI, integrate API, and write documentation", "In Progress", "Work") }
        } else {
            HomeScreen(tasks, onTaskClick = { selectedTask = it }, onAddTask = {
                tasks = tasks + Task("New Task", "Task description", "Pending", "General")
            })
        }
    }
}

@Composable
fun HomeScreen(tasks: List<Task>, onTaskClick: (Task) -> Unit, onAddTask: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.TopCenter) {
            Image(
                painter = painterResource(id = R.drawable.uth),
                contentDescription = "Logo",
                alignment = Alignment.TopEnd,
                modifier = Modifier.height(80.dp).width(380.dp)
            )
        }
        //Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier.weight(1f).padding(bottom = 32.dp).height(300.dp)) {
            LazyColumn(modifier = Modifier.fillMaxSize().padding(horizontal = 16.dp)) {
                items(tasks) { task ->
                    TaskItem(task, onClick = { onTaskClick(task) })
                }
            }
        }

        Box(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp), contentAlignment = Alignment.BottomEnd) {
            IconButton(onClick = onAddTask) {
                Image(
                    painter = painterResource(id = R.drawable.action_button),
                    contentDescription = "Add Task",
                    alignment = Alignment.BottomEnd,
                    modifier = Modifier.width(1000.dp).height(200.dp)
                )

            }
        }
    }
}


@Composable
fun TaskItem(task: Task, onClick: () -> Unit) {
    var isChecked by remember {  mutableStateOf<Boolean>(task.isCompleted) }

    Card(
        modifier = Modifier.fillMaxWidth().padding(8.dp).clickable { onClick() },
        backgroundColor = when (task.status) {
            "In Progress" -> Color(0xFFFFCDD2)
            "Pending" -> Color(0xFFDCEDC8)
            else -> Color(0xFFB3E5FC)
        }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(task.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(task.description, fontSize = 16.sp)
                Text("Status: ${task.status}", fontSize = 14.sp,fontWeight = FontWeight.Bold )
            }
            Checkbox(
                checked = isChecked,
                onCheckedChange = { isChecked = it }
            )
        }
    }
}

@Composable
fun EmptyScreen(onAddTask: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Image(
            painter = painterResource(id = R.drawable.uth),

            contentDescription = "Logo",
            //contentScale = ContentScale.Crop,
            alignment = Alignment.TopEnd,
            modifier = Modifier.width(380.dp).height(80.dp)
        )
        //Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.drawable.no_task),
            contentDescription = "Logo",
            //contentScale = ContentScale.Crop,
            alignment = Alignment.TopEnd,
            modifier = Modifier.width(350.dp).height(400.dp)
        )

        IconButton(onClick = onAddTask, modifier = Modifier.padding(16.dp)) {
            Image(
                painter = painterResource(id = R.drawable.action_button),
                contentDescription = "Back",
                //alignment = Alignment.BottomEnd,
                modifier = Modifier.size(400.dp),
                alignment = Alignment.BottomEnd,
            )

        }

    }
}

@Composable
fun TaskDetailScreen(task: Task, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        // Hàng chứa nút back và tiêu đề
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBack) {
                Image(
                    painter = painterResource(id = R.drawable.back), // Thay bằng icon back của bạn
                    contentDescription = "Back",
                    modifier = Modifier.size(40.dp)
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = "Detail",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Blue
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(onBack) {
                Image(
                    painter = painterResource(id = R.drawable.rac), // Thay bằng icon của bạn
                    contentDescription = "Extra Action",
                    modifier = Modifier.size(40.dp)
                )
            }
        }


        Spacer(modifier = Modifier.height(16.dp))

        // Thẻ chứa thông tin nhiệm vụ
        Card(
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color.White),
            elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(task.title, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                Text(task.description, fontSize = 16.sp)
                Spacer(modifier = Modifier.height(8.dp))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(id = R.drawable.back), contentDescription = "Category Icon")
                    Text(" Category: ", fontWeight = FontWeight.Bold)
                    Text(task.category)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(id = R.drawable.back), contentDescription = "Status Icon")
                    Text(" Status: ", fontWeight = FontWeight.Bold)
                    Text(task.status)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(painter = painterResource(id = R.drawable.back), contentDescription = "Priority Icon")
                    Text(" Priority: ", fontWeight = FontWeight.Bold)
                    Text("High", color = Color.Red) // Giả sử ưu tiên là cao
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Danh sách Subtasks
        Text("Subtasks", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(Color(0xFFE0E0E0))
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                val tasks = listOf(
                    "This task is related to Fitness. It needs to be completed",
                    "Complete the report for the project",
                    "Prepare for the upcoming meeting"
                )

                val checkedStates = remember { mutableStateListOf(false, false, false) }

                tasks.forEachIndexed { index, task ->
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(vertical = 4.dp)
                    ) {
                        Checkbox(
                            checked = checkedStates[index],
                            onCheckedChange = { checkedStates[index] = it }
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(task)
                    }
                }
            }
        }



        Spacer(modifier = Modifier.height(16.dp))

        // Phần đính kèm (Attachments)
        Text("Attachments", fontSize = 18.sp, fontWeight = FontWeight.Bold)
        Card(
            modifier = Modifier.fillMaxWidth().clip(RoundedCornerShape(12.dp)).background(Color(0xFFE0E0E0))
        ) {
            Row(
                modifier = Modifier.padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                //Icon(painter = painterResource(id = R.drawable.attachment), contentDescription = "Attachment")
                Spacer(modifier = Modifier.width(8.dp))
                Text("document_1_0.pdf")
            }
        }
    }
}


