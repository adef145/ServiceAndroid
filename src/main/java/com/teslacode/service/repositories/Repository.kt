package com.teslacode.service.repositories

import android.content.Context
import android.content.SharedPreferences
import com.teslacode.service.api.Api
import com.teslacode.service.builders.PreferencesBuilder
import com.teslacode.service.builders.RetrofitBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

/**
 * Created by adefruandta on 11/10/17.
 */

class Repository<A : Api> {

    companion object {
        var context: Context? = null
    }

    protected var retrofit: Retrofit? = null

    protected var service: A? = null

    protected var serviceClass: Class<A>? = null

    protected var preferences: SharedPreferences? = null

    constructor() {
        onCreateRetrofit(RetrofitBuilder.builder)
        onCreatePreferences(PreferencesBuilder.preferences)
    }

    protected fun onCreateRetrofit(builder: RetrofitBuilder?) {
        if (builder == null) {
            return
        }

        this.retrofit = builder.onCreate()
        onCreateService(this.retrofit)
    }

    protected fun onCreateService(retrofit: Retrofit?) {
        if (this.serviceClass == null || retrofit == null) {
            return
        }

        this.service = retrofit.create(this.serviceClass)
    }

    protected fun onCreatePreferences(preferences: SharedPreferences?) {
        this.preferences = preferences
    }

    protected fun observe(observable: Observable<*>): Observable<*> {
        return observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
    }
}