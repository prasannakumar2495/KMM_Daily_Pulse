package com.pk.dailypulse.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pk.dailypulse.Platform

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
    onUpButtonClick: () -> Unit
) {
    Column(modifier = modifier) {
        ToolBar(onUpButtonClick)
        ContentView()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ToolBar(onUpButtonClick: () -> Unit) {
    TopAppBar(title = { Text("About Device") },
        navigationIcon = {
            IconButton(onClick = onUpButtonClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        })
}

@Composable
fun ContentView() {
    val items = makeItems()

    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(items = items) { row ->
            RowView(title = row.first, subtitle = row.second)
        }
    }
}

fun makeItems(): List<Pair<String, String>> {
    val platforms = Platform()

    platforms.logSystemInfo()

    return listOf(
        Pair("Operating System", "${platforms.osName} ${platforms.osVersion}"),
        Pair("Device", platforms.deviceModel),
        Pair("Density", "${platforms.density}"),
    )
}

@Composable
fun RowView(title: String, subtitle: String) {
    Column(Modifier.padding(8.dp)) {
        BasicText(
            text = title,
            style = MaterialTheme.typography.bodySmall
        )
        BasicText(text = subtitle, style = MaterialTheme.typography.bodyLarge)
    }
    Divider()
}