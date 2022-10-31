package com.example.musicapp.view.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.MusicItemLayoutBinding
import com.example.musicapp.model.remote.MusicItem
import com.squareup.picasso3.Picasso

class MusicAdapter(
    private val dataSet: List<MusicItem>,
    private val openDetails: (MusicItem) -> Unit
) : RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {
    class MusicViewHolder(private val binding: MusicItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(currentElement: MusicItem, openDetails: (MusicItem) -> Unit) {
            binding.root.setOnClickListener {
                openDetails(currentElement)
            }
            binding.tvPrice.text = currentElement.trackPrice.toString()
            binding.tvArtistName.text = currentElement.artistName
            binding.tvSongTitle.text = currentElement.trackName
            val imageURL = currentElement.artworkUrl60.replace("60x60bb.jpg", "150x150bb.jpg")
            Picasso.Builder(binding.tvArtistName.context)
                .build()
                .load(imageURL).resize(150, 150)
                .into(binding.ivAlbumCover)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        return MusicViewHolder(
            MusicItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false,
            )
        )
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(dataSet[position], openDetails)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}

