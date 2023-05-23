package com.pgillis.applerssfeed

import android.app.Application
import com.pgillis.applerssfeed.service.AppContainer
import com.pgillis.applerssfeed.service.AppContainerImpl

class RSSApplication: Application() {
    val container: AppContainer by lazy { AppContainerImpl(this) }
}