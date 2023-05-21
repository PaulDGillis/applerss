package com.pgillis.applerssfeed.service

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.pgillis.applerssfeed.RSSApplication

// TODO maybe replace this hacky DI with Hilt? Idk if it is necessary for this size of app
abstract class BaseViewModel(application: Application): AndroidViewModel(application),
    AppContainer by (application as RSSApplication).container