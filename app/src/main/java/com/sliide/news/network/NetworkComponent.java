package com.sliide.news.network;

import dagger.Component;

@Component(modules = {
        ApiModule.class
})
public interface NetworkComponent {

    ApiInterface createApiInterface();
}
