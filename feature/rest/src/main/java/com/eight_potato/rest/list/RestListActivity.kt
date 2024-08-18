package com.eight_potato.rest.list

import android.content.Context
import android.content.Intent
import androidx.activity.viewModels
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.draggable
import androidx.compose.foundation.gestures.rememberDraggableState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.rememberBottomSheetState
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.rest.R
import com.eight_potato.rest.detail.RestStopDetailActivity
import com.eight_potato.rest.list.ui.RestListBottomSheet
import com.eight_potato.rest.list.ui.RestListHeader
import com.eight_potato.rest.model.RestStopUiModel
import com.eight_potato.rest.model.TEST_REST_STOP
import com.eight_potato.ui.direction.DirectionActivity
import com.eight_potato.ui.ext.dpToPx
import com.eight_potato.ui.map.NaverMap
import com.eight_potato.ui.model.address.AddressUiModel
import com.eight_potato.ui.model.address.PoiUiModel
import com.eight_potato.ui.model.address.toLatLng
import com.eight_potato.ui.util.AnnotatedTextBuilder
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

    // 86 , 48, 0
    @Composable
    override fun Body() {
        val start = viewModel.start.collectAsState()
        val end = viewModel.end.collectAsState()
        val path = restListViewModel.path.collectAsState()
        val restStop = TEST_REST_STOP

        val windowHeight = LocalConfiguration.current.screenHeightDp
        val currentBottomSheetHeightIndex = remember { mutableStateOf(0) }
        val isDragging = remember { mutableStateOf(false) }
        val bottomSheetHeight by animateDpAsState(
            targetValue = (windowHeight * REST_STOP_LIST_HEIGHT_RATE[currentBottomSheetHeightIndex.value]).dp,
            animationSpec = tween(300, easing = LinearOutSlowInEasing)
        )

        val draggableState = rememberDraggableState { delta ->
            if (isDragging.value.not()) {
                isDragging.value = true
                if (delta < 0) {
                    currentBottomSheetHeightIndex.value =
                        (currentBottomSheetHeightIndex.value + 1).coerceAtMost(
                            REST_STOP_LIST_HEIGHT_RATE.lastIndex
                        )
                } else {
                    currentBottomSheetHeightIndex.value =
                        (currentBottomSheetHeightIndex.value - 1).coerceAtLeast(0)
                }
            }
        }

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

        Box {
            NaverMap (modifier = Modifier.fillMaxSize()){
                naverMap = it
                pathOverlay.color = getColor(R.color.main100)
                pathOverlay.width = (6).dpToPx(this@RestListActivity)
                restListViewModel.getDirection(start.value, end.value)
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
            Column(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            ){
                Column(
                    modifier = Modifier
                        .draggable(
                            state = draggableState,
                            orientation = Orientation.Vertical,
                            onDragStopped = { isDragging.value = false }
                        )
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(topStart = 14.dp, topEnd = 14.dp)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_handle_bar),
                        contentDescription = "",
                        tint = Colors.Gray300
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 24.dp),
                        text = AnnotatedTextBuilder.generateTextWithStatus(
                            text = "총 ${restStop.size}개의 휴게소를 들릴 수 있어요",
                            highlightedText = "${restStop.size}개",
                            highlightColor = Colors.Main100
                        ),
                        textAlign = TextAlign.Center,
                        style = Typo.HeadB18
                    )
                }
                RestListBottomSheet(
                    modifier = Modifier.height(bottomSheetHeight),
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
        private val REST_STOP_LIST_HEIGHT_RATE = listOf(0f, 0.48f, 0.86f)
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