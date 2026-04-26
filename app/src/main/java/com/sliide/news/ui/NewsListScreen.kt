package com.sliide.news.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage
import com.sliide.news.core.viewmodel.NewsViewModel
import com.sliide.news.network.model.NewsItem

@Composable
fun NewsListScreen(
    viewModel: NewsViewModel = hiltViewModel()
) {
    val newsState by viewModel.newsState.collectAsStateWithLifecycle()
    val summaryState by viewModel.summaryState.collectAsStateWithLifecycle()
    var selectedArticle by remember { mutableStateOf<NewsItem?>(null) }

    Box(modifier = Modifier.fillMaxSize()) {
        when (val state = newsState) {
            is NewsViewModel.NewsState.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }

            is NewsViewModel.NewsState.Error -> {
                Column(
                    modifier = Modifier.align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text("Something went wrong")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = { viewModel.loadNews() }) {
                        Text("Retry")
                    }
                }
            }

            is NewsViewModel.NewsState.Success -> {
                LazyColumn(
                    contentPadding = PaddingValues(16.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(state.articles) { article ->
                        NewsCard(
                            article = article,
                            onClick = {
                                selectedArticle = article
                                viewModel.summariseArticle(article)
                            }
                        )
                    }
                }
            }
        }

        // AI Summary bottom sheet
        selectedArticle?.let { article ->
            AISummarySheet(
                article = article,
                summaryState = summaryState,
                onDismiss = {
                    selectedArticle = null
                    viewModel.resetSummary()
                }
            )
        }
    }
}

@Composable
fun NewsCard(
    article: NewsItem,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
                ) { onClick() },
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(12.dp)) {

            // Article image
            article.imageUrl?.let { url ->
                AsyncImage(
                    model = url,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(180.dp)
                        .clip(RoundedCornerShape(8.dp)),
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(8.dp))
            }

            // Source + date row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                article.sourceId?.let {
                    Text(
                        text = it.uppercase(),
                        fontSize = 11.sp,
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                article.pubDate?.let {
                    Text(
                        text = it.take(10),
                        fontSize = 11.sp,
                        color = Color.Gray
                    )
                }
            }

            Spacer(modifier = Modifier.height(6.dp))

            // Title
            article.title?.let {
                Text(
                    text = it,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // Description
            article.summary?.let {
                Text(
                    text = it,
                    fontSize = 13.sp,
                    color = Color.Gray,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // AI prompt hint
            Text(
                text = "✨ Tap for AI summary",
                fontSize = 11.sp,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
fun AISummarySheet(
    article: NewsItem,
    summaryState: NewsViewModel.SummaryState,
    onDismiss: () -> Unit
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        // Scrim
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ) { onDismiss() }
        )

        // Sheet
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            ) {
                // Handle bar
                Box(
                    modifier = Modifier
                        .width(40.dp)
                        .height(4.dp)
                        .align(Alignment.CenterHorizontally)
                        .clip(RoundedCornerShape(2.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Article title
                article.title?.let {
                    Text(
                        text = it,
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                // AI Summary content
                when (val state = summaryState) {
                    is NewsViewModel.SummaryState.Idle -> {
                        CircularProgressIndicator(
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        )
                    }

                    is NewsViewModel.SummaryState.Loading -> {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp)
                        ) {
                            CircularProgressIndicator(modifier = Modifier.size(20.dp))
                            Text(
                                text = "Gemini is summarising...",
                                color = Color.Gray,
                                fontSize = 14.sp
                            )
                        }
                    }

                    is NewsViewModel.SummaryState.Success -> {
                        // Sentiment badge
                        val sentimentColor = when (state.sentiment.lowercase()) {
                            "positive" -> Color(0xFF2E7D32)
                            "negative" -> Color(0xFFC62828)
                            else -> Color(0xFF1565C0)
                        }

                        Surface(
                            shape = RoundedCornerShape(20.dp),
                            color = sentimentColor.copy(alpha = 0.1f)
                        ) {
                            Text(
                                text = "● ${state.sentiment}",
                                modifier = Modifier.padding(
                                    horizontal = 12.dp,
                                    vertical = 4.dp
                                ),
                                color = sentimentColor,
                                fontSize = 12.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        }

                        Spacer(modifier = Modifier.height(12.dp))

                        // AI summary text
                        Text(
                            text = "AI Summary",
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp,
                            color = MaterialTheme.colorScheme.primary
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            text = state.summary,
                            fontSize = 14.sp,
                            lineHeight = 22.sp
                        )
                    }

                    is NewsViewModel.SummaryState.Error -> {
                        Text(
                            text = "Could not generate summary",
                            color = Color.Red,
                            fontSize = 14.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))

                // Close button
                OutlinedButton(
                    onClick = onDismiss,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Close")
                }
            }
        }
    }
}