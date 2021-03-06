package com.example.android_assignment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_assignment.viewHolder.PortfolioViewHolder
import com.example.android_assignment.R
import com.example.android_assignment.data.PortfolioData

/**
 * Created By kimdahyee
 * on 11월 07일, 2020
 */

class PortfolioAdapter(private val context: Context) : RecyclerView.Adapter<PortfolioViewHolder>() {

    var data = mutableListOf<PortfolioData>()
    var viewType = 1;

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PortfolioViewHolder {
        var view = when (viewType) {
            1 -> {
                LayoutInflater.from(context).inflate(R.layout.item_portfolio, parent, false)
            }
            else -> {
                LayoutInflater.from(context).inflate(R.layout.item_portfolio_grid, parent, false)
            }
        }
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

    override fun getItemViewType(position: Int): Int {
        return viewType
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