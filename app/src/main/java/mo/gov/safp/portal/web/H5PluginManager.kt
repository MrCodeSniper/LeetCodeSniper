package mo.gov.safp.portal.web

import com.alibaba.fastjson.JSON
import com.alibaba.fastjson.JSONObject
//import com.alibaba.yihutong.common.ServiceProvi
import com.alipay.mobile.h5container.api.H5BridgeContext
import com.alipay.mobile.h5container.api.H5CallBack
import com.alipay.mobile.h5container.api.H5Event
import com.alipay.mobile.h5container.api.H5Page
import com.alipay.mobile.h5container.service.H5Service
import com.mpaas.framework.adapter.api.MPFramework

/**
 * native向js发起通信
 *
 * @param api native与js通信的api
 * @param toJs native向js发送的数据，传相关数据类，须提前设置好值
 * @param callBack 通信成功后h5的回调
 */
fun <T> sendMessageToH5(api: String, toJs: T, callBack: H5CallBack?) {
    val h5Service = MPFramework.getMicroApplicationContext()
        .findServiceByInterface<H5Service>(H5Service::class.java.name)
    val h5Page = h5Service?.topH5Page
    val callToJs: JSONObject = JSON.toJSON(toJs) as JSONObject
    h5Page?.bridge?.sendDataWarpToWeb(api, callToJs, callBack)
}

fun <T> H5Page?.sendMessageToH5(api: String, toJs: T, callBack: H5CallBack?) {
    val callToJs: JSONObject = JSON.toJSON(toJs) as JSONObject
    this?.bridge?.sendDataWarpToWeb(api, callToJs, callBack)
}


/**
 * 发送给所有容器
 */
@JvmOverloads
fun sendMessageToAllH5(api: String, jsonObj: JSONObject? = null, h5CallBack: H5CallBack? = null) {
//    H5CollectionPlugin.sendMessageToAllH5(api, jsonObj, h5CallBack)
}

/**
 * js向native发起通信
 *
 * @param event h5发送的event事件，通信数据以json格式存储，通过getParam方法可取出
 * @param context H5BridgeContext，向js发送通信结果
 * @param fromJs js发送过来的数据，传数据类的Class即可，接口会自动转化为相关数据类
 * @param toJs 向js发送的数据，传相关数据类，须提前设置好值
 */
fun <K, T> parseH5MsgAndCallback(
    event: H5Event,
    context: H5BridgeContext?,
    fromJs: Class<K>?,
    toJs: T?
): K? {
    var dataFromJs: K? = null
    if (fromJs != null) {
        val params = event.param.toJSONString()
        //这行代码会引发两个问题
        //1.个别机型会将Map<String,String> 里面的String转换为JSONObject(params) 导致JSONObject无法转为String
        //2.会报kotlin data class have not constructor
//        dataFromJs = params.toJavaObject(fromJs)

        try {
            dataFromJs = JSON.parseObject(params, fromJs)
        } catch (e: Exception) {
//            ServiceProvider.getLoggerService()?.error(TAG, "json parse error")
        }
//        ServiceProvider.getLoggerService()?.debug(TAG, "js2Native... dataFromJs: $dataFromJs")
    }

    if (toJs != null) {
        val resultToJs = JSON.toJSON(toJs) as JSONObject
//        ServiceProvider.getLoggerService()?.debug(TAG, "js2Native... resultToJs: $resultToJs")
        context?.sendBridgeResult(resultToJs)
    }
    return dataFromJs
}

@JvmOverloads
fun <T> parseH5Message(event: H5Event, context: H5BridgeContext?, fromJs: Class<T>?): T? {
    try {
        return parseH5MsgAndCallback(event, context, fromJs, null)
    } catch (e: Exception) {
//        ServiceProvider.getLoggerService()?.error(TAG, "parse js param error:${e.message}")
    }
    return null
}

/**
 * 单独反馈消息给js
 */
fun <T> callbackToH5(context: H5BridgeContext?, toJs: T?) {
    if (toJs != null) {
        val resultToJs = JSON.toJSON(toJs) as JSONObject
        context?.sendBridgeResult(resultToJs)
    }
}


fun <T> actionCallbackToH5(context: H5BridgeContext?, toJs: T?, action: String? = null) {
    if (toJs != null) {
        val resultToJs = JSON.toJSON(toJs) as JSONObject
        context?.sendBridgeResult(resultToJs)
    }
}

fun js2Native(context: H5BridgeContext?) {
    context?.sendBridgeResult(JSONObject())
}
//
//fun logJsException(context: H5BridgeContext?, exception: String?) {
//    val h5Response: H5Response<String> = H5Response();
//    h5Response.success = false
//    h5Response.message = exception
//    callbackToH5(context, h5Response)
//    ServiceProvider.getLoggerService()?.error(TAG, "logJsException... exception is : $exception")
//}
//
//fun logJsException(context: H5BridgeContext?, result: ResultContainer<*>?) {
//    val h5Response: H5Response<String> = H5Response();
//    h5Response.success = false
//    h5Response.message = result?.message ?: ""
//    h5Response.code = result?.code ?: 0
//    callbackToH5(context, h5Response)
//}

fun logJsException(context: H5BridgeContext?, exception: String?, code: Int) {
    val h5Response: H5Response<String> = H5Response();
    h5Response.success = false
    h5Response.message = exception
    h5Response.code = code
    callbackToH5(context, h5Response)
}

fun log2JS(context: H5BridgeContext?, success: Boolean, data: String?, code: Int) {
    val h5Response: H5Response<String> = H5Response();
    h5Response.success = success
    h5Response.data = data
    h5Response.code = code
    callbackToH5(context, h5Response)
}

@Deprecated("type not safe", replaceWith = ReplaceWith("use GsonUtils instead"))
fun <T> parse2JavaObject(json: String?, clazz: Class<T>): T? {
    return if (json != null) {
        JSONObject.toJavaObject(JSON.parseObject(json), clazz)
    } else null
}

fun <T> parse2JSONObject(json: T?): JSONObject? {
    if (json != null) {
        return JSON.toJSON(json) as JSONObject
    }
    return null
}