package com.eight_potato.rest.detail.ui.menu

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.chip.HyusikChip
import com.eight_potato.designsystem.divider.HorizontalDivider
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.HyusikMatjuTheme
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.ui.ext.dpToPx
import com.eight_potato.ui.ext.toMoneyFormat
import com.eight_potato.ui.model.menu.MenuType
import com.eight_potato.ui.model.menu.MenuUiModel

@Composable
internal fun RestStopMenu(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    category: MenuType,
    menu: List<MenuUiModel>
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(20.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Colors.Main10,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(
                    vertical = 24.dp,
                    horizontal = 20.dp
                ),
            text = category.text,
            style = Typo.HeadB20,
            color = Colors.Blk100
        )
        HorizontalDivider(
            modifier = modifier.padding(horizontal = 20.dp),
            thickness = (3).dpToPx(context).toFloat(),
            color = Colors.Main30,
            dash = 18f
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Colors.Main10,
                    shape = RoundedCornerShape(20.dp)
                )
                .padding(
                    vertical = 32.dp,
                    horizontal = 20.dp
                ),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            menu.forEach {
                RestStopMenuItem(menu = it)
            }
        }
    }
}

@Composable
private fun RestStopMenuItem(
    modifier: Modifier = Modifier,
    menu: MenuUiModel
) {
    Row(
        modifier = modifier.fillMaxWidth()
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Text(
                text = menu.name,
                style = Typo.HeadB18,
                color = Colors.Blk100
            )
            Text(
                text = "${menu.price.toMoneyFormat()}원",
                style = Typo.BodySB16,
                color = Colors.Blk40
            )
        }
        Row(
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            if (menu.signature) {
                HyusikChip(
                    shape = RoundedCornerShape(8.dp),
                    paddingValues = PaddingValues(8.dp),
                    backgroundColor = Colors.White,
                    border = BorderStroke(
                        width = 1.dp,
                        color = Colors.Main100
                    )
                ) {
                    Text(
                        text = "시그니처",
                        style = Typo.SmallB12,
                        color = Colors.Main100
                    )
                }
            }
            if (menu.popular) {
                HyusikChip(
                    shape = RoundedCornerShape(8.dp),
                    paddingValues = PaddingValues(8.dp),
                    backgroundColor = Colors.Main100,
                    border = null
                ) {
                    Text(
                        text = "인기",
                        style = Typo.SmallB12,
                        color = Colors.White
                    )
                }
            }
        }
    }
}