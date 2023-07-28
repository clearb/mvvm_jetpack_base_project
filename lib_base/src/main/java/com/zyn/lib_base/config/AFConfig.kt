package com.zfkj.lib_base.config

import com.tsenf.lib_okhttp.util.IOUtils
import java.io.InputStreamReader
import java.nio.charset.StandardCharsets
import java.util.*

/**
 ************************************************
 * 包路径:com.zfkj.lib_base.config
 * 类描述:app基础常量
 * 创建者 @author:YangJiangLin[PHONE：181****4380]
 * 创建者邮箱:307806546@qq.com
 * 创建时间:2023/3/6 13:55
 * 项目名称:BaseTrenf20220220
 * 项目版本: 1.0
 * 修改人：
 * 修改时间：
 * 修改备注：
 ************************************************
 */
object AFConfig {
    var APP_NAME: String? = null
    var APP_PACKAGE_NAME: String? = null
    var UPYA_KEY_ID: String? = null
    var CHANNEL: String? = null
    var LOG_TAG: String? = null
    var LOG_PRINT = false
    var LOG_WRITE_UPLOAD = false
    var PERMIT_PROXY = false
    /**魔盒蓝牙测试版*/
    var isBoxDebug=true


    fun initValue(logPrint: Boolean, logWriteUpload: Boolean, permitProxy: Boolean) {
        LOG_PRINT = logPrint
        LOG_WRITE_UPLOAD = logWriteUpload
        PERMIT_PROXY = permitProxy
    }

    init {

        var isr: InputStreamReader? = null

        try {
            val properties = Properties()
            properties.load(InputStreamReader(AFConfig::class.java.getResourceAsStream("/assets/AFConfig"), StandardCharsets.UTF_8).also { isr = it })
            APP_NAME = properties.getProperty("appName")
            APP_PACKAGE_NAME = properties.getProperty("appPackageName")
            UPYA_KEY_ID = properties.getProperty("upyaKeyID")
            CHANNEL = properties.getProperty("channel")
            LOG_TAG = properties.getProperty("logTag")
        } catch (var5: Exception) {
            var5.printStackTrace()
            System.exit(0)
        } finally {
            IOUtils.closeQuietly(isr)
        }
    }

}