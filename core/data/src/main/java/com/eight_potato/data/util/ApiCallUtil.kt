package com.eight_potato.data.util

import com.eight_potato.data.ext.checkResponseIsSuccess
import com.eight_potato.data.ext.getBodyOrThrow
import retrofit2.Response
import kotlin.reflect.KClass

/**
 * API 호출 및 Response 검증 Util
 */
object ApiCallUtil {
    suspend operator fun <T> invoke(
        errorCodes: Map<Int, KClass<out Exception>>? = null,
        action: suspend () -> Response<T>
    ): T {
        // 1. Response 코드 검사
        val response = action()
        checkResponseIsSuccess(response, errorCodes)
        // 2. Body 검사
        return response.getBodyOrThrow()
    }
}