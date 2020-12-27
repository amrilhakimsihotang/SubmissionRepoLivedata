package com.amrilhakimsihotang.submissionrepolivedata.utils

import android.content.Context
import com.amrilhakimsihotang.submissionrepolivedata.data.source.remote.response.MovieResponse
import com.amrilhakimsihotang.submissionrepolivedata.data.source.remote.response.TiviResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }

    fun loadMovie(): List<MovieResponse> {
        val list = ArrayList<MovieResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("MoviesResponse.json").toString())
            val listArray = responseObject.getJSONArray("movie")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val id = movie.getString("id")
                val original_title = movie.getString("original_title")
                val poster_path = movie.getString("poster_path")
                val overview = movie.getString("overview")
                val director = movie.getString("director")
                val writer = movie.getString("writer")
                val screenplay = movie.getString("screenplay")
                val person_cast = movie.getString("person_cast")
                val movieResponse = MovieResponse(id, original_title, poster_path, overview, director, writer, screenplay, person_cast)
                list.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadTivi(): List<TiviResponse> {
        val list = ArrayList<TiviResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("TiviResponse.json").toString())
            val listArray = responseObject.getJSONArray("tivi")
            for (i in 0 until listArray.length()) {
                val tivi = listArray.getJSONObject(i)
                val id = tivi.getString("id")
                val original_name = tivi.getString("original_name")
                val poster_path = tivi.getString("poster_path")
                val overview = tivi.getString("overview")
                val creatorcast = tivi.getString("creatorcast")
                val seriescast = tivi.getString("seriescast")
                val writingcast = tivi.getString("writingcast")

                val tiviResponse = TiviResponse(id, original_name, poster_path, overview, creatorcast, seriescast, writingcast)
                list.add(tiviResponse)
            }
        }catch (e:JSONException){
            e.printStackTrace()
        }
        return list
    }
}

