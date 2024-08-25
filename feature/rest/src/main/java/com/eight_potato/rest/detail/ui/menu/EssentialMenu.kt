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
import coil.compose.AsyncImage
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.ui.ext.toMoneyFormat
import com.eight_potato.ui.model.menu.MenuUiModel
import com.eight_potato.ui.model.menu.RecommendMenuUIModel

@Composable
internal fun EssentialMenu(
    modifier: Modifier = Modifier,
    menu: List<RecommendMenuUIModel>
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "오직 이곳에서만 먹을 수 있는",
            style = Typo.HeadSB20,
            color = Colors.Blk100
        )
        Spacer(modifier = Modifier.height(32.dp))
        AsyncImage(
            modifier = Modifier
                .width(180.dp)
                .aspectRatio(1f)
                .clip(CircleShape),
            model = menu.first().menuImageUrl,
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = menu.first().description,
            style = Typo.BodyM16,
            color = Colors.Blk40
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = menu.first().name,
            style = Typo.HeadB18,
            color = Colors.Blk100
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = "${menu.first().price.toMoneyFormat()}원",
            style = Typo.BodySB16,
            color = Colors.Blk60
        )
    }
}