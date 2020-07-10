package org.dreamless


/**
 * 强制指定返回类型
 */
data class ApiResult<T>(
    val code: Int,
    val msg: String,
    val data: T? = null,
    val success: Boolean = code == 0
) {
    companion object {
        fun <T> success(data: T?): ApiResult<T> {
            return ApiResult<T>(0, "", data)
        }

        fun <T> failed(msg: String): ApiResult<T> {
            return ApiResult<T>(code = 1, msg = msg)
        }
    }
}
