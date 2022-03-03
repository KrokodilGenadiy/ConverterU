package com.zaus_app.converter.di

import com.zaus_app.converter.di.modules.DomainModule
import com.zaus_app.converter.viewmodel.HomeFragmentViewModel
import com.zaus_app.moviefrumy.di.modules.DatabaseModule
import com.zaus_app.moviefrumy.di.modules.RemoteModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        DomainModule::class,
        RemoteModule::class,
        DatabaseModule::class
    ]
)
interface AppComponent {
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
}