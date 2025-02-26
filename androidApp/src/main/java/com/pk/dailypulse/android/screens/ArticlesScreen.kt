package com.pk.dailypulse.android.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.SwipeRefreshState
import com.pk.dailypulse.articles.data.Articles
import com.pk.dailypulse.articles.viewModel.ArticlesViewModel
import org.koin.androidx.compose.getViewModel

@Composable
fun ArticlesScreen(
	modifier: Modifier = Modifier,
	articlesViewModel: ArticlesViewModel = getViewModel(),
	onSourceClick: () -> Unit,
	onAboutButtonClick: () -> Unit,
) {
	val articlesState = articlesViewModel.articlesState.collectAsState()
	
	Column(modifier = modifier) {
		AppBar("Articles", onSourceClick, onAboutButtonClick)
		
		if (articlesState.value.error != null)
			ErrorMessage(articlesState.value.error ?: "")
		if (articlesState.value.articles.isNotEmpty())
			ArticlesListView(articlesViewModel)
	}
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(
	title: String, onSourceClick: () -> Unit = {},
	onAboutButtonClick: () -> Unit,
) {
	TopAppBar(title = { Text(title) },
		actions = {
			IconButton(onClick = onSourceClick) {
				Icon(
					imageVector = Icons.Outlined.Menu,
					contentDescription = null
				)
			}
			IconButton(onClick = onAboutButtonClick) {
				Icon(
					imageVector = Icons.Outlined.Info,
					contentDescription = null
				)
			}
		})
}

@Composable
fun ErrorMessage(error: String) {
	Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
		Text(text = error, style = MaterialTheme.typography.bodyLarge, textAlign = TextAlign.Center)
	}
}

@Composable
fun ArticlesListView(viewModel: ArticlesViewModel) {
	SwipeRefresh(
		state = SwipeRefreshState(viewModel.articlesState.value.loading),
		onRefresh = {
			viewModel.getArticles(true)
		}) {
		LazyColumn {
			items(items = viewModel.articlesState.value.articles) {
				ArticleItemView(it)
			}
		}
	}
}

@Composable
fun ArticleItemView(article: Articles) {
	Column(
		modifier = Modifier
			.fillMaxWidth()
			.padding(16.dp)
	) {
		AsyncImage(
			modifier = Modifier
				.fillMaxWidth(),
			model = article.imageUrl,
			contentDescription = null,
			contentScale = ContentScale.FillBounds
		)
		Spacer(modifier = Modifier.height(4.dp))
		Text(text = article.title, style = MaterialTheme.typography.headlineSmall)
		Spacer(Modifier.height(4.dp))
		Text(text = article.desc, style = MaterialTheme.typography.bodyMedium)
		Spacer(Modifier.height(4.dp))
		Text(
			modifier = Modifier.fillMaxWidth(),
			text = article.date,
			style = MaterialTheme.typography.bodySmall,
			textAlign = TextAlign.End
		)
	}
}