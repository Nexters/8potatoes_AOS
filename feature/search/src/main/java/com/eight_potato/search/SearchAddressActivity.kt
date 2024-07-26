package com.eight_potato.search

import android.app.Activity
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.input.HyusikOutlinedTextField
import com.eight_potato.search.location.CurrentLocationActivity
import com.eight_potato.search.ui.SearchAddressList
import com.eight_potato.ui.base.BaseActivity
import com.eight_potato.ui.ext.getSerializable
import com.eight_potato.ui.header.SingleTextHeader
import com.eight_potato.ui.model.address.AddressUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchAddressActivity : BaseActivity() {
    private val viewModel: SearchAddressViewModel by viewModels()

    @Composable
    override fun Body() {
        val address = viewModel.address.collectAsState()
        val keyword = viewModel.keyword.collectAsState()
        Scaffold(
            topBar = {
                SingleTextHeader(
                    title = "위치 설정",
                    onClickCloseButton = { finish() }
                )
            }
        ) {
            Column(
                modifier = Modifier.padding(it)
            ) {
                HyusikOutlinedTextField(
                    modifier = Modifier.padding(vertical = 12.dp, horizontal = 20.dp),
                    hint = "건물명, 도로명 또는 지번으로 입력해주세요",
                    value = keyword.value,
                    onValueChanged = viewModel::changeKeyword,
                    onClear = { viewModel.changeKeyword("") }
                )
                CurrentPositionButton {
                    searchCurrentLocationResult.launch(
                        Intent(
                            this@SearchAddressActivity,
                            CurrentLocationActivity::class.java
                        )
                    )
                }
                SearchAddressList(
                    keyword = keyword.value,
                    addresses = address.value,
                    onClickAddressItem = ::onBackWithLocation
                )
            }
        }
    }

    private val searchCurrentLocationResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            it.data?.getSerializable<AddressUiModel>("location")?.let { address ->
                onBackWithLocation(address)
            }
        }
    }

    private fun onBackWithLocation(address: AddressUiModel) {
        setResult(RESULT_OK, intent.apply {
            putExtra("location", address)
        })
        finish()
    }
}

@Composable
private fun CurrentPositionButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = "현재 위치로 주소 검색"
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = "현재 위치로 주소 찾기")
    }
}