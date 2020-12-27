package com.amrilhakimsihotang.submissionrepolivedata.viewmodel


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.amrilhakimsihotang.submissionrepolivedata.data.MovieEntity
import com.amrilhakimsihotang.submissionrepolivedata.data.source.GeneralRepository
import com.amrilhakimsihotang.submissionrepolivedata.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieViewModelTest {

    private lateinit var movieViewModel: MovieViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var generalRepository: GeneralRepository

    @Mock
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setup() {
        movieViewModel = MovieViewModel(generalRepository)
    }

    @Test
    fun getMovie() {
        val dummyMovie = DataDummy.generateRemoteMovie()
        val movie = MutableLiveData<List<MovieEntity>>()
        movie.value = dummyMovie

        `when`(generalRepository.getAllMovie()).thenReturn(movie)
        val movieEntities = movieViewModel.getMovie().value
        verify(generalRepository).getAllMovie()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)
        movieViewModel.getMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)

    }
}

