package com.eight_potato.rest.list

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.eight_potato.rest.R
import com.eight_potato.rest.detail.RestStopDetailActivity
import com.eight_potato.rest.list.ui.RestListBottomSheet
import com.eight_potato.rest.list.ui.RestListHeader
import com.eight_potato.rest.model.RestStopUiModel
import com.eight_potato.ui.direction.DirectionActivity
import com.eight_potato.ui.ext.dpToPx
import com.eight_potato.ui.map.NaverMap
import com.eight_potato.ui.model.address.AddressUiModel
import com.eight_potato.ui.model.address.PoiUiModel
import com.eight_potato.ui.model.address.toLatLng
import com.naver.maps.geometry.LatLng
import com.naver.maps.geometry.LatLngBounds
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.overlay.PathOverlay
import dagger.hilt.android.AndroidEntryPoint

/**
 * 경로 상의 휴게소 리스트를 볼 수 있는 화면
 */
@AndroidEntryPoint
class RestListActivity : DirectionActivity() {
    private val restListViewModel: RestListViewModel by viewModels()
    private var naverMap: NaverMap? = null
    private val pathOverlay by lazy { PathOverlay() }

    @Composable
    override fun Body() {
        val start = viewModel.start.collectAsState()
        val end = viewModel.end.collectAsState()
        val path = restListViewModel.path.collectAsState()

        LaunchedEffect(path.value) {
            if (path.value.isNotEmpty()) {
                setDirectionAndRest(
                    start = start.value,
                    end = end.value,
                    paths = path.value,
                    restStops = emptyList()
                )
            }
        }

        LaunchedEffect(key1 = start.value, key2 = end.value) {
            restListViewModel.getDirection(start.value, end.value)
        }

        Box(modifier = Modifier.fillMaxSize()) {
            Column {
                NaverMap (modifier = Modifier.weight(1f)){
                    naverMap = it
                    pathOverlay.color = getColor(R.color.main100)
                    pathOverlay.width = (6).dpToPx(this@RestListActivity)
                    restListViewModel.getDirection(start.value, end.value)
                }
                RestListBottomSheet(
                    modifier = Modifier.height(140.dp),
                    onClickRestStop = {
                        startActivity(
                            Intent(
                                this@RestListActivity,
                                RestStopDetailActivity::class.java
                            )
                        )
                    }
                )
            }
            RestListHeader(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(20.dp),
                start = start.value,
                end = end.value,
                onClickStart = ::moveToSearchScreenForStart,
                onClickEnd = ::moveToSearchScreenForEnd
            )
        }
    }

    /**
     * 지도에 경로와 Marker 설정
     * @param start 춟발 위치
     * @param end 도착 위치
     * @param paths 경로
     * @param restStops 휴게소 위치 정보
     */
    private fun setDirectionAndRest(
        start: AddressUiModel?,
        end: AddressUiModel?,
        paths: List<PoiUiModel>,
        restStops: List<RestStopUiModel>
    ) {
        val startPoi = start?.poi?.toLatLng() ?: return
        val endPoi = end?.poi?.toLatLng() ?: return
        // 시작 위치 지정
        startPoi.let {
            Marker().apply {
                position = it
                icon = OverlayImage.fromResource(R.drawable.ic_start_journey)
            }.also { it.map = naverMap }
        }
        // 도착 위치 지정
        endPoi.let {
            Marker().apply {
                position = it
                icon = OverlayImage.fromResource(R.drawable.ic_end_journey)
            }.also { it.map = naverMap }
        }
        pathOverlay.coords = paths.map { it.toLatLng() }.toList()
        pathOverlay.map = naverMap
        // 카메라 위치 변경
        val bound = LatLngBounds(startPoi.toLatLng(), endPoi.toLatLng())
        naverMap?.moveCamera(CameraUpdate.fitBounds(bound, 120))
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