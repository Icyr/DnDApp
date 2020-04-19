package com.dndapp.character.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dndapp.R
import com.dndapp.character.view.CharacterViewFragmentArgs
import com.dndapp.databinding.ItemCharacterBinding
import com.dndapp.model.character.Character
import com.dndapp.utils.BindableViewHolder
import com.dndapp.utils.GenericAdapter
import com.dndapp.viewmodel.Destination
import com.dndapp.viewmodel.NavigationViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class CharacterListAdapter : GenericAdapter<Long, Character, CharacterViewHolder>(),
    KoinComponent, CharacterListItemClickHandler {

    private val navigationViewModel by inject<NavigationViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CharacterViewHolder(this, binding)
    }

    override fun onClick(character: Character) {
        val args = CharacterViewFragmentArgs(character).toBundle()
        navigationViewModel.navigate(Destination(R.id.fragment_character_view, args))
    }
}

class CharacterViewHolder(
    private val handler: CharacterListItemClickHandler,
    private val binding: ItemCharacterBinding
) : BindableViewHolder<Long, Character>(binding.root) {

    override fun bind(item: Character) {
        binding.character = item
        binding.handler = handler
        binding.executePendingBindings()
    }
}

interface CharacterListItemClickHandler {
    fun onClick(character: Character)
}