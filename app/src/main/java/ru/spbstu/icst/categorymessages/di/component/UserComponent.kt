package ru.spbstu.icst.categorymessages.di.component

import dagger.Subcomponent
import ru.spbstu.icst.categorymessages.di.module.UserModule
import ru.spbstu.icst.categorymessages.di.scope.UserScope

@UserScope
@Subcomponent(modules = [UserModule::class])
interface UserComponent {

    @Subcomponent.Builder
    interface Builder {
        fun userModule(module: UserModule): Builder
        fun build(): UserComponent
    }
}