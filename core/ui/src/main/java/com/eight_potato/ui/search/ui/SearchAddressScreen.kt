package com.eight_potato.ui.search.ui

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.rememberNestedScrollInteropConnection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.divider.HorizontalDivider
import com.eight_potato.designsystem.input.HyusikOutlinedTextField
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.ui.R
import com.eight_potato.ui.ext.dpToPx
import com.eight_potato.ui.model.address.AddressUiModel

/**
 * 위치 검색 화면 Screen
 * @param keyword 위치 검색 keyword
 * @param addresses 검색 결과 주소 리스트
 * @param onClickCloseButton 닫기 버튼 클릭
 * @param onChangeKeyword 키워드 입력
 * @param onClickCurrentPosition 현재 위치로 설정 버튼
 * @param onClickAddress 주소 클릭
 */
@OptIn(ExperimentalComposeUiApi::class)
@Composable
internal fun SearchAddressScreen(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    keyword: String,
    addresses: List<AddressUiModel>,
    onClickCloseButton : () -> Unit,
    onClearKeyword: () -> Unit,
    onChangeKeyword: (String) -> Unit,
    onClickCurrentPosition: () -> Unit,
    onClickAddress: (AddressUiModel) -> Unit
) {
    Scaffold (
        modifier = modifier
            .clip(RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)),
        topBar = {
            Box (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 26.dp, horizontal = 16.dp)
            ){
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "위치 검색",
                    style = Typo.HeadB18
                )
                Icon(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .size(32.dp)
                        .clickable { onClickCloseButton() },
                    imageVector = Icons.Default.Close,
                    contentDescription = ""
                )
            }
        }
    ) {
        Column(
            modifier = Modifier.padding(it)
        ) {
            HyusikOutlinedTextField(
                autoFocus = true,
                isSingleLine = true,
                maxLength = Int.MAX_VALUE,
                textStyle = Typo.BodyM16,
                imeAction = ImeAction.Search,
                value = keyword,
                onValueChanged = onChangeKeyword,
                trailingIcon = {
                    Icon(
                        modifier = Modifier
                            .size(24.dp)
                            .clickable { onClearKeyword() }
                            .padding(4.dp),
                        imageVector = Icons.Default.Clear,
                        contentDescription = "",
                        tint = Colors.Blk40
                    )
                }
            )
            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { onClickCurrentPosition() }
                    .padding(20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                Image(
                    modifier = Modifier.size(24.dp),
                    painter = painterResource(id = R.drawable.ic_search_current_position),
                    contentDescription = ""
                )
                Text(
                    text = "현재 위치로 주소 찾기",
                    style = Typo.BodySB14,
                    color = Colors.Blk40
                )
            }
            HorizontalDivider(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .padding(horizontal = 20.dp),
                thickness = (2).dpToPx(context).toFloat(),
                color = Colors.Bg50,
                dash = 18f
            )
            if (addresses.isNotEmpty()) {
                LazyColumn (
                    modifier = Modifier
                        .fillMaxWidth()
                        .nestedScroll(
                            rememberNestedScrollInteropConnection()
                        ),
                    contentPadding = PaddingValues(20.dp),
                    verticalArrangement = Arrangement.spacedBy(32.dp)
                ){
                    items(items = addresses, key = { it.name }) { address ->
                        SearchAddressListItem(
                            address = address,
                            onClickAddressItem = onClickAddress
                        )
                    }
                }
            } else {
                Box(modifier = Modifier.fillMaxSize()) {
                    Column (
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ){
                        Image(
                            modifier = Modifier.size(120.dp),
                            painter = painterResource(id = R.drawable.ic_none_address),
                            contentDescription = ""
                        )
                        Text(
                            text = "검색 결과가 없습니다.",
                            style = Typo.BodyM16,
                            color = Colors.Blk40
                        )
                    }
                }
            }
        }
    }
}