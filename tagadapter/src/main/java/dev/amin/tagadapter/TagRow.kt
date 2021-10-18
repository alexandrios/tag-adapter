package dev.amin.tagadapter

import android.util.Log
import kotlin.math.ceil

class TagRow {

    /***
     * The available spans that the current row has.
     */
    var freeSpans = MeasureHelper.SPAN_COUNT

    /***
     * List of the holders hosted in the current row.
     */
    val tagList = mutableListOf<Tag>()

    /***
     * List of the spans required by each holder hosted in the current row.
     */
    val spanList = mutableListOf<Int>()

    fun centerTags() {
        var countSpans = freeSpans/2 - 1
        //if (countSpans > 3)
        //    countSpans -= 2
        if (countSpans > 0) {
            spanList.add(0, countSpans)
            tagList.add(0, Tag(""))
        }
    }

    fun addTag(spanRequired: Float, tag: Tag) : Boolean {

        val spanRequiredInt = ceil(spanRequired).toInt()
        // if the current row has enough available span
        if (spanRequiredInt < freeSpans)
            if (tagList.add(tag)) {

                // Round the required span to Int
                //val spanRequiredInt = ceil(spanRequired).toInt()
                Log.d("INFO", "freeSpans=$freeSpans")
                Log.d("INFO", "spanRequiredInt=$spanRequiredInt")

                // Add the required span to spanList
                spanList.add(spanRequiredInt)

                // Minus the required span from the available span.
                freeSpans -= spanRequiredInt

                return true
            }

        return false
    }
}