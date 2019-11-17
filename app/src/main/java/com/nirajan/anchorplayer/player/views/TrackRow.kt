package com.nirajan.anchorplayer.player.views

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
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

    init {
        inflate(context, R.layout.track_row, this)
        titleView = findViewById(R.id.title)
        trackImage = findViewById(R.id.image)
    }

    @TextProp
    fun setTitle(title: CharSequence) {
        titleView.text = title
    }

    @ModelProp
    fun setImage(imageUrl: String) {
        Glide.with(context).load(imageUrl).into(trackImage)
    }

    @CallbackProp
    fun setClickListener(clickListener: OnClickListener?) {
        setOnClickListener(clickListener)
    }
}
