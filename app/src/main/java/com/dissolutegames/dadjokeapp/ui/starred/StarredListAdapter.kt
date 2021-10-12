package com.dissolutegames.dadjokeapp.ui.starred

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dissolutegames.dadjokeapp.databinding.ItemStarredBinding
import com.dissolutegames.dadjokeapp.model.Joke

class StarredListAdapter(
    private val inflater: LayoutInflater,
    private val items: Array<Joke>,
    private val listener: (joke: Joke) -> Unit
) : RecyclerView.Adapter<StarredItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = StarredItemViewHolder(
        ItemStarredBinding.inflate(inflater)
    )

    override fun onBindViewHolder(holder: StarredItemViewHolder, position: Int) {
        val joke = items[position]
        holder.binding.jokeShortText.text = joke.Joke
        holder.binding.root.setOnClickListener { listener(joke)}
    }

    override fun getItemCount() = items.size
}