package com.example.musicapp.view.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapp.databinding.MusicItemLayoutBinding
import com.example.musicapp.model.remote.MusicItem
import com.squareup.picasso3.Picasso

private const val TAG = "MusicAdapter"

class MusicAdapter(private val dataSet: List<MusicItem>,
                   private val openDetails: (MusicItem) -> Unit) :
    RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {

    class MusicViewHolder (private val binding: MusicItemLayoutBinding):
        RecyclerView.ViewHolder(binding.root){

        fun bind(currentElement: MusicItem, openDetails: (MusicItem) -> Unit){
            Log.d(TAG, "bind: bind fun before binding.root setOnClickListener")
            binding.root.setOnClickListener(){
                openDetails(currentElement)
            }
            Log.d(TAG, "bind: Bind function")
            binding.tvPrice.text = currentElement.trackPrice.toString()
            binding.tvArtistName.text = currentElement.artistName
            binding.tvSongTitle.text = currentElement.trackName
            var imageURL = currentElement.artworkUrl60.replace("100x100.jpg", "250x250bb.jpg")

            if(imageURL==""){
                Log.d(TAG, "bind: $currentElement.trackName")
            }
            try{
                Picasso.Builder(binding.tvArtistName.context)
                    .build()
                    .load(imageURL)
                    .into(binding.ivAlbumCover)

            }
            catch (ex:Exception){

            }
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

