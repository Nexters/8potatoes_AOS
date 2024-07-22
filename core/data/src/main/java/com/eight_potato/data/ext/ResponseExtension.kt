package com.eight_potato.data.ext

import com.eight_potato.common.exception.NetworkStatusException
import com.eight_potato.common.exception.NoneDataException
import com.eight_potato.common.ext.createInstance
import retrofit2.Response
import kotlin.reflect.KClass

/**
 * Response에서 body 추출
 */
internal fun <T> Response<T>.getBodyOrThrow(): T {
    if (isSuccessful.not() || body() == null) throw NoneDataException()
    return body()!!
}

/**
 * Response의 Error Code와 Exception 관리
 */
internal fun <T> checkResponseIsSuccess(
    response: Response<T>,
    errorCodes: Map<Int, KClass<out Exception>>?
) {
    val message = response.message()
    val code = response.code()
    if (code != 200) {
        throw errorCodes?.get(code)?.createInstance()
            ?: NetworkStatusException(
                message = message,
                code = code
            )
    }
}