package com.dndapp.character.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dndapp.databinding.ViewCharacterItemBinding
import com.dndapp.model.character.Character
import com.dndapp.utils.BindableViewHolder
import com.dndapp.utils.GenericAdapter

class CharacterListAdapter : GenericAdapter<String, Character, CharacterViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ViewCharacterItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CharacterViewHolder(binding)
    }
}

class CharacterViewHolder(
    private val binding: ViewCharacterItemBinding
) : BindableViewHolder<String, Character>(binding.root) {
    override fun bind(item: Character) {
        binding.character = item
        binding.executePendingBindings()
    }
}