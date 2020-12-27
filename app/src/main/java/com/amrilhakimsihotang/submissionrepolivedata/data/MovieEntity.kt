package com.amrilhakimsihotang.submissionrepolivedata.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class MovieEntity(
        val id: String,
        val original_title: String,
        val poster_path: String,
        val overview: String,
        val director:String,
        val writer:String,
        val screenplay:String,
        val person_cast:String
): Parcelable
