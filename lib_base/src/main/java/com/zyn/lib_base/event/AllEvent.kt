package com.zfkj.lib_base.event

import android.content.Intent
import com.jeremyliao.liveeventbus.core.LiveEvent


/**
 ************************************************
 * 包路径:com.czl.lib_base.base
 * 类描述:页面通信事件
 * 创建者 @author:YangJiangLin[PHONE：181****4380]
 * 创建者邮箱:307806546@qq.com
 * 创建时间:2023/2/17
 * 项目名称:TsenfBase
 * 项目版本: 1.0
 * 修改人：
 * 修改时间：
 * 修改备注：
 ************************************************
 */


data class NetStateEvent(val netState: Boolean) : LiveEvent

/**
 * 单个包byte
 */
data class SingFileUploadEvent(val byteArray: ByteArray) : LiveEvent


data class OnActivityResultEvent(val requestCode: Int, val resultCode: Int, val data: Intent?) : LiveEvent


/**
 * wifi 服务初始化成功
 */
data class OnWifiInitSuccessEvent(val isSuccess: Boolean) : LiveEvent


/**
 * 监听魔盒连接状态
 */
data class  ObserverBoxConnectEvent(val isConnect:Boolean) : LiveEvent




/**
 * 跳转魔盒详情页面
 */
data class  JumpBoxDetailsEvent(val isJump:Boolean,val isCloseSocket:Boolean=false) : LiveEvent



/**
 * 魔盒异常断开，执行后台重连
 */
data class  ReConnectBoxEvent(val isReStart:Boolean) : LiveEvent