package com.adefruandta.service.configs

import com.raizlabs.android.dbflow.converter.TypeConverter
import java.util.*
import com.raizlabs.android.dbflow.annotation.TypeConverter as TypeConverterAnnotation

/**
 * Created by adefruandta on 10/19/17.
 */

@TypeConverterAnnotation
class DateConverter : TypeConverter<Long, Date>() {

    override fun getDBValue(model: Date): Long? {
        return model.time
    }

    override fun getModelValue(data: Long?): Date {
        return if (data == null) {
            Date()
        } else {
            Date(data)
        }
    }

}

