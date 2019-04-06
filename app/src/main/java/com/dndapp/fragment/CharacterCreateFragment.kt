package com.dndapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dndapp.R
import com.dndapp.activity.DndAppActivity
import com.dndapp.viewmodel.CharacterListViewModel
import kotlinx.android.synthetic.main.fragment_character_create.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterCreateFragment : Fragment() {

    private val characterListViewModel: CharacterListViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        character_create_button.setOnClickListener {
            if (character_name_input.text.isEmpty()) {
                character_name_input.error = getString(R.string.error_character_create_name_required)
            } else {
                characterListViewModel.createCharacter(character_name_input.text.toString())
                (activity as DndAppActivity).navController.navigate(R.id.character_created)
            }
        }
    }
}
