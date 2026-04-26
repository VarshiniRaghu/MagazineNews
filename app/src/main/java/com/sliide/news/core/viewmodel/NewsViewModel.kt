package com.sliide.news.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sliide.news.network.ApiInterface
import com.sliide.news.network.GeminiService
import com.sliide.news.network.model.NewsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    private val apiInterface: ApiInterface,
    private val geminiService: GeminiService
) : ViewModel() {

    // News list state
    sealed class NewsState {
        object Loading : NewsState()
        data class Success(val articles: List<NewsItem>) : NewsState()
        data class Error(val message: String) : NewsState()
    }

    // AI summary state
    sealed class SummaryState {
        object Idle : SummaryState()
        object Loading : SummaryState()
        data class Success(val summary: String, val sentiment: String) : SummaryState()
        data class Error(val message: String) : SummaryState()
    }

    private val _newsState = MutableStateFlow<NewsState>(NewsState.Loading)
    val newsState: StateFlow<NewsState> = _newsState

    private val _summaryState = MutableStateFlow<SummaryState>(SummaryState.Idle)
    val summaryState: StateFlow<SummaryState> = _summaryState

    init {
        loadNews()
    }

    fun loadNews() {
        viewModelScope.launch {
            _newsState.value = NewsState.Loading
            try {
                val response = apiInterface.getNewsList()
                val articles = response.content ?: emptyList()
                _newsState.value = NewsState.Success(articles)
            } catch (e: Exception) {
                _newsState.value = NewsState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun summariseArticle(article: NewsItem) {
        viewModelScope.launch {
            _summaryState.value = SummaryState.Loading
            try {
                val title = article.title ?: "No title"
                val description = article.summary

                // Run both calls
                val summary = geminiService.summariseArticle(title, description)
                val sentiment = geminiService.getSentiment(title, description)

                _summaryState.value = SummaryState.Success(summary, sentiment)
            } catch (e: Exception) {
                _summaryState.value = SummaryState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun resetSummary() {
        _summaryState.value = SummaryState.Idle
    }
}