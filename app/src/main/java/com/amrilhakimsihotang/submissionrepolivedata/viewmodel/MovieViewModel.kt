package com.amrilhakimsihotang.submissionrepolivedata.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.amrilhakimsihotang.submissionrepolivedata.data.MovieEntity
import com.amrilhakimsihotang.submissionrepolivedata.data.source.GeneralRepository

class MovieViewModel(private val generalRepository: GeneralRepository) : ViewModel() {
   fun getMovie(): LiveData<List<MovieEntity>> = generalRepository.getAllMovie()
}