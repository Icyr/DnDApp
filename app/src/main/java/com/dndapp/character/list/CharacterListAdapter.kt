package com.dndapp.character.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dndapp.R
import com.dndapp.character.view.CharacterViewFragmentArgs
import com.dndapp.databinding.ItemCharacterBinding
import com.dndapp.model.character.Character
import com.dndapp.utils.BindableViewHolder
import com.dndapp.utils.GenericAdapter
import com.dndapp.utils.OnItemClickHandler
import com.dndapp.viewmodel.Destination
import com.dndapp.viewmodel.NavigationViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class CharacterListAdapter : GenericAdapter<Long, Character, CharacterViewHolder>(),
    KoinComponent, OnItemClickHandler<Character> {

    private val navigationViewModel by inject<NavigationViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ItemCharacterBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CharacterViewHolder(binding, this)
    }

    override fun onClick(item: Character) {
        val args = CharacterViewFragmentArgs(item).toBundle()
        navigationViewModel.navigate(Destination(R.id.fragment_character_view, args))
    }
}

class CharacterViewHolder(
    private val binding: ItemCharacterBinding,
    private val clickHandler: OnItemClickHandler<Character>
) : BindableViewHolder<Long, Character>(binding.root) {

    override fun bind(item: Character) {
        binding.character = item
        binding.clickHandler = clickHandler
        binding.executePendingBindings()
    }
}