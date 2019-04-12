package com.dndapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import com.dndapp.R
import com.dndapp.data.entity.CharacterClass
import com.dndapp.dndAppActivity
import com.dndapp.toInt
import com.dndapp.view.InputFilterMinMax
import com.dndapp.viewmodel.CharacterListViewModel
import kotlinx.android.synthetic.main.fragment_character_create.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterCreateFragment : Fragment() {

    private val characterListViewModel: CharacterListViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_character_create, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dndAppActivity().setTitle(R.string.new_character)
        context?.run {
            character_class_spinner.adapter = ArrayAdapter<CharacterClass>(
                this, android.R.layout.simple_spinner_item, CharacterClass.values()
            )
        }
        character_class_spinner.post {
            character_class_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    character_strength_input.requestFocus()
                }
            }
        }
        character_level_input.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_NEXT) {
                character_class_spinner.requestFocusFromTouch()
                character_class_spinner.performClick()
            }
            true
        }
        listOf(
            character_level_input,
            character_strength_input, character_dexterity_input, character_constitution_input,
            character_intelligence_input, character_wisdom_input, character_charisma_input
        ).forEach { it.filters = arrayOf(InputFilterMinMax(1, 20)) }
        character_create_button.setOnClickListener {
            var hasError = false
            listOf(
                character_name_input, character_level_input,
                character_strength_input, character_dexterity_input, character_constitution_input,
                character_intelligence_input, character_wisdom_input, character_charisma_input
            ).forEach {
                if (it.text.isEmpty()) {
                    it.error = getString(
                        when (it) {
                            character_name_input -> R.string.error_character_create_name_required
                            character_level_input -> R.string.error_character_create_level_required
                            character_strength_input -> R.string.error_character_create_strength_required
                            character_dexterity_input -> R.string.error_character_create_dexterity_required
                            character_constitution_input -> R.string.error_character_create_constitution_required
                            character_intelligence_input -> R.string.error_character_create_intelligence_required
                            character_wisdom_input -> R.string.error_character_create_wisdom_required
                            character_charisma_input -> R.string.error_character_create_charisma_required
                            else -> R.string.error_character_create_field_required
                        }
                    )
                    hasError = true
                }
            }
            if (!hasError) {
                characterListViewModel.viewModelScope.launch {
                    val id = characterListViewModel.createCharacter(
                        character_name_input.text.toString(),
                        character_class_spinner.selectedItem as CharacterClass,
                        character_level_input.text.toInt(),
                        character_strength_input.text.toInt(),
                        character_dexterity_input.text.toInt(),
                        character_constitution_input.text.toInt(),
                        character_intelligence_input.text.toInt(),
                        character_wisdom_input.text.toInt(),
                        character_charisma_input.text.toInt()
                    )
                    dndAppActivity().hideKeyboard()
                    dndAppActivity().navController.navigate(R.id.character_created, bundleOf("id" to id))
                }
            }
        }

        character_name_input.requestFocus()
        dndAppActivity().showKeyboard()
    }
}
