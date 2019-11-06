package ru.spbstu.icst.categorymessages.di

import ru.spbstu.icst.categorymessages.di.component.BootstrapComponent
import ru.spbstu.icst.categorymessages.di.component.UserComponent
import kotlin.properties.Delegates.notNull

object InjectionHolder {

    var bootstrapComponent: BootstrapComponent by notNull()

    var userComponent: UserComponent? = null
}