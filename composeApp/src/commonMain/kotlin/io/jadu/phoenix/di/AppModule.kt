package io.jadu.phoenix.di

import io.jadu.phoenix.storage.StorageRepository
import io.jadu.phoenix.ui.viewmodel.HomeViewModel
import io.jadu.phoenix.ui.viewmodel.StorageViewModel
import org.koin.core.module.Module
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

fun appModule(): Module = module {

    single { StorageRepository() }
    viewModel { HomeViewModel() }
    viewModel { StorageViewModel(get()) }
}

val appModule = listOf(
    appModule(),
    platformModule()
)
