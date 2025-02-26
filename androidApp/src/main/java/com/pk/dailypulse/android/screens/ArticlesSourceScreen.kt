package com.pk.dailypulse.android.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.pk.dailypulse.articlesSource.response.Source
import com.pk.dailypulse.articlesSource.viewModel.ArticlesSourceViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ArticlesSourceScreen(
	modifier: Modifier = Modifier,
	articlesSourceViewModel: ArticlesSourceViewModel = getViewModel(),
	onButtonClick: () -> Unit,
) {
	val articlesSourceState = articlesSourceViewModel.articlesSourceState.collectAsState()
	
	Column(modifier = modifier) {
		AppBar("Sources", onAboutButtonClick = onButtonClick)
		
		if (articlesSourceState.value.error != null)
			ErrorMessage(articlesSourceState.value.error ?: "")
		if (articlesSourceState.value.articlesSource.isNotEmpty())
			ArticlesSourceListView(articlesSourceState.value.articlesSource)
	}
}

@Composable
fun ArticlesSourceListView(data: List<Source>) {
	LazyColumn {
		items(items = data) {
			ArticlesSourceSingleItem(it)
		}
	}
}

@Composable
fun ArticlesSourceSingleItem(it: Source) {
	Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
		Text(it.name ?: "", style = MaterialTheme.typography.titleMedium)
		Text(it.description ?: "", style = MaterialTheme.typography.bodyMedium)
	}
}
