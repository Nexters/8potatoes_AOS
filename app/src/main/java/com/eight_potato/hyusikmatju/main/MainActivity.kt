package com.eight_potato.hyusikmatju.main

import android.app.Activity
import android.content.Intent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.eight_potato.designsystem.button.HyusikButton
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.hyusikmatju.ui.EditStartAndEndLocation
import com.eight_potato.search.SearchAddressActivity
import com.eight_potato.ui.base.BaseActivity
import com.eight_potato.ui.ext.getSerializable
import com.eight_potato.ui.header.SingleTextHeader
import com.eight_potato.ui.model.address.AddressUiModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity() {
    private val viewModel: MainViewModel by viewModels()

    @Composable
    override fun Body() {
        Scaffold(
            topBar = {
                SingleTextHeader(
                    title = "경로 입력"
                )
            },
            bottomBar = {
                HyusikButton(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp, bottom = 28.dp)
                        .fillMaxWidth(),
                    onClick = {},
                    backgroundColor = Colors.Black
                ) {
                    Text(text = "이 위치로 주소 등록")
                }
            }
        ) {
            val start = viewModel.start.collectAsState()
            val end = viewModel.end.collectAsState()

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                Image(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f),
                    painter = painterResource(id = com.eight_potato.designsystem.R.drawable.test),
                    contentScale = ContentScale.Crop,
                    contentDescription = ""
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(top = 40.dp, bottom = 32.dp),
                    text = "쥬쥬와 함께\n휴게소 맛집을 찾아보세요!",
                    color = Colors.Black,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
                EditStartAndEndLocation(
                    start = start.value,
                    end = end.value,
                    onClickStartAddress = ::moveToSearchScreenForStart,
                    onClickEndAddress = ::moveToSearchScreenForEnd,
                    onClickReverse = viewModel::reverse
                )
            }
        }
    }

    private fun moveToSearchScreenForStart() {
        startLocationLauncher.launch(Intent(this, SearchAddressActivity::class.java))
    }

    private fun moveToSearchScreenForEnd() {
        endLocationLauncher.launch(Intent(this, SearchAddressActivity::class.java))
    }

    private val startLocationLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            it.data?.getSerializable<AddressUiModel>("location")?.let { address ->
                viewModel.setStart(address)
            }
        }
    }

    private val endLocationLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == Activity.RESULT_OK) {
            it.data?.getSerializable<AddressUiModel>("location")?.let { address ->
                viewModel.setEnd(address)
            }
        }
    }
}