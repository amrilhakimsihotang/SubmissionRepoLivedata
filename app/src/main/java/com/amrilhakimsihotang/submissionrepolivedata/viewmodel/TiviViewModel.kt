package com.amrilhakimsihotang.submissionrepolivedata.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.amrilhakimsihotang.submissionrepolivedata.data.TiviEntity
import com.amrilhakimsihotang.submissionrepolivedata.data.source.GeneralRepository

class TiviViewModel(private val generalRepository: GeneralRepository): ViewModel() {
    fun getTivishow(): LiveData<List<TiviEntity>> = generalRepository.getAllTivi()

}