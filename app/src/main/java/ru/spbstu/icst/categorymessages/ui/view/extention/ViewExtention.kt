package ru.spbstu.icst.categorymessages.ui.view.extention

import android.widget.FrameLayout

fun FrameLayout.rippleEffect() {
    val attrs = intArrayOf(android.R.attr.selectableItemBackground)
    val typedArray = context.obtainStyledAttributes(attrs)
    val drawableFromTheme = typedArray.getDrawable(0 )
    typedArray.recycle()
    foreground = drawableFromTheme
}