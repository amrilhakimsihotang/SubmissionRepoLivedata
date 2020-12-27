package com.amrilhakimsihotang.submissionrepolivedata.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class TiviEntity(
        val id: String = "",
        val original_name: String = "",
        val poster_path: String = "",
        val overview: String = "",
        val creatorcast: String = "",
        val seriescast: String = "",
        val writingcast: String = ""
) : Parcelable
