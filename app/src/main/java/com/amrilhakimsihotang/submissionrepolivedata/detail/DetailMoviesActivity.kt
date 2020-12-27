package com.amrilhakimsihotang.submissionrepolivedata.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amrilhakimsihotang.submissionrepolivedata.R
import com.amrilhakimsihotang.submissionrepolivedata.data.MovieEntity
import com.amrilhakimsihotang.submissionrepolivedata.databinding.ActivityDetailMoviesBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailMoviesActivity : AppCompatActivity() {
    companion object {
        const val DETAIL_MOVIES = "detail_movies"
    }

    private lateinit var detailMoviesBinding: ActivityDetailMoviesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_movies)
        supportActionBar?.title = resources.getString(R.string.detailmovie)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        detailMoviesBinding = ActivityDetailMoviesBinding.inflate(layoutInflater)
        setContentView(detailMoviesBinding.root)

        val movieEntity = intent.getParcelableExtra<MovieEntity>(DETAIL_MOVIES) as MovieEntity
        Glide.with(this)
            .load(movieEntity.poster_path)
            .apply(RequestOptions().override(800, 600))
            .error(R.drawable.ic_baseline_tag_faces_24)
            .into(detailMoviesBinding.detpostermovies)
        detailMoviesBinding.dettitlemovies.text = movieEntity.original_title
        detailMoviesBinding.detoverviewmovies.text = movieEntity.overview
        detailMoviesBinding.detdirector.text =
            resources.getString(R.string.director, movieEntity.director)
        detailMoviesBinding.detwriter.text =
            resources.getString(R.string.writer, movieEntity.writer)
        detailMoviesBinding.detscreenplay.text =
            resources.getString(R.string.screenplay, movieEntity.screenplay)
        detailMoviesBinding.detpersoncasting.text = movieEntity.person_cast

        detailMoviesBinding.fabshare.setOnClickListener {
            val sMessage: String =
                resources.getString(R.string.sharedetail) + " " + detailMoviesBinding.dettitlemovies.text.toString() + " " + resources.getString(
                    R.string.sharedetail2
                )
            val mIntent = Intent(Intent.ACTION_SEND)
            mIntent.type = "text/plain"
            mIntent.putExtra(Intent.EXTRA_TEXT, sMessage)
            startActivity(Intent.createChooser(mIntent, "Share to: "))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}