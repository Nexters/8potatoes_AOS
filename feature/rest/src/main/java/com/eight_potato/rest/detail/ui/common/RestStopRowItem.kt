package com.eight_potato.rest.detail.ui.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.rest.model.BrandUiModel
import com.eight_potato.rest.model.RowItem

@Composable
internal fun RestStopRowItem(
    modifier: Modifier = Modifier,
    brands: List<RowItem>
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        contentPadding = PaddingValues(horizontal = 20.dp)
    ) {
        items(items = brands, key = { it.id }) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier
                        .size(80.dp)
                        .aspectRatio(1f)
                        .clip(CircleShape),
                    painter = painterResource(id = com.eight_potato.designsystem.R.drawable.test),
                    contentDescription = "",
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = it.name,
                    color = Colors.Blk100,
                    style = Typo.BodySB16
                )
            }
        }
    }
}