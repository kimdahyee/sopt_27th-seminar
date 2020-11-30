package com.example.android_assignment.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android_assignment.R
import kotlinx.android.synthetic.main.activity_portfolio_detail.*

class PortfolioDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portfolio_detail)

        var clickedPortfolio = intent
        tv_title_portfolio_detail.text = clickedPortfolio.getStringExtra("title")
        tv_subtitle_portfolio_detail.text = clickedPortfolio.getStringExtra("subTitle")
        tv_date_portfolio_detail.text = clickedPortfolio.getStringExtra("date")
        tv_content_portfolio_detail.text = clickedPortfolio.getStringExtra("content")
    }
}