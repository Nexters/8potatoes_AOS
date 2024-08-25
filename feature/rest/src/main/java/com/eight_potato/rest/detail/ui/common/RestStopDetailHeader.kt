package com.eight_potato.rest.detail.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.dot.BaseDot
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.rest.R
import com.eight_potato.rest.model.DetailRestStopUiModel

@Composable
internal fun RestStopDetailHeader(
    modifier: Modifier = Modifier,
    restStop: DetailRestStopUiModel
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Min),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = restStop.name,
                style = Typo.HeadB20
            )
            if (restStop.direction != null) {
                Spacer(modifier = Modifier
                    .padding(horizontal = 12.dp)
                    .size(width = 1.dp, height = 16.dp)
                    .background(Colors.Blk20))
                Text(
                    text = restStop.direction,
                    style = Typo.HeadSB20,
                    color = Colors.Blk40
                )
            }
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "식당 ${if (restStop.isOperating) "영업중" else "영업끝"}",
                style = Typo.BodySB14,
                color = Colors.Blk80
            )
            BaseDot(
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = "${restStop.endTime}까지",
                style = Typo.BodySB14,
                color = Colors.Blk80
            )
            Spacer(modifier = Modifier.width(12.dp))
            Icon(
                modifier = Modifier.size(12.dp),
                painter = painterResource(id = R.drawable.ic_star),
                contentDescription = "",
                tint = Colors.Blk80
            )
            Text(
                modifier = Modifier.padding(start = 2.dp),
                text = "${restStop.rate}",
                style = Typo.BodySB14,
                color = Colors.Blk80
            )
            BaseDot(
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            Text(
                text = "네이버평점",
                style = Typo.BodySB14,
                color = Colors.Blk80
            )
        }
    }
}