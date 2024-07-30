package com.eight_potato.ui.toolbar

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy

@Stable
interface ToolbarState {
    val height: Float
    val consumed: Float
    var scrollTopLimitReached: Boolean
    var scrollOffset: Float
}

abstract class ScrollFlagState(heightRange: IntRange) : ToolbarState {
    protected val minHeight = heightRange.first
    protected val maxHeight = heightRange.last
    protected val rangeOfDiff = maxHeight - minHeight
    protected var _consumed = 0f
    protected abstract var _scrollOffset: Float

    final override val height: Float
        get() = (maxHeight - scrollOffset).coerceIn(minHeight.toFloat(), maxHeight.toFloat())

    final override val consumed: Float
        get() = _consumed

    final override var scrollTopLimitReached = true
}

/**
 * Scroll 시 App bar 가 상단에 고정되는 Collapsed Toolbar 의 State
 */
class ExitUnitCollapsedState(
    heightRange: IntRange,
    scrollOffset: Float = 0f
) : ScrollFlagState(heightRange) {
    override var _scrollOffset by mutableStateOf(
        value = scrollOffset.coerceIn(0f, rangeOfDiff.toFloat()),
        policy = structuralEqualityPolicy()
    )

    /**
     * Scroll 된 정도
     * 즉, Toolbar 의 줄어든 길이
     * 때문에 height = maxHeight - scrollOffset 입니다.
     */
    override var scrollOffset: Float
        get() = _scrollOffset
        set(value) {
            if (scrollTopLimitReached) {
                val oldOffset = _scrollOffset
                _scrollOffset = value.coerceIn(0f, rangeOfDiff.toFloat())
                _consumed = oldOffset - _scrollOffset
            } else {
                _consumed = 0f
            }
        }
}

@Composable
fun rememberToolbarState(toolbarRange: IntRange): ToolbarState {
    return remember(toolbarRange){
        ExitUnitCollapsedState(toolbarRange)
    }
}