package dev.amin.tagadapter

import android.content.Context
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager

class MultipleSpanGridLayoutManager(context: Context, spanCount: Int, spanList: MutableList<Int>) :
    GridLayoutManager(context, spanCount) {

    init {
        val totalSize = spanList.size
        Log.d("INFO", "totalSize = $totalSize")

        spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int) = spanList[position] + 1

/*            override fun getSpanSize(position: Int): Int {
                return spanList[position] + 1
            }*/
        }
    }
}