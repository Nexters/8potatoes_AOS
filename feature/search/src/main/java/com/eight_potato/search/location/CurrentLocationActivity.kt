package com.eight_potato.search.location

import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.flowWithLifecycle
import com.eight_potato.designsystem.surface.HyusikSurface
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.ui.base.BaseActivity
import com.eight_potato.ui.header.SingleTextHeader
import com.eight_potato.ui.map.NaverMap
import com.google.android.gms.location.LocationServices
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.overlay.Marker
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class CurrentLocationActivity : BaseActivity() {
    private val viewModel: CurrentLocationViewModel by viewModels()

    @Composable
    override fun Body() {
        val address = viewModel.currentAddress.collectAsState()
        val locationSource = remember { LocationServices.getFusedLocationProviderClient(this) }
        Scaffold(
            topBar = {
                SingleTextHeader(
                    title = "지도에서 위치 확인",
                    onClickCloseButton = { finish() }
                )
            }
        ) {
            Box(modifier = Modifier.padding(it)) {
                NaverMap(modifier = Modifier.fillMaxSize()) { naverMap ->
                    val marker = Marker().apply {
                        position = LatLng(37.5666102, 126.9783881)
                        map = naverMap
                    }
                    naverMap.addOnCameraChangeListener { reason, animated ->
                        marker.position = naverMap.cameraPosition.target
                    }
                    naverMap.addOnCameraIdleListener {
                        val position = naverMap.cameraPosition.target
                        viewModel.getAddress(position)
                    }
                }
                HyusikSurface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart),
                    shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                    contentColor = Colors.Black
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 20.dp, top = 32.dp, end = 20.dp, bottom = 20.dp)
                    ) {
                        Text(
                            text = address.value?.buildingName ?: ""
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Text(
                            text = address.value?.fullAddress ?: ""
                        )
                    }
                }
            }
        }
    }

    override fun observe() {
        super.observe()
        viewModel.uiEvent.flowWithLifecycle(this.lifecycle)
            .onEach {
                when(it) {
                    CurrentLocationUiEvent.FailGetAddress -> {
                        toast("위치 정보를 가져오는 도중 문제가 발생하였습니다.")
                    }
                }
            }
    }
}