package com.eight_potato.search.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.chip.HyusikChip
import com.eight_potato.designsystem.divider.HorizontalDivider
import com.eight_potato.ui.model.address.AddressUiModel

/**
 * 위치 검색 결과 리스트
 */
@Composable
internal fun SearchAddressList(
    modifier: Modifier = Modifier,
    keyword: String, // 검색 키워드
    addresses: List<AddressUiModel>, // 검색 결과,
    onClickAddressItem: (AddressUiModel) -> Unit
) {
    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            modifier = Modifier.padding(vertical = 20.dp, horizontal = 16.dp),
            text = "$keyword 검색된 주소"
        )
        HorizontalDivider()
        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(vertical = 40.dp, horizontal = 20.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {
            items(items = addresses, key = { it.name }) {
                SearchAddressListItem(
                    address = it,
                    onClickAddressItem = onClickAddressItem
                )
            }
        }
    }
}

@Composable
private fun SearchAddressListItem(
    modifier: Modifier = Modifier,
    address: AddressUiModel,
    onClickAddressItem: (AddressUiModel) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClickAddressItem(address) }
    ) {
        Text(text = address.name)
        Spacer(modifier = Modifier.height(12.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            HyusikChip {
                Text(text = "도로명")
            }
            Spacer(modifier = Modifier.width(12.dp))
            Text(text = address.roadAddr)
        }
    }
}