package com.teslacode.service.builders

import retrofit2.Retrofit

/**
 * Created by adefruandta on 11/10/17.
 */

abstract class RetrofitBuilder {

    companion object {
        var builder: RetrofitBuilder? = null
    }

    abstract protected var baseUrl: String

    protected fun onCreateBuilder(): Retrofit.Builder {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
    }

    fun onCreate(): Retrofit {
        return onCreateBuilder()
                .build()
    }
}
