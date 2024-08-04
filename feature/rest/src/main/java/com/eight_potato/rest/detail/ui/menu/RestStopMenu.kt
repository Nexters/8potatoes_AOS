package com.eight_potato.rest.detail.ui.menu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eight_potato.common.model.MenuSortType
import com.eight_potato.designsystem.button.HyusikButton
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.ui.item.RestStopMenuItem
import com.eight_potato.ui.model.menu.MenuUiModel

/**
 * 휴게소 메뉴 정보
 */
@Composable
internal fun RestStopMenu(
    modifier: Modifier = Modifier,
    menus: List<MenuUiModel>,
    onClickMore: () -> Unit,
    onChangeMenuSortType: (MenuSortType) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Colors.White)
            .padding(vertical = 40.dp, horizontal = 20.dp)
    ) {
        Text(
            text = "이 안에 네 최애 있다",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${menus.size}개의 메뉴",
                fontSize = 14.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFBCB6AD)
            )
            Box {
                Row(
                    modifier = Modifier.clickable { expanded = true },
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = MenuSortType.LOW_PRICE.text,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = ""
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = expanded.not() }
                ) {
                    MenuSortType.values().forEach {
                        DropdownMenuItem(
                            text = { Text(text = it.text) },
                            onClick = { onChangeMenuSortType(it) }
                        )
                    }
                }
            }
        }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            menus.forEach { menu ->
                RestStopMenuItem(
                    modifier = Modifier.padding(bottom = 40.dp),
                    menu = menu
                )
            }
        }
        HyusikButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onClickMore,
            contentPadding = PaddingValues(vertical = 18.dp),
            shape = RoundedCornerShape(8.dp),
            backgroundColor = Colors.White,
            border = BorderStroke(width = 1.dp, color = Color(0xFFE9E5DE))
        ) {
            Text(
                text = "전체 메뉴 보기",
                fontSize = 16.sp,
                color = Colors.Black
            )
        }
    }
}