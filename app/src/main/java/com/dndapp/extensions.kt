package com.dndapp

import android.text.Editable
import androidx.fragment.app.Fragment
import com.dndapp.activity.DndAppActivity

fun Editable.toInt(): Int = toString().toInt()

fun Fragment.dndAppActivity(): DndAppActivity = activity as DndAppActivity