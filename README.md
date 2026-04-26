# MagazineNews

A modern Android news application powered by the [NewsData.io](https://newsdata.io) API, 
featuring AI-powered article summarisation and sentiment analysis using Google Gemini.

## Features

- 📰 Latest news feed with images, source, and publication date
- ✨ AI-powered article summarisation — tap any article for a 3-bullet Gemini summary
- 🎭 AI sentiment analysis — Positive, Negative, or Neutral badge per article
- 🔄 Clean loading, error, and retry states

## Tech Stack

| Category | Technology |
|---|---|
| Language | Kotlin |
| UI | Jetpack Compose |
| Architecture | MVVM |
| Dependency Injection | Hilt |
| Networking | Retrofit + OkHttp |
| Concurrency | Coroutines |
| Image Loading | Coil |
| AI | Google Gemini API |

## Architecture

The app follows MVVM with a clean separation of concerns:

- **Network layer** — Retrofit service + Gemini AI service
- **ViewModel** — manages news list state and AI summary state via StateFlow
- **UI layer** — Jetpack Compose screens consuming ViewModel state

## Previously

This app was originally built with RxJava, Dagger, XML layouts, 
RecyclerView, ViewPager, and Databinding. It has been fully modernised 
to the current Android stack with AI features added on top — 
demonstrating real-world legacy migration skills.

## Setup

1. Get a free API key from [newsdata.io](https://newsdata.io)
2. Get a Gemini API key from [Google AI Studio](https://aistudio.google.com)
3. Add to your `local.properties`:
   NEWS_API_KEY=your_newsdata_key
   GEMINI_API_KEY=your_gemini_key
4. Build and run


