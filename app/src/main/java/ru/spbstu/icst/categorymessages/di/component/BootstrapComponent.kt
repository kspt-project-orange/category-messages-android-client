package ru.spbstu.icst.categorymessages.di.component

import dagger.Component
import ru.spbstu.icst.categorymessages.di.module.AppNavigationModule
import ru.spbstu.icst.categorymessages.di.module.BootstrapModule
import ru.spbstu.icst.categorymessages.di.scope.BootScope
import ru.spbstu.icst.categorymessages.ui.AppActivity

@BootScope
@Component(modules = [BootstrapModule::class, AppNavigationModule::class])
interface BootstrapComponent {

    fun userGraphBuilder(): UserComponent.Builder

    fun inject(activity: AppActivity)
}