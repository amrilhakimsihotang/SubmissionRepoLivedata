package com.amrilhakimsihotang.submissionrepolivedata.data.source

import androidx.lifecycle.LiveData
import com.amrilhakimsihotang.submissionrepolivedata.data.MovieEntity
import com.amrilhakimsihotang.submissionrepolivedata.data.TiviEntity

interface GeneralDataSource {
    fun getAllMovie(): LiveData<List<MovieEntity>>
    fun getAllTivi() : LiveData<List<TiviEntity>>
}