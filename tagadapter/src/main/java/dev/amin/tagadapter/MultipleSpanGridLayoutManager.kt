package dev.amin.tagadapter

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager

class MultipleSpanGridLayoutManager(context: Context, spanCount: Int, spanList: MutableList<Int>) :
    GridLayoutManager(context, spanCount) {

    init {
        Log.d("INFO", "spanCount = $spanCount")
        Log.d("INFO", "spanList.size = ${spanList.size}")

        spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int) = spanList[position]
/*
            override fun getSpanSize(position: Int): Int {
                //Log.d("INFO", "$position: ${spanList[position]}")
                return spanList[position]
            }
*/
        }
    }
}