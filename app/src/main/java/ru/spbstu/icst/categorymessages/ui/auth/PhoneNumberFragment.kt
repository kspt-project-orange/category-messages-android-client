package ru.spbstu.icst.categorymessages.ui.auth

import org.androidannotations.annotations.EFragment
import ru.spbstu.icst.categorymessages.R
import ru.spbstu.icst.categorymessages.ui.common.BaseFragment

@EFragment(R.layout.fragment_phone_number)
open class PhoneNumberFragment : BaseFragment() {



    companion object {
        fun newInstance(): PhoneNumberFragment = PhoneNumberFragment_()
    }
}