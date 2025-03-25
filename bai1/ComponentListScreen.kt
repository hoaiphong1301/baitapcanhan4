package com.example.bai1

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun ListScreen(navController: NavController) {
    val items = remember { List(1_000_000) { index -> "${index + 1} | The only way to do great work is to love what you do." } }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_icon), // Đổi "back_icon" thành tên file ảnh của bạn
                    contentDescription = "Back",
                    modifier = Modifier.size(40.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Text(
                text = "LazyColumn",
                fontSize = 20.sp,
                color = Color.Blue,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.width(40.dp))
        }

        LazyColumn {
            itemsIndexed(items) { index, itemText ->
                var textState by remember { mutableStateOf(TextFieldValue(itemText)) }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                        .background(Color(0xFFB3E5FC))
                        .padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = textState,
                        onValueChange = { newValue ->
                            textState = newValue
                        },
                        modifier = Modifier.weight(1f)
                    )

                    IconButton(
                        onClick = { navController.navigate("detailScreen/${textState.text}") }
                    ) {
                        Text("➜", fontSize = 20.sp, color = Color.Black)
                    }
                }
            }
        }
    }
}
