package com.zfkj.lib_base.config

import com.blankj.utilcode.util.PathUtils
import java.io.File

/**
 * Description: 目录配置
 * author       : baoyuedong
 * email        : baoyuedong@tsenf.io
 * createTime   : 2023/6/1 19:16
 **/
object DirConfig {
    //临时目录
    val ROOT_TEMP = PathUtils.getInternalAppCachePath() + File.separator + "temp"

    //APP文件夹
    val ROOT_APPFILES = PathUtils.getInternalAppCachePath() + File.separator + "appspace"

    val ROOT_IM = ROOT_APPFILES + File.separator + "IM"
    val ROOT_IM_AUDIO = ROOT_IM + File.separator + "AUDIO"
    val ROOT_IM_VIDEO = ROOT_IM + File.separator + "VIDEO"
    val ROOT_IM_IMAGE = ROOT_IM + File.separator + "IMAGE"
    val ROOT_IM_DOCUMENT = ROOT_IM + File.separator + "DOC"
    val ROOT_IM_OTHER = ROOT_IM + File.separator + "OTHER"

    //魔盒空间
    val ROOT_MAGICBOXSPACE = "/"

    //魔盒空间-本地
    val ROOT_MAGIXBOXSPACE_LOCAL = PathUtils.getInternalAppCachePath() + File.separator + "space"
}