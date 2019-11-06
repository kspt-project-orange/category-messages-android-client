package ru.spbstu.icst.categorymessages.di.qualifier

import javax.inject.Qualifier

/**
 * Should never be used outside dagger module classes
 */
@Qualifier
@Retention(AnnotationRetention.RUNTIME)
internal annotation class GraphInternal