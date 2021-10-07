package mo.gov.safp.portal.govappbridge

import org.json.JSONArray
import org.json.JSONObject
import java.io.Serializable

internal class CallInfo(json: String?) : Serializable {
    var method: String? = null
    var data: Any? = null
    var success: String? = null
    var fail: String? = null
    var complete: String? = null
    private var hasParams = true // 是否帶參數

    // 如果有回调，则表示为异步
    var isAsyn = false // 是否異步回調

    // 是否有參數
    fun hasParams(): Boolean {
        return hasParams
    }

    init {
        val args = JSONObject(json)
        if (args.has("method")) {
            method = args.getString("method")
        }
        if (args.has("data")) { //獲取參數並檢查參數是否為空
            data = args["data"]
            if (data is JSONArray && (data as JSONArray).length() == 0) {
                hasParams = false
            } else if (data is JSONObject && (data as JSONObject).length() == 0) {
                hasParams = false
            }
        } else {
            hasParams = false
        }
        if (args.has("_initParams")) {
            hasParams = true
        }
        if (args.has("success")) {
            success = args["success"].toString()
            isAsyn = true
        }
        if (args.has("fail")) {
            fail = args["fail"].toString()
            isAsyn = true
        }
        if (args.has("complete")) {
            complete = args["complete"].toString()
            isAsyn = true
        }
        if (args.has("_initCallback")) {
            isAsyn = true
        }
    }
}