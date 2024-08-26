package com.eight_potato.designsystem.base

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.StyleRes
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.viewbinding.ViewBinding

abstract class BaseCustomView <T: ViewBinding> (
    context: Context,
    attributeSet: AttributeSet,
    @StyleRes customAttributeSet: IntArray
): ConstraintLayout(context, attributeSet){
    protected val binding: T

    var text: String = ""
        set(value) {
            setTextView(value)
            field = value
        }

    init {
        binding = getBinding(context)
        applyAttributeSet(attributeSet, customAttributeSet)
    }

    abstract fun getBinding(context: Context): T
    open fun setTextView(text:String){}
    open fun setAttributeSetToView(typedArray: TypedArray){}

    private fun applyAttributeSet(attributeSet: AttributeSet, customAttributeSet: IntArray) {
        val style = context.obtainStyledAttributes(attributeSet, customAttributeSet)
        setAttributeSetToView(style)
        style.recycle()
    }

    @ColorInt
    protected fun TypedArray.getColorFromIndex(index: Int, @ColorRes defaultColor: Int): Int {
        return this.getColor(index, ContextCompat.getColor(context, defaultColor))
    }
}