package com.dndapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dndapp.R
import com.dndapp.activity.DndAppActivity
import com.dndapp.data.Character
import com.dndapp.viewmodel.CharacterListViewModel
import kotlinx.android.synthetic.main.fragment_character_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterListFragment : Fragment() {

    private val characterListViewModel: CharacterListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        test_text_view.text = "Empty"
        button_create_character.setOnClickListener {
            (activity as DndAppActivity).navController.navigate(R.id.create_character)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        characterListViewModel.characters.observe(viewLifecycleOwner, Observer {
            updateCharacters(it)
        })
    }

    private fun updateCharacters(characters: List<Character>) {
        if (characters.isNullOrEmpty()) {
            test_text_view.text = "Empty"
        } else {
            test_text_view.text = characters.map { it.name }.joinToString(",")
        }
    }
}
