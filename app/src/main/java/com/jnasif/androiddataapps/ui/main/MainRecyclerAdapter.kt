package com.jnasif.androiddataapps.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.jnasif.androiddataapps.R
import com.jnasif.androiddataapps.data.Monster

class MainRecyclerAdapter(val context : Context, val monsters : List<Monster>) : RecyclerView.Adapter<MainRecyclerAdapter.ViewHolder>() {

    inner class ViewHolder(itemview : View) : RecyclerView.ViewHolder(itemview){
        val nameText = itemview.findViewById<TextView>(R.id.nameText)
        val monsterImage = itemview.findViewById<ImageView>(R.id.monsterImage)
        val ratingBar = itemview.findViewById<RatingBar>(R.id.ratingBar)
    }

    override fun getItemCount(): Int = monsters.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.monster_grid_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val monster = monsters[position]
        with(holder) {
            nameText?.let {
                it.text = monster.name
                it.contentDescription = monster.name
            }
            ratingBar?.rating = monster.scariness.toFloat()
            Glide.with(context).load(monster.thumbnailUrl).into(monsterImage)
        }
    }
}