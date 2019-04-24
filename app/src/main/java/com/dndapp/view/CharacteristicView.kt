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
    }

}