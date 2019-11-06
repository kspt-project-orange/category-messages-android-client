package ru.spbstu.icst.categorymessages.di.module

import dagger.Module
import dagger.Provides
import ru.spbstu.icst.categorymessages.di.scope.BootScope
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router

@Module
class AppNavigationModule {

    private val appCicerone: Cicerone<Router> = Cicerone.create()

    @Provides
    @BootScope
    internal fun provideRouter(): Router {
        return appCicerone.router
    }

    @Provides
    @BootScope
//    @Named(NavigationQualifiers.APP_NAVIGATION)
    internal fun provideNavigationHolder(): NavigatorHolder {
        return appCicerone.navigatorHolder
    }
}