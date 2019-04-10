package com.dndapp.view

import android.text.InputFilter
import android.text.Spanned


class InputFilterMinMax(private val min: Int, private val max: Int) : InputFilter {

    override fun filter(
        source: CharSequence?, start: Int, end: Int, dest: Spanned?, dstart: Int, dend: Int
    ): CharSequence? {
        try {
            var newVal = dest.toString().substring(0, dstart) + dest.toString().substring(dend, dest.toString().length)
            newVal = newVal.substring(0, dstart) + source.toString() + newVal.substring(dstart, newVal.length)
            val input = Integer.parseInt(newVal)
            if (isInRange(min, max, input)) return null
        } catch (nfe: NumberFormatException) {
            nfe.printStackTrace()
        }
        return ""
    }

    private fun isInRange(a: Int, b: Int, c: Int): Boolean {
        return if (b > a) c in a..b else c in b..a
    }
}