package com.adefruandta.service.builders

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by adefruandta on 11/10/17.
 */

abstract class RetrofitBuilder {

    companion object {
        var builder: RetrofitBuilder? = null
    }

    abstract protected var baseUrl: String

    protected open fun onCreateBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(baseUrl)
    }

    fun onCreate(): Retrofit {
        return onCreateBuilder()
                .build()
    }
}
