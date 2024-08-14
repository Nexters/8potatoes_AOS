package com.eight_potato.ui.permission.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.eight_potato.designsystem.button.HyusikButton
import com.eight_potato.designsystem.surface.HyusikSurface
import com.eight_potato.designsystem.theme.Colors
import com.eight_potato.designsystem.theme.HyusikMatjuTheme
import com.eight_potato.designsystem.theme.Typo
import com.eight_potato.ui.R

@Composable
internal fun RequestPermissionBody(
    modifier: Modifier = Modifier,
    onClickConfirm: () -> Unit
) {
    HyusikSurface (
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 20.dp),
        shape = RoundedCornerShape(20.dp)
    ){
        Column (
            modifier = Modifier.padding(top = 40.dp, bottom = 28.dp)
        ){
            Text(
                text = "휴식맛쥬 앱 이용을 위한 권한 안내",
                style = Typo.HeadB18,
                color = Colors.Blk100
            )
            Spacer(modifier = Modifier.height(32.dp))
            RequestPermissionItem(
                icon = R.drawable.ic_essential_location,
                title = "필수적 접근 권한",
                desc = "필수적 접근 권한 없음"
            )
            RequestPermissionItem(
                icon = R.drawable.ic_normal_location,
                title = "선택적 접근 권한",
                subTitle = "위치 정보",
                desc = "사용자 주변의 휴게소 검색"
            )
            Spacer(modifier = Modifier.height(32.dp))
            Text(
                text = "• 선택적 접근 권한은 관련 기능 이용 혹은 접근 시 동의 하실 수 있으며, " +
                        "미동의 시에도 해당 기능 외 앱 서비스는 이용이 가능합니다.",
                style = Typo.SmallM12,
                color = Colors.Blk40
            )
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "• 휴대폰 [설정 > 어플리케이션 관리 > 휴식맛쥬 > 앱 권한]에서도" +
                        " 설정을 변경하실 수 있습니다.",
                style = Typo.SmallM12,
                color = Colors.Blk40
            )
            Spacer(modifier = Modifier.height(32.dp))
            HyusikButton(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(16.dp),
                backgroundColor = Colors.Main100,
                contentPadding = PaddingValues(vertical = 18.dp, horizontal = 10.dp),
                onClick = onClickConfirm
            ) {
                Text(
                    text = "확인하기",
                    style = Typo.ButtonB14
                )
            }
        }
    }
}

@Composable
private fun RequestPermissionItem(
    modifier: Modifier = Modifier,
    @DrawableRes icon: Int,
    title: String,
    subTitle: String = "",
    desc: String
) {
    Column (modifier){
        Text(
            text = title,
            style = Typo.BodySB14,
            color = Colors.Blk100
        )
        Spacer(modifier = Modifier.height(20.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Image(
                modifier = Modifier.size(48.dp),
                painter = painterResource(id = icon),
                contentDescription =""
            )
            Column (
                modifier = Modifier.padding(start = 12.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ){
                if (subTitle.isNotBlank()) {
                    Text(
                        text = subTitle,
                        style = Typo.SmallB12,
                        color = Colors.Blk100
                    )
                }
                Text(
                    text = desc,
                    style = Typo.SmallM12,
                    color = Colors.Blk100
                )
            }
        }
    }
}

@Preview
@Composable
private fun RequestPermissionBodyPreview() {
    HyusikMatjuTheme {
        RequestPermissionBody(onClickConfirm = {})
    }
}