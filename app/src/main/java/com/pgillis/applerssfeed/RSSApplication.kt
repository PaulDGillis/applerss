package com.pgillis.applerssfeed

import android.app.Application
import com.pgillis.applerssfeed.service.AppContainer
import com.pgillis.applerssfeed.service.AppContainerImpl

class RSSApplication: Application() {

    private var _container: AppContainer? = null
    val container: AppContainer
        get() = _container!!

    override fun onCreate() {
        super.onCreate()

        _container = AppContainerImpl(this)
    }
}