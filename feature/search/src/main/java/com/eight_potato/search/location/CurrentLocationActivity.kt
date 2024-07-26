package com.eight_potato.search.location

import android.os.Bundle
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.flowWithLifecycle
import com.eight_potato.designsystem.button.HyusikButton
import com.eight_potato.designsystem.surface.HyusikSurface
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.ui.base.BaseActivity
import com.eight_potato.ui.header.SingleTextHeader
import com.eight_potato.ui.map.NaverMap
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.util.FusedLocationSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class CurrentLocationActivity : BaseActivity() {
    private val viewModel: CurrentLocationViewModel by viewModels()
    private lateinit var locationSource: FusedLocationSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationSource = FusedLocationSource(this, 2000)
    }

    @Composable
    override fun Body() {
        val address = viewModel.currentAddress.collectAsState()
        Scaffold(
            topBar = {
                SingleTextHeader(
                    title = "지도에서 위치 확인",
                    onClickCloseButton = { finish() }
                )
            },
            bottomBar = {
                HyusikButton(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .padding(top = 20.dp, bottom = 28.dp)
                        .fillMaxWidth(),
                    onClick = {
                        setResult(RESULT_OK, intent.apply {
                            putExtra("location", viewModel.currentLocation)
                        })
                        finish()
                    },
                    backgroundColor = Colors.Black
                ) {
                    Text(text = "이 위치로 주소 등록")
                }
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                NaverMap(modifier = Modifier.fillMaxSize()) { naverMap ->
                    naverMap.locationSource = locationSource
                    naverMap.locationTrackingMode = LocationTrackingMode.None

                    val marker = Marker().apply {
                        position = naverMap.cameraPosition.target
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
                            text = address.value?.let {
                                it.buildingName.ifBlank { it.fullAddress }
                            } ?: ""
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

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        val result =
            locationSource.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (result) {
            if (!locationSource.isActivated) {
                return
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun observe() {
        super.observe()
        viewModel.uiEvent.flowWithLifecycle(this.lifecycle)
            .onEach {
                when (it) {
                    CurrentLocationUiEvent.FailGetAddress -> {
                        toast("위치 정보를 가져오는 도중 문제가 발생하였습니다.")
                    }
                }
            }
    }
}