package com.eight_potato.ui.model.menu

import com.eight_potato.domain.model.reststop.MenuModel

/**
 * 휴게소 메뉴 정보
 */
data class MenuUiModel(
    val name: String, // 이름
    val price: Int, // 가격
    val signature: Boolean, // 시그니처
    val popular: Boolean, // 인기
    val menuType: MenuType // 메뉴 타입
)

fun MenuModel.toUiModel(): MenuUiModel {
    return MenuUiModel(
        name = menuName,
        price = menuPrice,
        signature = isSignatureMenu,
        popular = isPopularMenu,
        menuType = MenuType.getMenuTypeByText(category)
    )
}