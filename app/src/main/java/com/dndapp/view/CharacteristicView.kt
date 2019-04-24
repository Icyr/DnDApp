package com.dndapp.view

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.dndapp.data.entity.Character
import com.dndapp.data.entity.Characteristic
import com.dndapp.databinding.ViewCharacteristicBinding

class CharacteristicView(context: Context, attrs: AttributeSet) : LinearLayout(context, attrs) {

    private val binding: ViewCharacteristicBinding =
        ViewCharacteristicBinding.inflate(LayoutInflater.from(context), this, true)

    fun setCharacter(value: Character?) {
        binding.character = value
        binding.executePendingBindings()
    }

    fun setCharacteristic(value: Characteristic?) {
        binding.characteristic = value
        binding.executePendingBindings()
    }
}

@BindingAdapter("app:characteristicText")
fun TextView.characteristicText(characteristic: Characteristic?) {
    characteristic?.apply {
        text = context.getString(resourceId)
    }
}