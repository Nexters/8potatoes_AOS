package com.eight_potato.ui.search.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.chip.HyusikChip
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.designsystem.theme.Typography
import com.eight_potato.ui.R
import com.eight_potato.ui.model.address.AddressUiModel

/**
 * 위치 검색 결과
 */
@Composable
internal fun SearchAddressListItem(
    modifier: Modifier = Modifier,
    address: AddressUiModel,
    onClickAddressItem: (AddressUiModel) -> Unit
) {
    Row (
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClickAddressItem(address) }
    ){
        Icon(
            modifier = Modifier.size(24.dp),
            painter = painterResource(id = com.eight_potato.designsystem.R.drawable.ic_end),
            contentDescription = "",
            tint = Colors.Main50
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 6.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Text(
                text = address.name,
                style = Typo.BodyB16
            )
            Spacer(modifier = Modifier.height(4.dp))
            if (address.roadAddr.isNotBlank()) {
                AddrInfoItem(title = "도로명", address = address.roadAddr)
            }
            if (address.oldAddr.isNotBlank()) {
                AddrInfoItem(title = "지번", address = address.oldAddr)
            }
        }
    }
}

@Composable
private fun AddrInfoItem(
    modifier: Modifier = Modifier,
    title: String,
    address: String
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HyusikChip (
            shape = RoundedCornerShape(4.dp),
            paddingValues = PaddingValues(8.dp),
            backgroundColor = Colors.White,
            border = BorderStroke(1.dp, Colors.Bg100)
        ){
            Text(
                text = title,
                style = Typo.BodyM14,
                color = Colors.Blk60
            )
        }
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = address,
            style = Typo.BodyM14
        )
    }
}