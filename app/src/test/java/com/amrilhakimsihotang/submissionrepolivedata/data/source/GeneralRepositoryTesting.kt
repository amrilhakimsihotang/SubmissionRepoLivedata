package com.amrilhakimsihotang.submissionrepolivedata.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.amrilhakimsihotang.submissionrepolivedata.LiveDataTestUtil
import com.amrilhakimsihotang.submissionrepolivedata.data.source.remote.RemoteDataSource
import com.amrilhakimsihotang.submissionrepolivedata.utils.DataDummy
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class GeneralRepositoryTesting {
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val generalRepository = FakeGeneralRepository(remote)

    private val movieResponses = DataDummy.generateRemoteMovies()
    private val tiviResponses = DataDummy.generateRemotetivi()


    @Test
    fun getAllMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMovieCallback)
                .onAllMovieReceived(movieResponses)
            null
        }.`when`(remote).getAllMovie(any())
        val movieEntities = LiveDataTestUtil.getValue(generalRepository.getAllMovie())
        verify(remote).getAllMovie(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getAllTivi() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTiviCallback)
                .onAllTiviReceived(tiviResponses)
            null
        }.`when`(remote).getAllTivi(any())
        val tiviEntities = LiveDataTestUtil.getValue(generalRepository.getAllTivi())
        verify(remote).getAllTivi(any())
        assertNotNull(tiviEntities)
        assertEquals(tiviResponses.size.toLong(), tiviEntities.size.toLong())
    }
}