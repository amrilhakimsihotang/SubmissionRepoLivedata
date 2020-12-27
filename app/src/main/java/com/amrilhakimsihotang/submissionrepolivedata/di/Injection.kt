package com.amrilhakimsihotang.submissionrepolivedata.di

import android.content.Context
import com.amrilhakimsihotang.submissionrepolivedata.data.source.GeneralRepository
import com.amrilhakimsihotang.submissionrepolivedata.data.source.remote.RemoteDataSource
import com.amrilhakimsihotang.submissionrepolivedata.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): GeneralRepository{
        val remoreDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        return GeneralRepository.getInstance(remoreDataSource)
    }
}