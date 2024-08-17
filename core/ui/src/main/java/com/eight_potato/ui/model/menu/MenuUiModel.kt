package com.eight_potato.ui.model.menu

/**
 * 휴게소 메뉴 정보
 */
data class MenuUiModel(
    val id: String, // 메뉴 id
    val name: String, // 이름
    val price: Int, // 가격
    val signature: Boolean, // 시그니처
    val popular: Boolean, // 인기
    val menuType: MenuType // 메뉴 타입
)

val TEST_MENU = (0..30).map {
    MenuUiModel(
        id = "$it",
        name = "메뉴 이름 $it",
        price = it * 200,
        signature = it % 2 == 0,
        popular = it % 3 == 0,
        menuType = if (it % 4 == 0) MenuType.KOREAN
        else if (it % 3 == 0) MenuType.WESTERN
        else MenuType.SNACK
    )
}