package com.amrilhakimsihotang.submissionrepolivedata.detail

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.amrilhakimsihotang.submissionrepolivedata.R
import com.amrilhakimsihotang.submissionrepolivedata.data.TiviEntity
import com.amrilhakimsihotang.submissionrepolivedata.databinding.ActivityDetailTvshowBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class DetailTvshowActivity : AppCompatActivity() {
    companion object {
        const val DETAIL_TIVISHOW = "detail_tivishow"
    }

    private lateinit var detailTvshowBinding: ActivityDetailTvshowBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_tvshow)
        detailTvshowBinding = ActivityDetailTvshowBinding.inflate(layoutInflater)
        setContentView(detailTvshowBinding.root)
        supportActionBar?.title = resources.getString(R.string.detailtivishow)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val tiviEntity =
            intent.getParcelableExtra<TiviEntity>(DETAIL_TIVISHOW) as TiviEntity
        Glide.with(this)
            .load(tiviEntity.poster_path)
            .apply(RequestOptions().override(800, 600))
            .error(R.drawable.ic_baseline_tag_faces_24)
            .into(detailTvshowBinding.detpostertv)

        detailTvshowBinding.dettitletv.text = tiviEntity.original_name
        detailTvshowBinding.detoverviewtv.text = tiviEntity.overview
        detailTvshowBinding.detcreator.text =
            resources.getString(R.string.creator, tiviEntity.creatorcast)
        detailTvshowBinding.detwriter.text =
            resources.getString(R.string.writer, tiviEntity.writingcast)
        detailTvshowBinding.detseriescast.text = tiviEntity.seriescast

        detailTvshowBinding.fabtivi.setOnClickListener {
            val sMessage: String =
                resources.getString(R.string.sharedetailtv) + " " + detailTvshowBinding.dettitletv.text.toString() + " " + resources.getString(
                    R.string.sharedetailtv2
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