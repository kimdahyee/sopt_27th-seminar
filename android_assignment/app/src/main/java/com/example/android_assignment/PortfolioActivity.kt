package com.example.android_assignment

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_portfolio.*

class PortfolioActivity : AppCompatActivity() {

    private lateinit var portfolioAdapter: PortfolioAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_portfolio)

        portfolioAdapter = PortfolioAdapter(this)
        rcv_portfolio.adapter = portfolioAdapter

        portfolioAdapter.data = mutableListOf(
            PortfolioData("내 이름은", "김다혜", "2020/07/03", "이것은 부가설명입니다."),
            PortfolioData("나는 스물셋", "1998/05/21", "2020/03/11", "쓸 말이 없네요 정말"),
            PortfolioData("동덕여자대학교", "컴퓨터학과에 재학 중", "2019/12/08", "아아아아아아"),
            PortfolioData("SOPT 26th", "안드로이드 YB", "2009/04/04", "할말없다아아아아아"),
            PortfolioData("SOPT 27th", "안드로이드 OB", "2004/10/20", "에베베베에에에엥"),
            PortfolioData("placepic의", "멋진 Heroid", "2015/11/11", "우가우가우가우가"),
            PortfolioData("나는 스물셋", "1998/05/21", "2020/03/11", "쓸 말이 없네요 정말"),
            PortfolioData("동덕여자대학교", "컴퓨터학과에 재학 중", "2019/12/08", "아아아아아아"),
            PortfolioData("SOPT 26th", "안드로이드 YB", "2009/04/04", "할말없다아아아아아"),
            PortfolioData("SOPT 27th", "안드로이드 OB", "2004/10/20", "에베베베에에에엥"),
            PortfolioData("placepic의", "멋진 Heroid", "2015/11/11", "우가우가우가우가"),
            PortfolioData("나는 스물셋", "1998/05/21", "2020/03/11", "쓸 말이 없네요 정말"),
            PortfolioData("동덕여자대학교", "컴퓨터학과에 재학 중", "2019/12/08", "아아아아아아"),
            PortfolioData("SOPT 26th", "안드로이드 YB", "2009/04/04", "할말없다아아아아아"),
            PortfolioData("SOPT 27th", "안드로이드 OB", "2004/10/20", "에베베베에에에엥"),
            PortfolioData("placepic의", "멋진 Heroid", "2015/11/11", "우가우가우가우가")
        )
        portfolioAdapter.notifyDataSetChanged()

        portfolioAdapter.setItemClickListener(object : PortfolioAdapter.ItemClickListener {
            override fun onItemClick(view: View, position: Int) {
                val clickedPortfolioIntent = Intent(view.context, PortfolioDetailActivity::class.java)
                clickedPortfolioIntent.putExtra("title", portfolioAdapter.data[position].title)
                clickedPortfolioIntent.putExtra("subTitle", portfolioAdapter.data[position].subTitle)
                clickedPortfolioIntent.putExtra("date", portfolioAdapter.data[position].date)
                clickedPortfolioIntent.putExtra("content", portfolioAdapter.data[position].content)
                startActivity(clickedPortfolioIntent)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.layout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item?.itemId) {
            R.id.menu_linear -> {
                portfolioAdapter.viewType = 1
                rcv_portfolio.layoutManager = LinearLayoutManager(this)
            }
            R.id.menu_grid -> {
                portfolioAdapter.viewType = 2
                rcv_portfolio.layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}