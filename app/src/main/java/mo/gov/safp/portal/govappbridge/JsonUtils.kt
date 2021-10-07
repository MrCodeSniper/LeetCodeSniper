package mo.gov.safp.portal.govappbridge

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


object JsonUtils {

    @JvmStatic
    fun toJsonString(obj: Any?): String {
        obj?.let {
            with(Gson()) {
                return toJson(it)
            }
        }

        return ""
    }

    fun <T> fromJson2List(json: String?): List<T>? {
        return Gson().fromJson(json, object : TypeToken<List<T>>() {}.type)
    }


    fun getInstance(): Gson = Gson()


    fun toMap(json: String?): Map<String, Any?> {
        return getInstance().fromJson(json, object : TypeToken<Map<String, Any?>?>() {}.type)
    }
}