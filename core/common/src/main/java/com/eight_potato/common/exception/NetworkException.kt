package com.eight_potato.common.exception

/**
 * 네트워크와 관련된 Custom Exception
 */
class NoneDataException :
    CustomException(
        "서버로부터 데이터를 받아오는 도중 문제가 발생하였습니다.",
        CustomExceptionCode.NoneDataException.ordinal
    )

/**
 * 400 / 500 / 600 대 에러 발생
 */
class NetworkStatusException(message: String?, code: Int) : CustomException(message, code)