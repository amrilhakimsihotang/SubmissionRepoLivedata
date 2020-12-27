package com.amrilhakimsihotang.submissionrepolivedata.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.amrilhakimsihotang.submissionrepolivedata.data.MovieEntity
import com.amrilhakimsihotang.submissionrepolivedata.data.TiviEntity
import com.amrilhakimsihotang.submissionrepolivedata.data.source.remote.RemoteDataSource
import com.amrilhakimsihotang.submissionrepolivedata.data.source.remote.response.MovieResponse
import com.amrilhakimsihotang.submissionrepolivedata.data.source.remote.response.TiviResponse

class FakeGeneralRepository(private val remoteDataSource: RemoteDataSource) :
    GeneralDataSource {

    override fun getAllMovie(): LiveData<List<MovieEntity>> {
        val movieResult = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getAllMovie(object : RemoteDataSource.LoadMovieCallback {
            override fun onAllMovieReceived(movieResponse: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponse) {
                    val movie = MovieEntity(
                        response.id,
                        response.original_title,
                        response.poster_path,
                        response.overview,
                        response.director,
                        response.writer,
                        response.screenplay,
                        response.person_cast
                    )
                    movieList.add(movie)
                }
                movieResult.postValue(movieList)
            }
        })
        return movieResult
    }

    override fun getAllTivi(): LiveData<List<TiviEntity>> {
        val tiviResult = MutableLiveData<List<TiviEntity>>()
        remoteDataSource.getAllTivi(object : RemoteDataSource.LoadTiviCallback {
            override fun onAllTiviReceived(tiviResponse: List<TiviResponse>) {
                val tiviList = ArrayList<TiviEntity>()
                for (response in tiviResponse) {
                    val tiviShow = TiviEntity(
                        response.id,
                        response.original_name,
                        response.poster_path,
                        response.overview,
                        response.creatorcast,
                        response.seriescast,
                        response.writingcast
                    )
                    tiviList.add(tiviShow)
                }
                return tiviResult.postValue(tiviList)
            }

        })
        return tiviResult
    }
}