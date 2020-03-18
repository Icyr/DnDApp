package com.dndapp.character.list

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dndapp.R
import com.dndapp.character.entity.CharacterEntityFragmentArgs
import com.dndapp.databinding.ViewCharacterItemBinding
import com.dndapp.model.character.Character
import com.dndapp.utils.BindableViewHolder
import com.dndapp.utils.GenericAdapter
import com.dndapp.viewmodel.Destination
import com.dndapp.viewmodel.NavigationViewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class CharacterListAdapter(
) : GenericAdapter<String, Character, CharacterViewHolder>(), KoinComponent,
    CharacterListItemClickHandler {
    private val navigationViewModel by inject<NavigationViewModel>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val binding = ViewCharacterItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return CharacterViewHolder(this, binding)
    }

    override fun onClick(position: Int) {
        val args = CharacterEntityFragmentArgs().toBundle()
        navigationViewModel.navigate(Destination(R.id.fragment_character_entity, args))
    }

}

class CharacterViewHolder(
    private val handler: CharacterListItemClickHandler,
    private val binding: ViewCharacterItemBinding
) : BindableViewHolder<String, Character>(binding.root) {

    override fun bind(item: Character) {
        binding.character = item
        binding.executePendingBindings()
        binding.handler = handler
    }
}

public interface CharacterListItemClickHandler {
    fun onClick(position: Int)
}