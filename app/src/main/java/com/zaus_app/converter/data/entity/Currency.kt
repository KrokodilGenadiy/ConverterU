package com.zaus_app.converter.data.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "cached_currency", indices = [Index(value = ["name"], unique = true)])
data class Currency(
    @PrimaryKey(autoGenerate = true) val table_id: Int = 0,
    @ColumnInfo(name = "id") val ID: String,
    @ColumnInfo(name = "num_code") val NumCode: String,
    @ColumnInfo(name = "char_code")val CharCode: String,
    @ColumnInfo(name = "nominal")val Nominal: Int,
    @ColumnInfo(name = "name") val Name: String,
    @ColumnInfo(name = "value") val Value: Double,
    @ColumnInfo(name = "previous") val Previous: Double
): Parcelable