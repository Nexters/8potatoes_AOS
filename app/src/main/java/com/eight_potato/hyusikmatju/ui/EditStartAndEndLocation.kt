package com.eight_potato.hyusikmatju.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.layout
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.divider.HorizontalDivider
import com.eight_potato.designsystem.divider.VerticalDivider
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.ui.ext.dpToPx
import com.eight_potato.ui.model.address.AddressUiModel

/**
 * 출발지와 도착지 입력란
 */
@Composable
internal fun EditStartAndEndLocation(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    start: AddressUiModel? = null,
    end: AddressUiModel? = null,
    onClickStartAddress: () -> Unit,
    onClickEndAddress: () -> Unit,
    onClickReverse: () -> Unit
) {
    Layout(
        modifier = modifier.padding(horizontal = 20.dp, vertical = 10.dp),
        content = {
            VerticalDivider(
                modifier = Modifier.layoutId("vertical"),
                thickness = (2).dpToPx(context).toFloat(),
                color = Colors.Main30,
                dash = (4).dpToPx(context).toFloat()
            )
            Image(
                modifier = Modifier.layoutId("icStart"),
                painter = painterResource(id = com.eight_potato.designsystem.R.drawable.ic_start),
                contentDescription = "start"
            )
            Image(
                modifier = Modifier.layoutId("icEnd"),
                painter = painterResource(id = com.eight_potato.designsystem.R.drawable.ic_end),
                contentDescription = "start"
            )
            Image(
                modifier = Modifier
                    .layoutId("reverse")
                    .clickable { onClickReverse() }
                    .padding(16.dp),
                painter = painterResource(id = com.eight_potato.designsystem.R.drawable.ic_reverse),
                contentDescription = "start"
            )
            InputLocationItem(
                modifier = Modifier.layoutId("start"),
                title = "출발지 입력",
                desc = "어디서 출발하세요?",
                address = start,
                onClickAddress = onClickStartAddress
            )
            HorizontalDivider(modifier = Modifier.layoutId("divider"))
            InputLocationItem(
                modifier = Modifier.layoutId("end"),
                title = "도착지 입력",
                desc = "어디까지 가세요?",
                address = end,
                onClickAddress = onClickEndAddress
            )
        }
    ) { measurable, constraints ->
        val reverse = measurable.find { it.layoutId == "reverse" }?.measure(constraints)
        val icStart = measurable.find { it.layoutId == "icStart" }?.measure(constraints)
        val icEnd = measurable.find { it.layoutId == "icEnd" }?.measure(constraints)

        val reverseWidth = reverse?.width ?: 0
        val reverseHeight = reverse?.height ?: 0
        val icStartHeight = icStart?.height ?: 0
        val icStartWidth = icStart?.width ?: 0
        val icEndHeight = icEnd?.height ?: 0

        val frontPadding = (20).dp.roundToPx()
        val extraWidth =
            constraints.maxWidth - reverseWidth - icStartWidth - frontPadding
        val newConstraints = constraints.copy(
            minWidth = 0, maxWidth = kotlin.math.max(0, extraWidth)
        )

        val start = measurable.find { it.layoutId == "start" }?.measure(newConstraints)
        val end = measurable.find { it.layoutId == "end" }?.measure(newConstraints)
        val divider = measurable.find { it.layoutId == "divider" }?.measure(newConstraints)

        val startHeight = start?.height ?: 0
        val endHeight = end?.height ?: 0

        val verticalHeight = startHeight - icStartHeight - (8).dp.roundToPx()
        val vertical = measurable.find { it.layoutId == "vertical" }?.measure(
            constraints = constraints.copy(
                minHeight = verticalHeight, maxHeight = verticalHeight
            )
        )
        layout(
            width = constraints.maxWidth,
            height = constraints.maxHeight
        ) {
            val startX = icStartWidth + (20).dp.roundToPx()
            start?.placeRelative(startX, 0)
            end?.placeRelative(startX, startHeight)
            divider?.placeRelative(startX, startHeight)
            icStart?.placeRelative(0, (startHeight - icStartHeight) / 2)
            icEnd?.placeRelative(0, startHeight + (endHeight - icEndHeight) / 2)
            reverse?.placeRelative(
                constraints.maxWidth - reverseWidth,
                (constraints.maxHeight - reverseHeight) / 2
            )
            vertical?.placeRelative(
                icStartWidth / 2, startHeight / 2 + icStartHeight / 2 + (4).dp.roundToPx()
            )
        }
    }
}

@Composable
private fun InputLocationItem(
    modifier: Modifier = Modifier,
    title: String,
    desc: String,
    address: AddressUiModel?,
    onClickAddress: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClickAddress() }
            .padding(vertical = 22.dp)
    ) {
        Row {
            Text(
                text = title,
                style = Typo.BodyM14,
                color = Colors.Blk30
            )
            Text(
                text = "*",
                style = Typo.BodyM14,
                color = Colors.error100
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = address?.name ?: desc,
            style = Typo.BodySB18,
            color = address?.name?.let { Colors.Blk100 } ?: Colors.Blk40
        )
    }
}