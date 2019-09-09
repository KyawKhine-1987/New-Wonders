package com.android.freelance.wonders.ui.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.freelance.wonders.R
import com.android.freelance.wonders.data.db.entity.Wonders
import com.bumptech.glide.Glide

class WondersAdapter(val mListItemClickListener: ListItemClickListener, private val context: Context, private val wonders: List<Wonders>) :
    RecyclerView.Adapter<WondersAdapter.WondersViewHolder>() {

    private val LOG_TAG = WondersAdapter::class.java.name

    interface ListItemClickListener {
        fun onListItemClick(clickItemIndex: Int, wondersEntity: List<Wonders>)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WondersViewHolder {
        Log.i(LOG_TAG, "TEST: onCreateViewHolder() is called...")

        val layoutInflater = LayoutInflater.from(parent.context)
        return WondersViewHolder(layoutInflater.inflate(R.layout.item_wonders_list, parent, false))
    }

    override fun getItemCount(): Int = wonders.size

    override fun onBindViewHolder(holder: WondersViewHolder, i: Int) {
        Log.i(LOG_TAG, "TEST: onBindViewHolder() is called...")

        holder.bindModel(wonders[i])
    }

    inner class WondersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener  {

        private val LOG_TAG = WondersViewHolder::class.java.name

        init {
            itemView.setOnClickListener(this)
        }

        /*private var view: View = itemView*/
        private val wondersImage: ImageView = itemView.findViewById(R.id.ivWonders)
        private val title: TextView = itemView.findViewById(R.id.tvLocation)
        private val desp: TextView = itemView.findViewById(R.id.tvDescription)

        fun bindModel(wonders: Wonders) {
            Log.i(LOG_TAG, "TEST: bindModel() is called...")

            Glide.with(context).load(wonders.image).into(wondersImage)
            title.text = wonders.location
            desp.text = wonders.description
        }

        override fun onClick(v: View?) {
            Log.i(LOG_TAG, "TEST: onClick() is called...")

            val clickedPosition = getAdapterPosition()
            mListItemClickListener.onListItemClick(clickedPosition, wonders)
        }
    }
}

