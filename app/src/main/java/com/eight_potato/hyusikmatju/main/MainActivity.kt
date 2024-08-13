package com.eight_potato.hyusikmatju.main

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
import com.eight_potato.rest.list.RestListActivity
import com.eight_potato.ui.direction.DirectionActivity
import com.eight_potato.ui.header.SingleTextHeader
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : DirectionActivity() {
    @Composable
    override fun Body() {
        val start = viewModel.start.collectAsState()
        val end = viewModel.end.collectAsState()

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
                    onClick = {
                        RestListActivity.start(
                            context = this@MainActivity,
                            start = start.value,
                            end = end.value
                        )
                    },
                    backgroundColor = Colors.Black
                ) {
                    Text(text = "이 위치로 주소 등록")
                }
            }
        ) {
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

}