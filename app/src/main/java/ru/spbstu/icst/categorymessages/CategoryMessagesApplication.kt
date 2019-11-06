package ru.spbstu.icst.categorymessages

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import ru.spbstu.icst.categorymessages.di.AppGraphManager
import timber.log.Timber

class CategoryMessagesApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        initLogger()
        initThreeTean()

        AppGraphManager.initInstance(this)
        AppGraphManager.instance().initBootstrapGraph()
    }

    private fun initLogger() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initThreeTean() {
        AndroidThreeTen.init(this)
    }
}