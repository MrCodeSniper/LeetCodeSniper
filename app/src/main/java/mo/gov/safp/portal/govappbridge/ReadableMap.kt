package com.alibaba.yihutong.common.h5plugin.govappbridge

import android.net.Uri
import mo.gov.safp.portal.govappbridge.JsonUtils
import java.util.*

class ReadableMap private constructor(map: Map<String, Any?>) {
    val data: Map<String, Any?>?
    val isEmpty: Boolean
        get() = data == null || data.isEmpty()

    fun getDouble(name: String): Double {
        return getDouble(name, 0.00)
    }

    fun getDouble(name: String, defaultVal: Double): Double {
        if (data!!.isEmpty()) {
            return defaultVal
        }
        val obj = data[name]
        if (obj != null) {
            if (obj is Float) {
                return java.lang.Double.valueOf(obj.toString())
            } else if (obj is Double) {
                return java.lang.Double.valueOf(obj.toString())
            } else if (obj is Number) {
                return obj.toDouble()
            }
        }
        return if (obj != null && obj is Double) obj else defaultVal
    }



    fun getValue(name:String):Any?{
        return data?.get(name)
    }


    fun hasKey(name: String): Boolean {
        return !data!!.isEmpty() && data[name] != null
    }

    fun getBoolean(name: String): Boolean {
        return this.getBoolean(name, false)
    }

    fun getBoolean(name: String, defaultVal: Boolean): Boolean {
        if (data!!.isEmpty()) {
            return defaultVal
        }
        val obj = data[name]
        return if (obj != null && obj is Boolean) obj else defaultVal
    }

    fun getInt(name: String): Int {
        return getInt(name, 0)
    }

    fun getInt(name: String, defaultVal: Int): Int {
        if (data!!.isEmpty()) {
            return defaultVal
        }
        val obj = data[name]
        if (obj != null) {
            if (obj is Int) {
                return obj
            } else if (obj is Long) {
                return java.lang.Long.valueOf(obj.toString()).toInt()
            }
        }
        return if (obj != null && obj is Int) obj else defaultVal
    }

    fun getFloat(name: String): Float {
        return getFloat(name, 0.0f)
    }

    fun getFloat(name: String, defaultVal: Float): Float {
        if (data!!.isEmpty()) {
            return defaultVal
        }
        val obj = data[name]
        if (obj != null) {
            if (obj is Float) {
                return obj
            } else if (obj is Double) {
                return java.lang.Double.valueOf(obj.toString()).toFloat()
            } else if (obj is Number) {
                return obj.toFloat()
            }
        }
        return if (obj != null && obj is Float) obj else defaultVal
    }

    fun getString(name: String): String {
        return getString(name, null)
    }

    fun getString(name: String, defaultVal: String?): String {
        val obj = data!![name]
        return obj?.toString() ?: defaultVal!!
    }

    fun getStringList(name: String): ArrayList<String>? {
        if (data!!.isEmpty()) {
            return null
        }
        val obj = data[name]
        if (obj is List<*>) {
            val list = ArrayList<String>()
            for (`val` in obj as List<Any>) {
                list.add(`val`.toString())
            }
            return list
        }
        return null
    }

    fun getList(name: String): List<Any>? {
        if (data!!.isEmpty()) {
            return null
        }
        val obj = data[name]
        return if (obj is List<*>) {
            obj as List<Any>?
        } else null
    }

    fun getMap(name: String): Map<String, Any>? {
        if (data!!.isEmpty()) {
            return null
        }
        val obj = data[name]
        return if (obj is Map<*, *>) {
            obj as Map<String, Any>?
        } else null
    }

    companion object {
        fun readMap(obj: Any?): ReadableMap {
            return readMap(obj?.toString() ?: "{}")
        }

        fun readMap(json: String?): ReadableMap {
            return ReadableMap(JsonUtils.toMap(json))
        }

        fun readMap(uri: Uri): ReadableMap {
            val queryParameters: MutableMap<String, Any?> = HashMap()
            for (name in uri.queryParameterNames) {
                queryParameters[name] = uri.getQueryParameter(name)
            }
            return ReadableMap(queryParameters)
        }
    }

    init {
        data = map
    }
}