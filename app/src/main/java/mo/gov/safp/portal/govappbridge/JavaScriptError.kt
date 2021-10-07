package com.alibaba.yihutong.common.h5plugin.govappbridge

// JS Api错误
enum class JavaScriptError(val value: String) {
    METHOD_NOT_FOUND("無法調用接口"), NO_ACCESS("禁止調用接口"), INVALID_PARAMETER("參數錯誤"), UNEXPECTED_RESULT("無法獲取數據"), NOT_AUTHENTICATED("未登入"), ACCESS_DENIED("登入已失效"), CLIENT_ERROR("無法調用服務"), SERVICE_ERROR("無法調用服務"), PERMISSION_DENIED("拒絕權限申請"), NETWORK_ERROR("網絡錯誤"), UNKNOWN_ERROR("未知錯誤");
}