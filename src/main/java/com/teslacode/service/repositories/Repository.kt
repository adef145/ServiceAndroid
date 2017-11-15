package com.teslacode.service.repositories

import android.content.Context
import android.content.SharedPreferences
import com.teslacode.service.builders.PreferencesBuilder
import com.teslacode.service.builders.RetrofitBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

/**
 * Created by adefruandta on 11/10/17.
 */

open class Repository<A> {

    companion object {
        var context: Context? = null
    }

    protected var service: A? = null

    protected var preferences: SharedPreferences? = null

    protected open var serviceClass: Class<A>? = null

    constructor() {
        onCreateRetrofit(RetrofitBuilder.builder)
        onCreatePreferences(PreferencesBuilder.preferences)
    }

    protected open fun onCreateRetrofit(builder: RetrofitBuilder?) {
        if (builder == null) {
            return
        }

        val retrofit = builder.onCreate()
        onCreateService(retrofit)
    }

    protected open fun onCreateService(retrofit: Retrofit?) {
        if (this.serviceClass == null || retrofit == null) {
            return
        }

        this.service = retrofit.create(this.serviceClass)
    }

    protected open fun onCreatePreferences(preferences: SharedPreferences?) {
        this.preferences = preferences
    }

    protected fun <T> observe(observable: Observable<T>): Observable<T> {
        return observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
    }
}