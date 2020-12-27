package com.amrilhakimsihotang.submissionrepolivedata.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.amrilhakimsihotang.submissionrepolivedata.data.TiviEntity
import com.amrilhakimsihotang.submissionrepolivedata.data.source.GeneralRepository
import com.amrilhakimsihotang.submissionrepolivedata.utils.DataDummy
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TiviViewModelTest {
    private lateinit var tiviViewModel: TiviViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var generalRepository: GeneralRepository

    @Mock
    private lateinit var observer: Observer<List<TiviEntity>>

    @Before
    fun setup() {
        tiviViewModel = TiviViewModel(generalRepository)
    }

    @Test
    fun getTivi() {
        val dummyTivi = DataDummy.generateRemoteTivishow()
        val tivi = MutableLiveData<List<TiviEntity>>()
        tivi.value = dummyTivi

        `when`(generalRepository.getAllTivi()).thenReturn(tivi)
        val tiviEntities = tiviViewModel.getTivishow().value
        verify(generalRepository).getAllTivi()
        assertNotNull(tiviEntities)
        assertEquals(10, tiviEntities?.size)
        tiviViewModel.getTivishow().observeForever(observer)
        verify(observer).onChanged(dummyTivi)
    }
}
