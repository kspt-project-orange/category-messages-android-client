package ru.spbstu.icst.categorymessages.ui

import android.annotation.SuppressLint
import android.os.Bundle
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import org.androidannotations.annotations.EActivity
import ru.spbstu.icst.categorymessages.R
import ru.spbstu.icst.categorymessages.di.InjectionHolder
import ru.spbstu.icst.categorymessages.presentation.AppPresenter
import ru.spbstu.icst.categorymessages.presentation.IAppView
import ru.spbstu.icst.categorymessages.ui.common.BaseActivity
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import javax.inject.Inject

@SuppressLint("Registered")
@EActivity(R.layout.layout_container)
open class AppActivity : BaseActivity(), IAppView {

    @InjectPresenter
    internal lateinit var presenter: AppPresenter

    @Inject /*@field:Named(NavigationQualifiers.APP_NAVIGATION)*/
    internal lateinit var navigatorHolder: NavigatorHolder

    @Inject
    internal lateinit var daggerPresenter: AppPresenter

    @ProvidePresenter
    fun provideAppPresenter(): AppPresenter {
        return daggerPresenter
    }

    private val navigator: Navigator = SupportAppNavigator(this, supportFragmentManager, R.id.layout_container)

    override fun onCreate(savedInstanceState: Bundle?) {
        InjectionHolder.bootstrapComponent.inject(this)
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            presenter.launchRootScreen()
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        presenter.onBackPressedClicked()
    }
}