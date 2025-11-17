package com.sliide.news.network

import dagger.Component

@Component(modules = [ApiModule::class])
interface NetworkComponent {
    fun createApiInterface(): ApiInterface
}