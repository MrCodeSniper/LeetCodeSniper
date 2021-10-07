package com.alibaba.yihutong.common.h5plugin.govappbridge

interface Callback<T> {
    fun success(result: T)

    fun fail(error: T)
}