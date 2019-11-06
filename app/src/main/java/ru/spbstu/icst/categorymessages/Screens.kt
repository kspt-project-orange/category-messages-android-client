package ru.spbstu.icst.categorymessages

import ru.spbstu.icst.categorymessages.ui.auth.PhoneNumberFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

   // Add screens for navigation

    object PhoneNumberScreen : SupportAppScreen() {
        override fun getFragment() = PhoneNumberFragment.newInstance()
    }
}