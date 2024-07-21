package com.eight_potato.search

import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.input.HyusikOutlinedTextField
import com.eight_potato.search.ui.SearchAddressHeader
import com.eight_potato.search.ui.SearchAddressList
import com.eight_potato.ui.base.BaseActivity
import com.eight_potato.ui.model.address.TEST_ADDRESS
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchAddressActivity : BaseActivity() {
    private val viewModel: SearchAddressViewModel by viewModels()

    @Composable
    override fun Body() {
        val keyword = viewModel.keyword.collectAsState()
        Scaffold(
            topBar = {
                SearchAddressHeader { finish() }
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
                SearchAddressList(
                    keyword = "홍대",
                    addresses = TEST_ADDRESS,
                    onClickAddressItem = {}
                )
            }
        }
    }
}