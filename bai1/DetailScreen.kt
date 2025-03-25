package com.example.bai1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun DetailScreen(navController: NavController, text: String) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = { navController.popBackStack() }
            ) {
                Image(
                    painter = painterResource(id = R.drawable.back_icon),
                    contentDescription = "Back",
                    modifier = Modifier.size(40.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Text(
                text = "Detail",
                fontSize = 20.sp,
                color = Color.Blue,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.width(40.dp))
        }

        Spacer(modifier = Modifier.height(30.dp))

        Text(text)

        Spacer(modifier = Modifier.height(48.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(500.dp),
            contentAlignment = Alignment.TopStart
        ) {
            Image(
                painter = painterResource(id = R.drawable.backgroud),
                contentDescription = "",
                modifier = Modifier.matchParentSize(),
                contentScale = ContentScale.FillWidth
            )

            Text(
                text = text,
                color = Color.Black,
                fontSize = 30.sp,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)

            )}

        //Spacer(modifier = Modifier.height(8.dp))

        IconButton(
            onClick = { navController.navigate("homescreen") }

        ) {
            Image(
                painter = painterResource(id = R.drawable.backtoroot),
                contentDescription = "Back",
                modifier = Modifier.size(400.dp),
                contentScale = ContentScale.Fit
            )
        }
    }}

