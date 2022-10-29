package com.example.musicapp.view.adapter

import android.telecom.Call.Details
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.MusicItemLayoutBinding
import com.example.musicapp.model.remote.MusicItem
import com.squareup.picasso3.Picasso

class MusicAdapter(private val dataSet: List<MusicItem>,
private val openDetails: (MusicItem) -> Unit) :RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    class MusicViewHolder (private val binding: MusicItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root){

            fun bind(currentElement: MusicItem, openDetails: (MusicItem) -> Unit){
                binding.root.setOnClickListener(){
                    openDetails(currentElement)
                }
                binding.tvPrice.text = currentElement.trackPrice
                binding.tvArtistName.text = currentElement.artistName
                binding.tvSongTitle.text = currentElement.collectionName
                var imageUrl = currentElement.artwork
                Picasso.Builder(binding.root.context)
                    .build()
                    .load(imageUrl)
                    .into(binding.ivAlbumCover)
            }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicViewHolder {
        return MusicViewHolder(
            MusicItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false,)
        )
    }

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        holder.bind(dataSet[position], openDetails)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

}