package com.example.android_assignment.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android_assignment.adapter.PortfolioAdapter
import com.example.android_assignment.activity.PortfolioDetailActivity
import com.example.android_assignment.R
import com.example.android_assignment.data.PortfolioData
import kotlinx.android.synthetic.main.fragment_grid.rcv_portfolio

class GridFragment : Fragment() {

    private lateinit var portfolioAdapter: PortfolioAdapter
    val datas: MutableList<PortfolioData> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_grid, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRcv(view)
        loadData()
    }

    private fun initRcv(view: View) {
        portfolioAdapter = PortfolioAdapter(view.context)
        portfolioAdapter.viewType = 2

        rcv_portfolio.adapter = portfolioAdapter
        rcv_portfolio.layoutManager = GridLayoutManager(context!!, 3, RecyclerView.VERTICAL, false)

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

    private fun loadData() {
        datas.apply {
            add (
                PortfolioData(
                    title = "내 이름은",
                    subTitle = "김다혜",
                    date = "2020/07/03",
                    content = "이것은 부가설명입니다.")
            )

            add (
                PortfolioData(
                    title = "나는 스물셋",
                    subTitle = "암트웬티쓰리",
                    date = "1998/05/21",
                    content = "이것은 부가설명입니다.")
            )

            add (
                PortfolioData(
                    title = "SOPT 27th",
                    subTitle = "android OB",
                    date = "2020/07/03",
                    content = "이것은 부가설명입니다.")
            )

            add (
                PortfolioData(
                    title = "지금은",
                    subTitle = "학교에서 일하는 중",
                    date = "2020/07/03",
                    content = "이것은 부가설명입니다.")
            )

            add (
                PortfolioData(
                    title = "나는 지금",
                    subTitle = "집에 가고싶어",
                    date = "2020/07/03",
                    content = "이것은 부가설명입니다.")
            )

            add (
                PortfolioData(
                    title = "내 이름은",
                    subTitle = "김다혜",
                    date = "2020/07/03",
                    content = "이것은 부가설명입니다.")
            )

            add (
                PortfolioData(
                    title = "나는 스물셋",
                    subTitle = "암트웬티쓰리",
                    date = "1998/05/21",
                    content = "이것은 부가설명입니다.")
            )

            add (
                PortfolioData(
                    title = "SOPT 27th",
                    subTitle = "android OB",
                    date = "2020/07/03",
                    content = "이것은 부가설명입니다.")
            )

            add (
                PortfolioData(
                    title = "지금은",
                    subTitle = "학교에서 일하는 중",
                    date = "2020/07/03",
                    content = "이것은 부가설명입니다.")
            )

            add (
                PortfolioData(
                    title = "나는 지금",
                    subTitle = "집에 가고싶어",
                    date = "2020/07/03",
                    content = "이것은 부가설명입니다.")
            )
        }
        portfolioAdapter.data = datas
        portfolioAdapter.notifyDataSetChanged()
    }
}