package com.teslacode.service.repositories

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

open class Repository<T> {

    protected var api: T? = null

    protected var preferences: SharedPreferences? = null

    constructor() {
        onCreateRetrofit(RetrofitBuilder.builder)
        onCreatePreferences(PreferencesBuilder.preferences)
    }

    protected open fun onCreateRetrofit(builder: RetrofitBuilder?) {
        if (builder == null) {
            return
        }

        val retrofit = builder.onCreate()
        api = onCreateApi(retrofit)
    }

    protected open fun onCreateApi(retrofit: Retrofit): T? {
        return null
    }

    protected open fun onCreatePreferences(preferences: SharedPreferences?) {
        this.preferences = preferences
    }

    protected open fun <T> observe(observable: Observable<T>): Observable<T> {
        return observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
    }
}