package com.amrilhakimsihotang.submissionrepolivedata.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.amrilhakimsihotang.submissionrepolivedata.R
import com.amrilhakimsihotang.submissionrepolivedata.data.TiviEntity
import com.amrilhakimsihotang.submissionrepolivedata.databinding.ListTvshowBinding
import com.amrilhakimsihotang.submissionrepolivedata.detail.DetailTvshowActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class TiviShowAdapter :RecyclerView.Adapter<TiviShowAdapter.TiviShowViewHolder>() {
    private val listiviShow = ArrayList<TiviEntity>()
    fun setTivishow(tivi: List<TiviEntity>?) {
        if (tivi == null) return
        this.listiviShow.clear()
        this.listiviShow.addAll(tivi)
        this.notifyDataSetChanged()

    }

    class TiviShowViewHolder(private val binding: ListTvshowBinding) :
            RecyclerView.ViewHolder(binding.root) {
        fun bind(tiviEntity: TiviEntity) {
            binding.tvshowTitle.text = tiviEntity.original_name
            Glide.with(itemView.context)
                    .load(tiviEntity.poster_path)
                    .apply(RequestOptions().override(800, 600))
                    .error(R.drawable.ic_baseline_tag_faces_24)
                    .into(binding.imgPosterTvshow)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TiviShowViewHolder {
        val binding = ListTvshowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TiviShowViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TiviShowViewHolder, position: Int) {
        val tivi = listiviShow[position]
        holder.bind(listiviShow[position])
        holder.itemView.setOnClickListener {
            val tivishowIntent = TiviEntity(
                    tivi.id,
                    tivi.original_name,
                    tivi.poster_path,
                    tivi.overview,
                    tivi.creatorcast,
                    tivi.seriescast,
                    tivi.writingcast
            )

            val mIntent = Intent(it.context,DetailTvshowActivity::class.java)
            mIntent.putExtra(DetailTvshowActivity.DETAIL_TIVISHOW,tivishowIntent)
            it.context.startActivity(mIntent)
        }

    }
    override fun getItemCount(): Int = listiviShow.size
}