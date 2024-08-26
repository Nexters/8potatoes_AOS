package com.eight_potato.rest.list.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.eight_potato.rest.R
import com.eight_potato.ui.ext.dpToPx

class RestStopPointContainer (
    context: Context,
    attributeSet: AttributeSet
) : View(context, attributeSet) {
    private var backgroundColor: Int = ContextCompat.getColor(context, R.color.white)
    fun setColor(
        backgroundColor: Int
    ) {
        this.backgroundColor = backgroundColor
    }

    override fun onDraw(canvas: Canvas) {
        val paint = Paint().apply {
            setColor(ContextCompat.getColor(context, R.color.main100))
            strokeWidth = (2).dpToPx(context).toFloat()
            style = Paint.Style.STROKE
        }
        val solidPaint = Paint().apply {
            setColor(backgroundColor)
            style = Paint.Style.FILL
        }

        val boxHeight = height.toFloat()
        val arc = (8).dpToPx(context).toFloat()
        val tailWidth = (9).dpToPx(context).toFloat()
        val tailHeight = (20).dpToPx(context).toFloat()
        val widthF = width.toFloat() - tailWidth
        val tailArc = (1).dpToPx(context).toFloat()

        // 시작점은 2, 끝점은 width - 2
        val path = Path().apply {
            moveTo(arc, 0f)
            lineTo(widthF - arc, 0f)
            cubicTo(
                widthF - arc, 0f,
                widthF, 0f,
                widthF, arc
            )
            lineTo(widthF + tailWidth - tailArc, arc)
            cubicTo(
                widthF + tailWidth - tailArc, arc,
                widthF + tailWidth, arc,
                widthF + tailWidth, arc + tailArc
            )
            lineTo(widthF, arc + tailHeight)
            lineTo(widthF, boxHeight - arc)
            cubicTo(
                widthF, boxHeight - arc,
                widthF, boxHeight,
                widthF - arc, boxHeight
            )
            lineTo(arc, boxHeight)
            cubicTo(
                arc, boxHeight,
                0f, boxHeight,
                0f, boxHeight - arc
            )
            lineTo(0f, arc)
            cubicTo(
                0f, arc,
                0f, 0f,
                arc, 0f
            )
            close()
        }
        canvas.drawPath(path, paint)
        canvas.drawPath(path, solidPaint)
        super.onDraw(canvas)
    }
}