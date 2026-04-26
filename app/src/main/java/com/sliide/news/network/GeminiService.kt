package com.sliide.news.network

import com.google.ai.client.generativeai.GenerativeModel
import com.sliide.news.BuildConfig
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GeminiService @Inject constructor() {

    private val model = GenerativeModel(
        modelName = "gemini-1.5-flash",
        apiKey = BuildConfig.GEMINI_API_KEY
    )

    suspend fun summariseArticle(title: String, description: String?): String {
        return try {
            val prompt = """
                Summarise this news article in exactly 3 short bullet points.
                Each bullet point should be one sentence maximum.
                Be concise and factual.
                
                Title: $title
                Description: ${description ?: "No description available"}
                
                Format your response as:
                • Point one
                • Point two  
                • Point three
            """.trimIndent()

            val response = model.generateContent(prompt)
            response.text ?: "Unable to generate summary"
        } catch (e: Exception) {
            "Unable to generate summary: ${e.message}"
        }
    }

    suspend fun getSentiment(title: String, description: String?): String {
        return try {
            val prompt = """
                Analyse the sentiment of this news headline and description.
                Reply with ONLY one word: Positive, Negative, or Neutral.
                
                Title: $title
                Description: ${description ?: ""}
            """.trimIndent()

            val response = model.generateContent(prompt)
            response.text?.trim() ?: "Neutral"
        } catch (e: Exception) {
            "Neutral"
        }
    }
}