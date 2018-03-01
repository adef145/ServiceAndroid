package com.adefruandta.service.repositories

import android.content.SharedPreferences
import com.adefruandta.service.builders.PreferencesBuilder
import com.adefruandta.service.builders.RetrofitBuilder
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit

/**
 * Created by adefruandta on 11/10/17.
 */

open class Repository<T> {

    open var api: T? = null
        get() {
            if (field == null) {
                field = onCreateApi(onCreateRetrofit(onCreateRetrofitBuilder()))
            }

            return field
        }

    open var preferences: SharedPreferences? = null
        get() {
            if (field == null) {
                field = onCreatePreferences()
            }

            return field
        }

    open fun invalidate() {
        api = null
        preferences = null
    }

    open fun onCreateRetrofitBuilder(): RetrofitBuilder? = RetrofitBuilder.builder

    open fun onCreatePreferences(): SharedPreferences? = PreferencesBuilder.preferences

    open fun onCreateRetrofit(builder: RetrofitBuilder?): Retrofit? {
        if (builder == null) {
            return null
        }

        return builder.onCreate()
    }

    open fun onCreateApi(retrofit: Retrofit?): T? = null

    open fun <T> observe(observable: Observable<T>): Observable<T> {
        return observable
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
    }
}