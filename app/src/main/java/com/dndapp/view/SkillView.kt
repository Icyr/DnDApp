package com.dndapp.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.dndapp.R
import kotlinx.android.synthetic.main.view_skill.view.*

class SkillView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val label: String
    private val proficient: Boolean
    private val value: Int

    init {
        context.obtainStyledAttributes(attrs, R.styleable.SkillView).apply {
            label = getString(R.styleable.SkillView_label).orEmpty()
            proficient = getBoolean(R.styleable.SkillView_proficient, false)
            value = getInt(R.styleable.SkillView_value, 0)
            recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        addView(
            LayoutInflater.from(context).inflate(R.layout.view_skill, this, false)
        )
        skill_icon.visibility = if (proficient) View.VISIBLE else View.INVISIBLE
        val valueString = if (value >= 0) "+$value" else value.toString()
        skill_label.text = context.getString(R.string.skill_number, label, valueString)
    }
}