package com.amrilhakimsihotang.submissionrepolivedata.data.source.remote.response


data class MovieResponse(
    val id: String,
    val original_title: String ,
    val poster_path: String,
    val overview: String,
    val director: String,
    val writer: String,
    val screenplay: String,
    val person_cast: String
)