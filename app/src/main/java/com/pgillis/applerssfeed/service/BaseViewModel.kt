package com.pgillis.applerssfeed.service

import android.app.Application
import androidx.lifecycle.AndroidViewModel

abstract class BaseViewModel(application: Application): AndroidViewModel(application),
    AppContainer by AppContainerImpl(application)