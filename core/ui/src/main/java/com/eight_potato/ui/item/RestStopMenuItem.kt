package com.eight_potato.ui.item

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eight_potato.ui.ext.toMoneyFormat
import com.eight_potato.ui.model.menu.MenuUiModel

/**
 * 휴게소 메뉴 리스트 아이템
 */
@Composable
fun RestStopMenuItem(
    modifier: Modifier = Modifier,
    menu: MenuUiModel
) {
    Row (
        modifier = modifier.fillMaxWidth()
    ){
        Column (
            modifier = Modifier.weight(1f)
        ){
            Text(
                text = menu.name,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "${menu.price.toMoneyFormat()}원",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFA69F95)
            )
        }
    }
}