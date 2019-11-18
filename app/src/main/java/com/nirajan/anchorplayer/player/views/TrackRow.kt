package com.nirajan.anchorplayer.player.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.constraintlayout.widget.ConstraintLayout
import com.airbnb.epoxy.CallbackProp
import com.airbnb.epoxy.ModelProp
import com.airbnb.epoxy.ModelView
import com.airbnb.epoxy.TextProp
import com.bumptech.glide.Glide
import com.nirajan.anchorplayer.R

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_WRAP_HEIGHT)
class TrackRow @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val titleView: TextView
    private val trackImage: ImageView
    private val playIcon: ImageView

    init {
        inflate(context, R.layout.track_row, this)
        titleView = findViewById(R.id.title)
        trackImage = findViewById(R.id.image)
        playIcon = findViewById(R.id.play)
    }

    @TextProp
    fun setTitle(title: CharSequence) {
        titleView.text = title
    }

    @ModelProp
    fun setImage(imageUrl: String) {
        Glide.with(context).load(imageUrl).into(trackImage)
    }

    @ModelProp
    fun setPlayerIcon(@DrawableRes drawableRes: Int) {
        playIcon.setImageResource(drawableRes)
    }

    @CallbackProp
    fun setClickListener(clickListener: OnClickListener?) {
        playIcon.setOnClickListener(clickListener)
    }
}
