package com.eight_potato.rest.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.rest.databinding.ItemReststopPointBinding
import com.eight_potato.rest.model.RestStopUiModel
import com.naver.maps.map.overlay.InfoWindow

class RestStopPointAdapter(
    private val context: Context,
    private val restStopUiModel: RestStopUiModel
): InfoWindow.DefaultViewAdapter(context) {
    override fun getContentView(infoWindow: InfoWindow): View {
        val item = ItemReststopPointBinding.inflate(
            LayoutInflater.from(context)
        )

        item.root.setContent { RestStopItem(restStopUiModel = restStopUiModel) }

        return item.root
    }
}

@Composable
private fun RestStopItem(
    modifier: Modifier = Modifier,
    restStopUiModel: RestStopUiModel
) {
    Box(modifier = modifier) {
        Text(
            modifier = Modifier
                .border(
                    1.dp, Colors.Main100, RoundedCornerShape(8.dp)
                )
                .background(
                    if (restStopUiModel.isRecommend) Colors.Main100 else Colors.White,
                    RoundedCornerShape(8.dp)
                )
                .padding(12.dp),
            text = restStopUiModel.name,
            style = Typo.BodyB14,
            color = Colors.Main100
        )
    }
}