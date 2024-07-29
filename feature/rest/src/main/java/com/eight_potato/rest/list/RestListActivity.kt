package com.eight_potato.rest.list

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eight_potato.rest.list.ui.RestListBottomSheet
import com.eight_potato.rest.list.ui.RestListHeader
import com.eight_potato.ui.direction.DirectionActivity
import com.eight_potato.ui.map.NaverMap
import com.eight_potato.ui.model.address.AddressUiModel
import dagger.hilt.android.AndroidEntryPoint

/**
 * 경로 상의 휴게소 리스트를 볼 수 있는 화면
 */
@AndroidEntryPoint
class RestListActivity : DirectionActivity() {
    @Composable
    override fun Body() {
        val start = viewModel.start.collectAsState()
        val end = viewModel.end.collectAsState()

        Box(modifier = Modifier.fillMaxSize()) {
            NaverMap(
                modifier = Modifier.fillMaxSize()
            ) {

            }
            RestListHeader(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(16.dp),
                start = start.value,
                end = end.value,
                onClickStart = ::moveToSearchScreenForStart,
                onClickEnd = ::moveToSearchScreenForEnd
            )
            RestListBottomSheet(
                modifier = Modifier.align(Alignment.BottomStart)
            )
        }
    }

    companion object {
        fun start(
            context: Context,
            start: AddressUiModel?,
            end: AddressUiModel?
        ) {
            val intent = Intent(context, RestListActivity::class.java)
            intent.putExtra(START_ARGS, start)
            intent.putExtra(END_ARGS, end)
            context.startActivity(intent)
        }
    }
}