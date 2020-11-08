package com.example.android_assignment

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

/**
 * Created By kimdahyee
 * on 11월 07일, 2020
 */
 
class PortfolioViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView) {

    private val title : TextView = itemView.findViewById(R.id.tv_title_portfolio)
    private val subTitle : TextView = itemView.findViewById(R.id.tv_subtitle_portfolio)

    fun onBind(portfolioData: PortfolioData) {
        title.text = portfolioData.title
        subTitle.text = portfolioData.subTitle
    }
}