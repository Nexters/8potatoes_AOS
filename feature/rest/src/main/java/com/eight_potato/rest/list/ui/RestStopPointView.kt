package com.eight_potato.rest.list.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.view.LayoutInflater
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.eight_potato.rest.R
import com.eight_potato.rest.databinding.ItemReststopPointBinding
import com.eight_potato.ui.ext.dpToPx

class RestStopPointView(
    context: Context,
    private val restStopName: String,
    private val isRecommend: Boolean
) : ConstraintLayout(context) {

    init {
        ItemReststopPointBinding.inflate(
            LayoutInflater.from(context),
            this,
            true
        ).apply {
            container.setColor(ContextCompat.getColor(
                context,
                if (isRecommend) R.color.main100 else R.color.white
            ))
            txtRestStopName.text = restStopName
            txtRestStopName.setTextColor(ContextCompat.getColor(
                context,
                if (isRecommend) R.color.white else R.color.main100
            ))
            recommendIcon.isVisible = isRecommend
        }
    }
}