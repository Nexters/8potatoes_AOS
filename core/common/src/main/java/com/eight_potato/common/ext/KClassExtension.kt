package com.eight_potato.common.ext

import kotlin.reflect.KClass

inline fun <reified T: Any> KClass<out T>.createInstance(): T {
    return java.newInstance()
}