package com.alibaba.yihutong.common.h5plugin.govappbridge

interface JavaScriptListener {

    fun evaluateJavascript(script: String?)

    fun getToken(callbackId: String?);
}