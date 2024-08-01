package com.eight_potato.ui.model.menu

/**
 * 휴게소 메뉴 정보
 */
data class MenuUiModel(
    val id: String, // 메뉴 id
    val name: String, // 이름
    val price: Int, // 가격
    val premium: Boolean, // 프리미엄
    val best: Boolean, // 베스트
    val recommend: Boolean, // 추천
    val menuType: MenuType // 메뉴 타입
)

val TEST_MENU = (0..30).map {
    MenuUiModel(
        id = "$it",
        name = "메뉴 이름 $it",
        price = it * 200,
        premium = it % 2 == 0,
        best = it % 3 == 0,
        recommend = it % 4 == 0,
        menuType = MenuType.KOREAN
    )
}