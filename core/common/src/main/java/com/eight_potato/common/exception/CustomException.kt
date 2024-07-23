package com.eight_potato.common.exception

/**
 * 공통 적인 Result Code Exception 관리
 */
open class CustomException(
    override val message: String?,
    val code: Int
) : Exception(message)

enum class CustomExceptionCode {
    NoneDataException, // 0
    NetworkStatusException // 1
}