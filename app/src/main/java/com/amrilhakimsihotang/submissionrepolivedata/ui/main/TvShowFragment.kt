package com.amrilhakimsihotang.submissionrepolivedata.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.amrilhakimsihotang.submissionrepolivedata.adapter.TiviShowAdapter
import com.amrilhakimsihotang.submissionrepolivedata.databinding.FragmentTvShowBinding
import com.amrilhakimsihotang.submissionrepolivedata.viewmodel.TiviViewModel
import com.amrilhakimsihotang.submissionrepolivedata.viewmodel.ViewModelFactory

class TvShowFragment : Fragment() {

    private lateinit var tiviShowAdapter: TiviShowAdapter
    private lateinit var binding: FragmentTvShowBinding
    private lateinit var tiviViewModel: TiviViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())

            tiviViewModel = ViewModelProvider(
                    requireActivity(),factory
            ).get(TiviViewModel::class.java)
            tiviShowAdapter = TiviShowAdapter()
            binding.progressBar2.visibility = View.VISIBLE
            tiviViewModel.getTivishow().observe(this,{ tivi ->
                binding.progressBar2.visibility = View.GONE
                tiviShowAdapter.setTivishow(tivi)
                tiviShowAdapter.notifyDataSetChanged()

            })

            binding.rvtivi.layoutManager = GridLayoutManager(requireActivity(), 2)
            binding.rvtivi.setHasFixedSize(true)
            binding.rvtivi.adapter = tiviShowAdapter
        }

    }
}
