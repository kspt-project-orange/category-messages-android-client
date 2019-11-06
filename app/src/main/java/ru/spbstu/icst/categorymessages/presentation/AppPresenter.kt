package ru.spbstu.icst.categorymessages.presentation

import moxy.MvpPresenter
import ru.spbstu.icst.categorymessages.Screens
import ru.terrakok.cicerone.Router
import javax.inject.Inject

class AppPresenter @Inject constructor(
//    private val bootstrapInteractor: IBootstrapInteractor,
    private val router: Router
): MvpPresenter<IAppView>() {

    fun launchRootScreen() {
//        val isSignIn = bootstrapInteractor.isSignIn()
//
//        val screen = if (isSignIn) Screens.UserFlow else Screens.AuthFlow
//        router.newRootScreen(screen)
        router.newRootScreen(Screens.PhoneNumberScreen)
    }

    fun onBackPressedClicked() = router.exit()
}