package ru.spbstu.icst.categorymessages.ui.view

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.graphics.drawable.Drawable
import android.graphics.drawable.LayerDrawable
import android.os.Handler
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import org.androidannotations.annotations.AfterViews
import org.androidannotations.annotations.EViewGroup
import org.androidannotations.annotations.ViewById
import ru.spbstu.icst.categorymessages.R
import ru.spbstu.icst.categorymessages.ui.view.extention.rippleEffect
import java.util.ArrayList

@EViewGroup(R.layout.layout_loading_button)
open class LoadingButton : FrameLayout {

    private val isClickEnabled: Boolean
        get() = _isEnabled && !isLoading

    private var mClickListener: OnClickListener? = null

    private var isLoading: Boolean = false
    private var _isEnabled: Boolean = false
    private var text: String? = null

    private var startAnimationTime: Long = 0

    private var dotsColor: Int = 0
    private var bgColor: Int = 0
    private var bgColorDisabled: Int = 0
    private var textColor: Int = 0
    private var textColorDisabled: Int = 0

    @ViewById(R.id.image_progress_bar)
    protected lateinit var progressBarImage: ImageView

    @ViewById(R.id.label_loading_button)
    protected lateinit var loadingButtonText: TextView

    @JvmOverloads
    constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyleAttr: Int = R.attr.loadingButtonTheme
    ) : super(context, attrs, defStyleAttr) {
        processAttributes(context, attrs, defStyleAttr, R.style.Widget_LoadingButton)
    }

    constructor(
        context: Context,
        attrs: AttributeSet,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr) {
        processAttributes(context, attrs, defStyleAttr, defStyleRes)
    }

    private fun processAttributes(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) {

        val styledAttributes = context.obtainStyledAttributes(
            attrs,
            R.styleable.LoadingButton,
            defStyleAttr,
            defStyleRes
        )

        text = styledAttributes.getString(R.styleable.LoadingButton_lb_buttonText)

        dotsColor = styledAttributes.getColor(R.styleable.LoadingButton_lb_dotsColor, dotsColor)
        bgColor = styledAttributes.getColor(R.styleable.LoadingButton_lb_backgroundColor, bgColor)
        bgColorDisabled = styledAttributes.getColor(
            R.styleable.LoadingButton_lb_backgroundColorDisabled,
            bgColorDisabled
        )
        textColor = styledAttributes.getColor(R.styleable.LoadingButton_lb_textColor, textColor)
        textColorDisabled = styledAttributes.getColor(
            R.styleable.LoadingButton_lb_textColorDisabled,
            textColorDisabled
        )

        styledAttributes.recycle()
    }

    @AfterViews
    fun onInit() {
        minimumHeight = resources.getDimensionPixelSize(R.dimen.loading_button_min_height)

        val colorFilter = PorterDuffColorFilter(dotsColor, PorterDuff.Mode.SRC_IN)

        val layerDrawable = ContextCompat.getDrawable(context, R.drawable.ic_dots_animation) as LayerDrawable
        val drawables = ArrayList<Drawable>()

        for (i in 0 until layerDrawable.numberOfLayers) {
            val drawable = layerDrawable.findDrawableByLayerId(layerDrawable.getId(i))
            drawable.colorFilter = colorFilter
            drawables.add(drawable)
        }

        val dots = CyclicTransitionDrawable(drawables.toTypedArray())
        progressBarImage.setImageDrawable(dots)
        dots.startTransition(300, 0)

        setText(text)

        super.setOnClickListener { view ->
            if (isClickEnabled && mClickListener != null) {
                mClickListener?.onClick(view)
            }
        }

        isEnabled = true
    }

    fun setLoading(loading: Boolean) {
        if (loading) {
            startAnimationTime = System.currentTimeMillis()
            switchState(true, isEnabled)
        } else {
            val currentTime = System.currentTimeMillis()
            if (currentTime > startAnimationTime + MIN_ANIMATION_DURATION) {
                switchState(false, isEnabled)
            } else {
                Handler().postDelayed(
                    { switchState(false, isEnabled) },
                    MIN_ANIMATION_DURATION - (currentTime - startAnimationTime)
                )
            }
        }
    }

    override fun setEnabled(enabled: Boolean) {
        switchState(isLoading, enabled)
    }

    fun setText(text: String?) {
        setCapsText(text)
    }

    fun setText(@StringRes resId: Int) {
        setCapsText(resources.getString(resId))
    }

    fun setFormattedText(formattedText: CharSequence) {
        loadingButtonText.text = formattedText
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        mClickListener = listener
    }

    // MARK: - Private functions

    private fun setCapsText(text: String?) {
        if (!text.isNullOrEmpty()) {
            loadingButtonText.text = text.toUpperCase()
        }
    }

    private fun switchState(isLoading: Boolean, isEnabled: Boolean) {
        _isEnabled = isEnabled
        this.isLoading = isLoading
        loadingButtonText.visibility = if (this.isLoading) View.GONE else View.VISIBLE
        progressBarImage.visibility = if (this.isLoading) View.VISIBLE else View.GONE

        loadingButtonText.setTextColor(if (isEnabled) textColor else textColorDisabled)
        this.setBackgroundColor(if (isEnabled) bgColor else bgColorDisabled)

        if (isClickEnabled) {
            rippleEffect()
        } else {
            foreground = null
        }
    }

    companion object {

        fun newView(context: Context, style: Int): LoadingButton {
            return LoadingButton_.build(context, null, 0, style)
        }

        private const val MIN_ANIMATION_DURATION: Long = 1000
    }
}
