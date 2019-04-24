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

    init {
        context.obtainStyledAttributes(attrs, R.styleable.SkillView).apply {
            label = getString(R.styleable.SkillView_label).orEmpty()
            proficient = getBoolean(R.styleable.SkillView_proficient, false)
            recycle()
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        addView(
            LayoutInflater.from(context).inflate(R.layout.view_skill, this, false)
        )
        skill_icon.visibility = if (proficient) View.VISIBLE else View.INVISIBLE
        skill_label.text = label
    }

    fun setValue(value: String?) {
        value?.apply {
            skill_bonus.text = value
        }
    }
}