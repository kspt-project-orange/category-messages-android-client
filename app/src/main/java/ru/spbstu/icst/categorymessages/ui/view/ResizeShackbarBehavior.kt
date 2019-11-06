package ru.spbstu.icst.categorymessages.ui.view

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.view.ViewGroup
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.snackbar.Snackbar
import kotlin.math.abs
import kotlin.math.roundToInt

class ResizeShackbarBehavior(context: Context, attrs: AttributeSet) : CoordinatorLayout.Behavior<View>(context, attrs) {

    override fun layoutDependsOn(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        return dependency is Snackbar.SnackbarLayout
    }

    override fun onDependentViewRemoved(parent: CoordinatorLayout, child: View, dependency: View) {
        super.onDependentViewRemoved(parent, child, dependency)

        val layoutParams = child.layoutParams
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        child.layoutParams = layoutParams
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: View, dependency: View): Boolean {
        val visibleSnackBarHeight = dependency.translationY - dependency.height

        val layoutParams = child.layoutParams
        layoutParams.height = (parent.height - abs(visibleSnackBarHeight)).roundToInt()
        child.layoutParams = layoutParams

        return true
    }
}