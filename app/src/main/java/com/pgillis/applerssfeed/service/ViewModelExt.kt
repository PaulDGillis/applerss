package com.pgillis.applerssfeed.service

import androidx.compose.runtime.Composable
import androidx.lifecycle.HasDefaultViewModelProviderFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.pgillis.applerssfeed.RSSApplication


// This is to
@Suppress("MissingJvmstatic")
@Composable
inline fun <reified VM : ViewModel> viewModel(
    viewModelStoreOwner: ViewModelStoreOwner = checkNotNull(LocalViewModelStoreOwner.current) {
        "No ViewModelStoreOwner was provided via LocalViewModelStoreOwner"
    },
    key: String? = null,
    extras: CreationExtras = if (viewModelStoreOwner is HasDefaultViewModelProviderFactory) {
        viewModelStoreOwner.defaultViewModelCreationExtras
    } else {
        CreationExtras.Empty
    },
    crossinline objFactory: CreationExtras.(AppContainer) -> VM
): VM {
    val factory: ViewModelProvider.Factory = viewModelFactory {
        initializer {
            val container = (this[APPLICATION_KEY] as RSSApplication).container
            return@initializer objFactory(container)
        }
    }

    return androidx.lifecycle.viewmodel.compose.viewModel(
        VM::class.java,
        viewModelStoreOwner,
        key,
        factory,
        extras
    )
}