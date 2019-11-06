package ru.spbstu.icst.categorymessages.di

import android.annotation.SuppressLint
import android.content.Context
import ru.spbstu.icst.categorymessages.di.component.DaggerBootstrapComponent
import ru.spbstu.icst.categorymessages.di.module.AppNavigationModule
import ru.spbstu.icst.categorymessages.di.module.BootstrapModule
import ru.spbstu.icst.categorymessages.di.module.UserModule

interface IAppGraphManager {
    fun initBootstrapGraph()
    fun initUserGraph(/*identity: UserIdentity*/)
    fun deinitUserGraph()
}

internal class AppGraphManager private constructor(
    private val context: Context
) : IAppGraphManager {

    override fun initBootstrapGraph() {

        val bootstrapComponent = DaggerBootstrapComponent.builder()
            .bootstrapModule(BootstrapModule(context))
            .appNavigationModule(AppNavigationModule())
            .build()

        InjectionHolder.bootstrapComponent = bootstrapComponent
    }

    override fun initUserGraph(/*identity: UserIdentity*/) {
        val userComponent = InjectionHolder.bootstrapComponent
            .userGraphBuilder()
            .userModule(UserModule(/*identity*/))
            .build()

        InjectionHolder.userComponent = userComponent
    }

    override fun deinitUserGraph() {
        InjectionHolder.userComponent = null
    }

    companion object {

        @SuppressLint("StaticFieldLeak")
        private lateinit var INSTANCE: IAppGraphManager

        fun instance(): IAppGraphManager = INSTANCE

        fun initInstance(context: Context) {
            INSTANCE = AppGraphManager(context)
        }
    }
}