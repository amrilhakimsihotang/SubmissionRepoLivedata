package com.amrilhakimsihotang.submissionrepolivedata.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.amrilhakimsihotang.submissionrepolivedata.data.source.GeneralRepository
import com.amrilhakimsihotang.submissionrepolivedata.di.Injection

class ViewModelFactory private constructor(private val mGeneralRepository: GeneralRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
                instance ?: synchronized(this) {
                    instance ?: ViewModelFactory(Injection.provideRepository(context))
                }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(MovieViewModel::class.java) ->{
                return MovieViewModel(mGeneralRepository) as T
            }
            modelClass.isAssignableFrom(TiviViewModel::class.java) ->{
                return TiviViewModel(mGeneralRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }
}
