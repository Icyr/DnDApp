package com.dndapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dndapp.R
import com.dndapp.activity.DndAppActivity
import com.dndapp.data.Character
import com.dndapp.databinding.CharacterListItemBinding
import com.dndapp.viewmodel.CharacterListViewModel
import kotlinx.android.synthetic.main.fragment_character_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterListFragment : Fragment() {

    private val characterListViewModel: CharacterListViewModel by viewModel()

    private lateinit var characterListAdapter: CharacterListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        characterListAdapter = CharacterListAdapter()
        val manager = LinearLayoutManager(context)
        manager.orientation = LinearLayoutManager.VERTICAL
        character_list.apply {
            layoutManager = manager
            adapter = characterListAdapter
        }
        button_create_character.setOnClickListener {
            (activity as DndAppActivity).navController.navigate(R.id.create_character)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        characterListViewModel.characters.observe(viewLifecycleOwner, Observer {
            characterListAdapter.characters = it
            characterListAdapter.notifyDataSetChanged()
        })
    }

    class CharacterHolder(private val binding: CharacterListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(character: Character) {
            binding.character = character
            binding.executePendingBindings()
        }
    }

    class CharacterListAdapter : RecyclerView.Adapter<CharacterHolder>() {

        var characters: List<Character> = ArrayList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = DataBindingUtil.inflate<CharacterListItemBinding>(inflater, R.layout.character_list_item, parent, false)
            return CharacterHolder(binding)
        }

        override fun getItemCount(): Int = characters.size

        override fun onBindViewHolder(holder: CharacterHolder, position: Int) {
            holder.bind(characters[position])
        }
    }
}
