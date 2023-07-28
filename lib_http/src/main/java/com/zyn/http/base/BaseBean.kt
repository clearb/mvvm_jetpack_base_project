package com.zyn.http.base

import java.io.Serializable


/**
 ************************************************
 * 项目版本: 1.0
 * 修改人：
 * 修改时间：
 * 修改备注：
 ************************************************
 */
open class BaseBean<T> : Serializable {
    var data: T? = null
    var code: Int = 0
    var msg: String? = null


    companion object {
        private const val serialVersionUID = 5223230387175917834L

        fun <T> newFailed(code: Int = -1, msg: String? = null): BaseBean<T> {
            return BaseBean<T>().apply { this.code = code;this.msg = msg }
        }
        fun <T> newSuccess(data: T? = null): BaseBean<T> {
            return BaseBean<T>().apply { this.data = data }
        }
    }

    override fun toString(): String {
        return "BaseBean(data=$data, code=$code, msg=$msg)"
    }
    fun isSuccess(): Boolean {
        return this.code == 200
    }

    fun isTimeout(): Boolean {
        return this.code == 15003
    }
}
