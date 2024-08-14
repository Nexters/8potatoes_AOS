package com.eight_potato.search.location

import android.icu.text.IDNA.Info
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.flowWithLifecycle
import com.eight_potato.designsystem.button.HyusikButton
import com.eight_potato.designsystem.surface.HyusikSurface
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.search.R
import com.eight_potato.ui.base.BaseActivity
import com.eight_potato.ui.header.SingleTextHeader
import com.eight_potato.ui.manager.LocationManager
import com.eight_potato.ui.map.NaverMap
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.LocationSource
import com.naver.maps.map.LocationTrackingMode
import com.naver.maps.map.NaverMap
import com.naver.maps.map.overlay.InfoWindow
import com.naver.maps.map.overlay.InfoWindow.ViewAdapter
import com.naver.maps.map.overlay.Marker
import com.naver.maps.map.overlay.OverlayImage
import com.naver.maps.map.util.FusedLocationSource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class CurrentLocationActivity : BaseActivity() {
    private val viewModel: CurrentLocationViewModel by viewModels()
    private val locationManager by lazy { LocationManager(this) }
    private var naverMap: NaverMap? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationManager.checkLocationPermissionAndShowDialog {
            if (it) setCurrentPosition()
            else finish()
        }
    }

    private fun setCurrentPosition() {
        LocationServices.getFusedLocationProviderClient(this)
            .lastLocation
            .addOnSuccessListener {
                val position = LatLng(it.latitude, it.longitude)
                viewModel.getAddress(position)
                naverMap?.moveCamera(CameraUpdate.scrollTo(position))
            }
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
                    contentPadding = PaddingValues(20.dp),
                    backgroundColor = Colors.Main100
                ) {
                    Text(
                        text = "휴게소 찾기",
                        style = Typo.ButtonB16,
                        color = Colors.White
                    )
                }
            }
        ) { padding ->
            Box(modifier = Modifier.padding(padding)) {
                NaverMap(modifier = Modifier.fillMaxSize()) { naverMap ->
                    val marker = Marker().apply {
                        position = naverMap.cameraPosition.target
                        icon = OverlayImage.fromResource(
                            com.eight_potato.ui.R.drawable.ic_search_current_position
                        )
                        map = naverMap
                    }

                    naverMap.addOnCameraChangeListener { reason, animated ->
                        marker.position = naverMap.cameraPosition.target
                    }
                    naverMap.addOnCameraIdleListener {
                        val position = naverMap.cameraPosition.target
                        viewModel.getAddress(position)
                    }

                    this@CurrentLocationActivity.naverMap = naverMap
                }
                Column (
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.BottomStart)
                ){
                    Image(
                        modifier = Modifier
                            .padding(20.dp)
                            .size(48.dp)
                            .align(Alignment.End)
                            .clickable { setCurrentPosition() },
                        painter = painterResource(id = com.eight_potato.ui.R.drawable.ic_current_position),
                        contentDescription = ""
                    )
                    HyusikSurface(
                        contentPadding = PaddingValues(top = 40.dp, end = 20.dp, bottom = 12.dp, start = 20.dp),
                        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
                        contentColor = Colors.Black
                    ) {
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = address.value?.let {
                                if (it.buildingName.isNotEmpty()) it.buildingName
                                else if (it.fullAddress.isNotEmpty()) it.fullAddress
                                else if (it.oldAddress.isNotEmpty()) it.oldAddress
                                else ""
                            } ?: "",
                            style = Typo.HeadB18,
                            color = Colors.Blk100,
                            textAlign = TextAlign.Start
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
                when (it) {
                    CurrentLocationUiEvent.FailGetAddress -> {
                        toast("위치 정보를 가져오는 도중 문제가 발생하였습니다.")
                    }
                }
            }
    }
}