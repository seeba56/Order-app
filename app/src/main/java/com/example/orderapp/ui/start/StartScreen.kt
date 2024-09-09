package com.example.orderapp.ui.start

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.orderapp.ui.theme.OrderAppTheme

@Composable
fun StartScreen() {
    StartScreenContent(
        {},
        {},
        {}
    )
}

@Composable
private fun StartScreenContent(
    onMenuClick:()->Unit,
    onOrderClick:()->Unit,
    onPastOrderClick:()->Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(1F))
        Text(
            text = "Lipizzano",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic
        )
        Spacer(modifier = Modifier.weight(1F))
        Button(onClick = { /*TODO*/ }, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            Text(text = "Pogledaj jelovnik")
        }
        Button(onClick = { /*TODO*/ },modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            Text(text = "Kreiraj narudžbu")
        }
        Button(onClick = { /*TODO*/ },modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)) {
            Text(text = "Pogledaj prethodne narudžbe")
        }
    }
}

@Composable
@Preview
private fun StartScreenContentPreview(){
    OrderAppTheme {
        StartScreenContent({},{},{})
    }
}