package com.dndapp.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.dndapp.R
import kotlinx.android.synthetic.main.view_characteristic.view.*

class CharacteristicView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val label: String

    init {
        context.obtainStyledAttributes(attrs, R.styleable.CharacteristicView).apply {
            label = getString(R.styleable.CharacteristicView_label).orEmpty()
            recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        addView(
            LayoutInflater.from(context).inflate(R.layout.view_characteristic, this, false)
        )
        characteristic_label.text = label
    }

    fun setValue(value: Int) {
        characteristic_value.text = value.toString()
        val bonus = getBonus(value)
        characteristic_bonus.text = if (bonus >= 0) "+$bonus" else bonus.toString()
    }

    private fun getBonus(value: Int): Int = when (value) {
        0, 1 -> -5
        2, 3 -> -4
        4, 5 -> -3
        6, 7 -> -2
        8, 9 -> -1
        10, 11 -> 0
        12, 13 -> 1
        14, 15 -> 2
        16, 17 -> 3
        18, 19 -> 4
        20, 21 -> 5
        22, 23 -> 6
        24, 25 -> 7
        26, 27 -> 8
        28, 29 -> 9
        30 -> 10
        else -> -5
    }
}