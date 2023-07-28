package com.zyn.http.net.cookie

import com.blankj.utilcode.util.Utils
import com.zyn.http.interceptor.log.AndroidLoggingInterceptor
import com.zyn.http.net.cookie.store.PersistentCookieStore
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * packageName  : com.zyn.http.net.cookie
 * classDescribe: Retrofit client 创建
 * author       : zanyanneng
 * email        : 2529240409@qq.com
 * createTime   : 2023/7/26 10:34
 * projectName  : MyBaseProject
 *
 **/
object RetrofitManager  {
    private val mOkClient = OkHttpClient.Builder()
        .callTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .cookieJar(CookieJarImpl(PersistentCookieStore(Utils.getApp().applicationContext)))
        .addInterceptor(AndroidLoggingInterceptor.build()).build()
    private var mRetrofit: Retrofit? = null


    fun initRetrofit(): RetrofitManager {
        mRetrofit = Retrofit.Builder()
            .baseUrl("https://www.wanandroid.com")
            .client(mOkClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return this
    }

    fun <T> getService(serviceClass: Class<T>): T {
        if (mRetrofit == null) {
            throw UninitializedPropertyAccessException("Retrofit必须初始化")
        } else {
            return mRetrofit!!.create(serviceClass)
        }
    }
}