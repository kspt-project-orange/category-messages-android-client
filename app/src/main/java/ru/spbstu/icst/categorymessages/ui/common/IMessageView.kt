package ru.spbstu.icst.categorymessages.ui.common

import androidx.annotation.StringRes

interface IMessageView {
    fun showMessage(@StringRes messageResId: Int)
}