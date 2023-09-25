package com.example.chart

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    val list: ArrayList<PieEntry> = ArrayList()
    list.add(PieEntry(usespace.toFloat(), "used " + convertFileSize(usespace)))
    list.add(PieEntry(freespace.toFloat(), "free " + convertFileSize(freespace)))
    val pieDataSet = PieDataSet(list, "")
    val pieData = PieData(pieDataSet)
    val colors = listOf(
        Color.WHITE,
        Color.parseColor("#1AFFFFFF"),
    )
    pieDataSet.colors = colors
    if (pieData.entryCount == 0) {
        pieChart.visibility = View.GONE
        noPieChart.visibility = View.VISIBLE
    } else {
        pieChart.visibility = View.VISIBLE
        noPieChart.visibility = View.GONE
        pieChart.data = pieData
        pieChart.invalidate()
    }
    pieChart.renderer =
    RoundedSlicesPieChartRenderer(pieChart, pieChart.animator, pieChart.viewPortHandler)
    pieChart.description.text = "Pie Chart"
    pieChart.description.isEnabled = false
    pieChart.setCenterTextSize(24f)
    var textCenter =
        String.format("%.0f", (usespace.toFloat() / totalspace.toFloat()) * 100f)
    pieChart.centerText = spannableString(textCenter)
    pieChart.setCenterTextColor(Color.WHITE)
    pieChart.isDrawHoleEnabled = true
    pieChart.setHoleColor(Color.TRANSPARENT)
    pieChart.setTransparentCircleColor(Color.TRANSPARENT)
    pieChart.setTransparentCircleAlpha(0)
    pieChart.setDrawEntryLabels(false)
    pieDataSet.valueTextSize = 0f
    pieChart.holeRadius = 80f
    pieChart.setTouchEnabled(false)
    pieChart.transparentCircleRadius =
    resources.getDimension(R.dimen.pie_chart_outer_radius)

    val legend = pieChart.legend
    legend.textSize = 20f
    pieChart.legend.isEnabled = false
    legend.isEnabled = false
    legend.textSize = 20f
    legend.textColor = Color.WHITE
    legend.formToTextSpace = 10f
    legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
    pieChart.animateY(1000)
}


}