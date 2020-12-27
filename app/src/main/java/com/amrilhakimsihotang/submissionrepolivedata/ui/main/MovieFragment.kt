package com.amrilhakimsihotang.submissionrepolivedata.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.amrilhakimsihotang.submissionrepolivedata.adapter.MoviesAdapter
import com.amrilhakimsihotang.submissionrepolivedata.databinding.FragmentMovieBinding
import com.amrilhakimsihotang.submissionrepolivedata.viewmodel.MovieViewModel
import com.amrilhakimsihotang.submissionrepolivedata.viewmodel.ViewModelFactory

class MovieFragment : Fragment() {

    private lateinit var binding: FragmentMovieBinding
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var movieViewModel: MovieViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            movieViewModel = ViewModelProvider(requireActivity(), factory)
                    .get(MovieViewModel::class.java)

            moviesAdapter = MoviesAdapter()
            binding.progressBar1.visibility = View.VISIBLE
            movieViewModel.getMovie().observe(this,{ movie ->
                binding.progressBar1.visibility= View.GONE
                moviesAdapter.setMovie(movie)
                moviesAdapter.notifyDataSetChanged()
            })
            binding.rvmovies.layoutManager = GridLayoutManager(requireActivity(), 2)
            binding.rvmovies.setHasFixedSize(true)
            binding.rvmovies.adapter = moviesAdapter
        }
    }
}