package com.alibaba.yihutong.common.h5plugin.govappbridge

import java.util.*

class CallbackManager<T> {
    private val cacheCallbacks: MutableMap<String, Callback<T>?> = LinkedHashMap()
    fun getCallback(id: String?): Callback<T>? {
        if (id == null) {
            return null
        }
        val callback = cacheCallbacks[id]
        if (callback != null) {
            cacheCallbacks.remove(id)
        }
        return callback
    }

    fun addCallback(callback: Callback<T>): String {
        val callbackId = buildCallbackId()
        setCallback(callbackId, callback)
        return callbackId
    }

    private fun setCallback(id: String, callback: Callback<T>) {
        cacheCallbacks[id] = callback
    }

    fun hasCallback(id: String): Boolean {
        return cacheCallbacks[id] != null
    }

    fun clear() {
        cacheCallbacks.clear()
    }

    companion object {
        var shared = CallbackManager<String?>()
        private fun buildCallbackId(): String {
            return UUID.randomUUID().toString().replace("-".toRegex(), "")
        }
    }
}