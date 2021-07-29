package com.example.covidtracker
import android.graphics.RectF
import com.robinhood.spark.SparkAdapter



class CovidSparkAdapter(private val dailyData: List<CovidData>): SparkAdapter(){
    //abstract class inherits from
    // spark adapter which came in from the UI from internet, : indicate inheritance
    // daily data is a kind of constructor of Spark Adapter

    var metric = Metric.POSITIVE
    var daysAgo = TimeScale.MAX
    override fun getY(index: Int): Float {
        val chosenDayData = dailyData[index]
        return when (metric)
        {
            Metric.NEGATIVE ->chosenDayData.negativeIncrease.toFloat()
            Metric.POSITIVE ->chosenDayData.positiveIncrease.toFloat()
            Metric.DEATH ->chosenDayData.deathIncrease.toFloat()
        }
    }
    override fun getItem(index: Int)= dailyData[index]
    //given an index returns an object at that index
    override fun getCount()=dailyData.size
    //returns the size of the list
       override fun getDataBounds(): RectF {
          val bounds = super.getDataBounds()
        if(daysAgo!=TimeScale.MAX) {
            bounds.left = count - daysAgo.numDays.toFloat()
        }
        return bounds
       }
}
