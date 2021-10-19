package dev.amin.tagadapter

import android.util.Log
import kotlin.math.ceil

class TagRow {

    /***
     * The available spans that the current row has.
     */
    private var freeSpans = MeasureHelper.SPAN_COUNT

    /***
     * List of the holders hosted in the current row.
     */
    val tagList = mutableListOf<Tag>()

    /***
     * List of the spans required by each holder hosted in the current row.
     */
    val spanList = mutableListOf<Int>()

    /***
    * Set alignment of all tags in the row by center through the adding two new tags
    * to the row: one - the first, another - the last. Total spans count equal freeSpans.
    */
    fun centerTags() {
        val emptyTitle = ""
        val leftSpan = freeSpans / 2
        if (leftSpan > 0) {
            spanList.add(0, leftSpan)
            tagList.add(0, Tag(emptyTitle))
        }
        val rightSpan = freeSpans - leftSpan
        if (rightSpan > 0) {
            spanList.add(rightSpan)
            tagList.add(Tag(emptyTitle))
        }
    }

    fun addTag(spanRequired: Float, tag: Tag) : Boolean {
        // Round the required span to Int
        val spanRequiredInt = ceil(spanRequired).toInt()

        Log.d("INFO", "freeSpans=$freeSpans")
        Log.d("INFO", "spanRequiredInt=$spanRequiredInt")

        // if the current row has enough available span
        if (spanRequired < freeSpans)
            if (tagList.add(tag)) {
                // Add the required span to spanList
                spanList.add(spanRequiredInt)

                // Minus the required span from the available span.
                freeSpans -= spanRequiredInt

                return true
            }

        return false
    }
}