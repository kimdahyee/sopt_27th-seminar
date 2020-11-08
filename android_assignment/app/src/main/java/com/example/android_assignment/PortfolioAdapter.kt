package com.example.android_assignment

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created By kimdahyee
 * on 11월 07일, 2020
 */

class PortfolioAdapter(private val context: Context) : RecyclerView.Adapter<PortfolioViewHolder>() {

    var data = mutableListOf<PortfolioData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_portfolio, parent, false)
        return PortfolioViewHolder(view)
    }

    override fun onBindViewHolder(holder: PortfolioViewHolder, position: Int) {
        holder.onBind(data[position])
        holder.itemView.setOnClickListener{
            itemClickListener.onItemClick(it, position)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    //click interface
    interface ItemClickListener {
        fun onItemClick(view: View, position: Int)
    }

    private lateinit var itemClickListener: ItemClickListener

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }
}