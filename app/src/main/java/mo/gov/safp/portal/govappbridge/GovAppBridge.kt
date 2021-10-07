package com.alibaba.yihutong.common.h5plugin.govappbridge

import android.os.Build
import android.text.TextUtils
import android.webkit.JavascriptInterface
import com.alipay.mobile.nebula.webview.APWebView
import mo.gov.safp.portal.govappbridge.CallInfo
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method


class GovAppBridge(private val javaScriptListener: JavaScriptListener? = null) {

//
//    // JS： 獲取App當前語言
//    @JavascriptInterface
//
//    fun getCurrentLanaguage(): String? {
//
//        return ""
//    }

//    enum class AppTheme {
//        TRAD, SIMPLE
//    }
//
//    // JS： 獲取app信息
//    @JavascriptInterface
//
//    fun getAppInfo(callback: Callback<String?>) {
////        LogUtil.d(TAG, "getAppInfo")
////        val data = ArrayMap<String, Any>()
////        data["versionName"] = AppInfoUtils.getVersion(PaasGlobalManager.get())
////        data["versionCode"] = AppInfoUtils.getAppVersionCode(PaasGlobalManager.get())
////        data["platform"] = "android" //客戶端平台
////        data["sdkVersion"] = 2 //sdk版本
////        var language = LanguageSPManager.getInstance().language
////        if(language.equals(CHINESE_TRADITIONAL)){
////            language = "zh_TW"
////        }
////        data["language"] = language //app設置語言
////        data["screenWidth"] = screenWidth //屏幕寬度
////        data["screenHeight"] = screenHeight //屏幕高度
////        data["systemVersion"] = Build.VERSION.RELEASE //系統版本
////        data["model"] = Build.MODEL //設備型號
////        data["brand"] = Build.BRAND //設備品牌
////        //深色模式
////        val isDarkModel = isDarkMode(PaasGlobalManager.get())
////        data["theme"] = if (isDarkModel) "dark" else "light"
////        // App主题
////        val appTheme: String = SettingSPManager.getTheme()
////        data["appTheme"] = if (TextUtils.equals(appTheme,ThemeUtil.THEME_TRADITION)) AppTheme.TRAD else AppTheme.SIMPLE
////        try {
////            callback.success(Gson().toJson(data))
////        } catch (ex: java.lang.Exception) {
////            callback.fail("{}")
////        }
//        callback.fail("{}")
//
//    }
//
//    // JS： 檢查是否有Softtoken
//    @JavascriptInterface
//
//    fun checkSoftToken(msg: Any?): String? {
//        return "1"
//    }
//
//    // 獲取軟件令牌
//    @JavascriptInterface
//
//    fun getSoftToken(msg: Any?, callback: Callback<String?>) {
//
//    }
//
//
//    // JS： 請求身份驗證
//    @JavascriptInterface
//
//    fun requestIdentity(msg: Any?, callback: Callback<String?>) {
//
//    }
//
//    // JS： 檢查是否登入
//    @JavascriptInterface
//
//    fun isLogin(): String? {
//        return "1"
//    }
//
//    // JS： 登入
//    @JavascriptInterface
//
//    fun login(msg: Any?, callback: Callback<String?>?) {
//
//    }

//    // JS： 获取Token
//    @JavascriptInterface
//    fun getToken(msg: Any?, callback: Callback<String?>?) {
//        val callbackId = callback?.let { CallbackManager.shared.addCallback(it) }
//        javaScriptListener?.getToken(callbackId)
//    }

//    // JS： 檢查手機是否驗證
//    @JavascriptInterface
//
//    fun hasVerifyMobile(): String? {
//
//        return "1"
//    }
//
//    // JS： 進行手機驗證
//    @JavascriptInterface
//
//    fun verifyMobile(callback: Callback<String?>?) {
//
//    }
//
//    // JS： 獲取手機資料
//    @JavascriptInterface
//
//    fun getPhoneProfile(callback: Callback<String?>?) {
//
//    }
//
//    // JS： 設置屏幕亮度
//    @JavascriptInterface
//    fun setBrightness(msg: Any?) {
//    }
//
//    // JS： 重置屏幕亮度
//    @JavascriptInterface
//    fun resetBrightness() {
//    }
//
//    @JavascriptInterface
//    fun postMessage() {
//    }

    @JavascriptInterface
    fun postMessage(json: String?): String? {
        var error: String? = "Js bridge  called, but can not find a corresponded " + "JavascriptInterface object , please check your code!"
        var callInfo: CallInfo? = null
        try {
            callInfo = CallInfo(json)
        } catch (e: JSONException) {
            error = String.format("The argument of : \"%s\" must be a JSON object string!", json)
            return error
        }
        if (TextUtils.isEmpty(callInfo.method)) {
            error = "Parameter method required!"
            return error
        }
        var method: Method? = null
        val cls: Class<*> = GovAppBridge::class.java
        try {
            method = if (callInfo.isAsyn) {
                if (callInfo.hasParams()) {
                    cls.getMethod(callInfo.method, Any::class.java, Callback::class.java)
                } else {
                    cls.getMethod(callInfo.method, Callback::class.java)
                }
            } else {
                if (callInfo.hasParams()) {
                    cls.getMethod(callInfo.method, Any::class.java)
                } else {
                    cls.getMethod(callInfo.method, *arrayOf<Class<*>>())
                }
            }
        } catch (e: Exception) {
        }
        if (method == null) {
            error = "Not find method \"" + callInfo.method.toString() + "\" implementation! please check if the  signature or namespace of the method is right "
            return error
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val annotation = method.getAnnotation(JavascriptInterface::class.java)
            if (annotation == null) {
                error = "Method " + callInfo.method.toString() + " is not invoked, since  " +
                        "it is not declared with JavascriptInterface annotation! "
                return error
            }
        }
        method.isAccessible = true
        try {
            if (callInfo.isAsyn) {
                val finalCallInfo: CallInfo = callInfo
                val callback = object : Callback<Any?> {
                    override fun success(result: Any?) {
                        evaluateJavascript(true, result)
                    }

                    override fun fail(error: Any?) {
                        evaluateJavascript(false, error)
                    }

                    private fun evaluateJavascript(success: Boolean, obj: Any?) {
                        var result = ""
                        if (obj != null) {
                            result = if (obj is JSONObject || obj is JSONArray) {
                                String.format("JSON.parse(%s)", obj.toString())
                            } else {
                                obj.toString()
                            }
                        }
                        val script = StringBuffer()
                        if (!TextUtils.isEmpty(finalCallInfo.success)) {
                            if (success) {
                                script.append(java.lang.String.format("%s(%s);", finalCallInfo.success, result))
                            }
                            script.append(java.lang.String.format("delete window.%s;", finalCallInfo.success))
                        }
                        if (!TextUtils.isEmpty(finalCallInfo.fail)) {
                            if (!success) {
                                script.append(java.lang.String.format("%s(%s);", finalCallInfo.fail, result))
                            }
                            script.append(java.lang.String.format("delete window.%s;", finalCallInfo.fail))
                        }
                        if (!TextUtils.isEmpty(finalCallInfo.complete)) {
                            script.append(java.lang.String.format("%s();", finalCallInfo.complete))
                            script.append(java.lang.String.format("delete window.%s;", finalCallInfo.complete))
                        }
                        if (javaScriptListener is JavaScriptListener) {
                            javaScriptListener.evaluateJavascript(script.toString())
                        }
                    }
                }
                if (callInfo.hasParams()) {
                    method.invoke(GovAppBridge(javaScriptListener), callInfo.data, callback)
                } else {
                    method.invoke(GovAppBridge(javaScriptListener), callback)
                }
            } else {
                return if (callInfo.hasParams()) {
                    val result = method.invoke(GovAppBridge(javaScriptListener), callInfo.data)
                    result.toString()
                } else {
                    val result = method.invoke(GovAppBridge(javaScriptListener))
                    result.toString()
                }
            }
        } catch (ex: InvocationTargetException) {
            ex.printStackTrace()
            error = if (ex.targetException != null) {
                val buffer = StringBuffer()
                buffer.append("Call failed [ " + callInfo.method.toString() + " ]：")
                buffer.append(ex.targetException.message)
                buffer.toString()
            } else {
                String.format("Call failed：The parameter of \"%s\" in Java is invalid.", callInfo.method)
            }
            return error
        } catch (ex: Exception) {
            ex.printStackTrace()
            val buffer = StringBuffer()
            buffer.append("Call failed [ " + callInfo.method.toString() + " ]：")
            buffer.append(ex.localizedMessage)
            error = buffer.toString()
            return error
        }
        return ""
    }

    companion object {
        private const val TAG = "GovAppBridge"
        const val KEY_IDENTITY = "%s_identifiySecret"
        const val KEY_USERNAME = "username"
        const val INTERVAL = 30L

    }

}