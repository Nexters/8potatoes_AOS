package com.eight_potato.rest.detail.ui.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.ui.ext.toMoneyFormat
import com.eight_potato.ui.model.menu.MenuUiModel

@Composable
internal fun EssentialMenu(
    modifier: Modifier = Modifier,
    menu: MenuUiModel,
    restStopName: String
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Colors.White)
            .padding(vertical = 40.dp, horizontal = 44.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = com.eight_potato.designsystem.R.drawable.ic_essential_menu),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(6.dp))
            Text(
                text = "예쁘게 먹으면 기분이 조크등요",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Image(
            modifier = Modifier
                .width(164.dp)
                .aspectRatio(1f)
                .clip(CircleShape),
            painter = painterResource(
                id = com.eight_potato.designsystem.R.drawable.test
            ),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "오직 ${restStopName}에서만 즐길 수 있는",
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                color = Colors.Gray600
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = menu.name,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Colors.Black
            )
        }
        Text(
            text = "${menu.price.toMoneyFormat()}원",
            fontSize = 16.sp,
            fontWeight = FontWeight.SemiBold,
            color = Colors.Gray600
        )
    }
}