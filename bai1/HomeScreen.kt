
package com.example.bai1

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Spacer(modifier = Modifier.height(100.dp))
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",
            modifier = Modifier.size(200.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.text),
            contentDescription = "",
            modifier = Modifier.size(400.dp)
        )

        IconButton(onClick = { navController.navigate("listScreen") }) {
            Image(
                painter = painterResource(id = R.drawable.push),
                contentDescription = "Back",
                modifier = Modifier.size(400.dp),
                //contentScale = ContentScale.Fit
            )
        }
    }
}
