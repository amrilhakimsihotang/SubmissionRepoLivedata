package com.amrilhakimsihotang.submissionrepolivedata.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amrilhakimsihotang.submissionrepolivedata.R
import com.amrilhakimsihotang.submissionrepolivedata.data.MovieEntity
import com.amrilhakimsihotang.submissionrepolivedata.databinding.ListMoviesBinding
import com.amrilhakimsihotang.submissionrepolivedata.detail.DetailMoviesActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MoviesAdapter : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {
    private val listMovies = ArrayList<MovieEntity>()
    fun setMovie(movie: List<MovieEntity>?) {
        if (movie == null) return
        this.listMovies.clear()
        this.listMovies.addAll(movie)
        this.notifyDataSetChanged()
    }

    inner class MoviesViewHolder(private val binding: ListMoviesBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(movieEntity: MovieEntity) {
            binding.movieTitle.text = movieEntity.original_title
            Glide.with(itemView.context)
                    .load(movieEntity.poster_path)
                    .apply(RequestOptions().override(800, 600))
                    .error(R.drawable.ic_baseline_tag_faces_24)
                    .into(binding.imgPosterMovies)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val binding = ListMoviesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MoviesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = listMovies[position]
        holder.bind(listMovies[position])
        holder.itemView.setOnClickListener {
            val movieIntent = MovieEntity(
                    movie.id,
                    movie.original_title,
                    movie.poster_path,
                    movie.overview,
                    movie.director,
                    movie.writer,
                    movie.screenplay,
                    movie.person_cast
            )
            val mIntent = Intent(it.context, DetailMoviesActivity::class.java)
            mIntent.putExtra(DetailMoviesActivity.DETAIL_MOVIES, movieIntent)
            it.context.startActivity(mIntent)
        }
    }

    override fun getItemCount(): Int = listMovies.size

}