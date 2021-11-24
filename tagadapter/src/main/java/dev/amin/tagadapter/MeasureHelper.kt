package dev.amin.tagadapter

import android.util.Log
import android.view.ViewGroup
import android.view.ViewTreeObserver
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_tag.view.*

class MeasureHelper(
    private val adapter: TagAdapter,
    private val count: Int
) {

    companion object {

        /***
         * Number of the total spans that the layout manager is allowed to draw.
         */
        const val SPAN_COUNT = 52
    }

    /***
     * Holds the count of measured views to know when measuring is finished.
     */
    private var measuredCount = 0

    /***
     * TagRowManager is responsible for adding and getting of the tags.
     */
    private val rowManager = TagRowManager()

    /***
     * baseCell is the value used for determining how much span will a holder require.
     */
    private var baseCell: Float = 0f

    fun measureBaseCell(width: Int) {
        baseCell = (width / SPAN_COUNT).toFloat()
    }

    /***
     * If measuring is finished or not.
     */
    fun shouldMeasure() = measuredCount != count

    fun getItems() = rowManager.getTags()

    fun getSpans() = rowManager.getSpans()

    /***
     * Calling this method every time a cell is measured and
     * if there is no more cell to measure it will notify the adapter
     * to update it self.
     */
    private fun cellMeasured() {

        if (!adapter.measuringDone && !shouldMeasure())
            adapter.measuringDone = true
    }

    fun measure(holder: RecyclerView.ViewHolder, tag: Tag) {

        /* Get the ItemView and minimize it's height as small as possible
            to fit as many cells as it's possible in the screen. */
        val itemView = holder.itemView.apply {
            layoutParams.height = 1
        }

        val globalLayoutListener = object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {

                // Remove the observer to avoid multiple callbacks.
                itemView.viewTreeObserver.removeOnGlobalLayoutListener(this)

                //Log.d("INFO", tag.title)
                Log.d("INFOTAG", "rowTitle.text=${itemView.rowTitle.text.trim()}")

                val marginStart = (itemView.rowTitle.layoutParams as ViewGroup.MarginLayoutParams).marginStart
                //Log.d("INFO", "marginStart = $marginStart" )
                // Include also the horizontal margin of the layout.
                val marginTotal = marginStart * 2
                //Log.d("INFO", "marginTotal = $marginTotal" )

                // Required span for the holder in Float/
                val span = (itemView.rowTitle.width + marginTotal) / baseCell
                Log.d("INFOTAG", "itemView.rowTitle.width=${itemView.rowTitle.width}")
                Log.d("INFOTAG", "span = $span" )

                // Increase measured count.
                measuredCount++

                rowManager.add(span, tag)

                if (measuredCount == count) {
                    rowManager.centerLastRow()
                }

                cellMeasured()
            }
        }

        // Observe for the view
        itemView.viewTreeObserver.addOnGlobalLayoutListener(globalLayoutListener)
    }
}