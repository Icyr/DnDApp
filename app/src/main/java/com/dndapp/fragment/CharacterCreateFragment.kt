package com.dndapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.dndapp.R
import com.dndapp.activity.DndAppActivity
import com.dndapp.data.entity.CharacterClass
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
        context?.run {
            character_class_spinner.adapter = ArrayAdapter<CharacterClass>(
                this, android.R.layout.simple_spinner_item, CharacterClass.values()
            )
        }
        character_create_button.setOnClickListener {
            when {
                character_name_input.text.isEmpty() -> character_name_input.error = getString(R.string.error_character_create_name_required)
                character_level_input.text.isEmpty() -> character_level_input.error = getString(R.string.error_character_create_level_required)
                else -> {
                    characterListViewModel.createCharacter(
                        character_name_input.text.toString(),
                        character_class_spinner.selectedItem as CharacterClass,
                        character_level_input.text.toString().toInt()
                    )
                    (activity as DndAppActivity).navController.navigate(R.id.character_created)
                }
            }
        }
    }
}
