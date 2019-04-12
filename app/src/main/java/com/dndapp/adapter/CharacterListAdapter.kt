package com.dndapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dndapp.R
import com.dndapp.data.entity.Character
import com.dndapp.databinding.CharacterListItemBinding
import com.dndapp.viewmodel.CharacterListViewModel


class CharacterListAdapter(private val characterListViewModel: CharacterListViewModel) :
    RecyclerView.Adapter<CharacterHolder>() {

    var characters: List<Character> = emptyList()
    lateinit var onClickHandler: (character: Character) -> Unit

    fun deleteCharacter(position: Int) {
        val character = characters[position]
        characters = characters - character
        notifyItemRemoved(position)
        characterListViewModel.deleteCharacter(character)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<CharacterListItemBinding>(inflater, R.layout.character_list_item, parent, false)
        return CharacterHolder(binding)
    }

    override fun getItemCount(): Int = characters.size

    override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
        val character = characters[position]
        holder.bind(character, CharacterListItemHandlers(onClickHandler))
    }
}

class CharacterHolder(private val binding: CharacterListItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(character: Character, handlers: CharacterListItemHandlers) {
        binding.character = character
        binding.handlers = handlers
        binding.executePendingBindings()
    }
}

class CharacterListItemHandlers(val onClickHandler: (character: Character) -> Unit)

class CharacterDiffUtilCallback(
    private val oldList: List<Character>,
    private val newList: List<Character>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCharacter = oldList[oldItemPosition]
        val newCharacter = newList[newItemPosition]
        return oldCharacter.id == newCharacter.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldCharacter = oldList[oldItemPosition]
        val newCharacter = newList[newItemPosition]
        return oldCharacter.name == newCharacter.name
    }
}
