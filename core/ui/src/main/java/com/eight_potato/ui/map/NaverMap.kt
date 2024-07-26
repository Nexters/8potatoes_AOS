package com.eight_potato.ui.map

import android.content.Context
import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.naver.maps.map.MapView
import com.naver.maps.map.OnMapReadyCallback
import kotlinx.coroutines.launch

@Composable
fun NaverMap(
    modifier: Modifier = Modifier,
    context: Context = LocalContext.current,
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onMapReadCallback: OnMapReadyCallback
) {
    val coroutineScope = rememberCoroutineScope()
    val naverMap = remember {
        MapView(context).apply {
            getMapAsync(onMapReadCallback)
        }
    }

    val lifecycleObserver = remember {
        LifecycleEventObserver { source, event ->
            coroutineScope.launch {
                when (event) {
                    Lifecycle.Event.ON_CREATE -> naverMap.onCreate(Bundle())
                    Lifecycle.Event.ON_START -> naverMap.onStart()
                    Lifecycle.Event.ON_RESUME -> naverMap.onResume()
                    Lifecycle.Event.ON_PAUSE -> naverMap.onPause()
                    Lifecycle.Event.ON_STOP -> naverMap.onStop()
                    Lifecycle.Event.ON_DESTROY -> naverMap.onDestroy()
                    else -> {}
                }
            }
        }
    }

    DisposableEffect(true) {
        lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleObserver)
        }
    }
    AndroidView(
        modifier = modifier,
        factory = { naverMap }
    )
}