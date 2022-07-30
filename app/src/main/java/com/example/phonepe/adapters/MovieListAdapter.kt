package com.example.phonepe.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.phonepe.R
import com.example.phonepe.databinding.ItemMovieFeedBinding
import com.example.phonepe.helpers.clearAndAddAll
import com.example.phonepe.helpers.click
import com.example.phonepe.models.MovieData

class MovieListAdapter(var onItemClick: (MovieData) -> Unit) : RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {

    private val movieList: MutableList<MovieData> = mutableListOf()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemMovieFeedBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(movieList[position])

    override fun getItemCount() = if (movieList.isEmpty()) 0 else movieList.size

    fun setData(movies: List<MovieData>?) {
        movies?.let { movieList.clearAndAddAll(it) }
        notifyDataSetChanged()
    }

    fun addData(movies: List<MovieData>?) {
        val previousSize = movieList.size
        movies?.let {
            movieList.addAll(it)
            notifyItemRangeInserted(previousSize, movies.size)
        }
    }

    inner class ViewHolder(var itemViewBinding: ItemMovieFeedBinding) : RecyclerView.ViewHolder(itemViewBinding.root) {

        init {
            itemViewBinding.root.click {
                onItemClick(movieList[adapterPosition])
            }
        }

        fun bind(data: MovieData) {
            with(itemViewBinding.root) {
                Glide.with(this)
                    .load(data.getImagePath())
                    .error(R.drawable.error_placeholder)
                    .into(itemViewBinding.ivMovieThumbnail)

                itemViewBinding.tvMovieTitle.text = data.title
                itemViewBinding.tvDescription.text = data.overview
            }
        }

    }

}