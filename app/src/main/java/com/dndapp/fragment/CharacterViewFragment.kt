package com.dndapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.viewModelScope
import com.dndapp.R
import com.dndapp.databinding.FragmentCharacterViewBinding
import com.dndapp.dndAppActivity
import com.dndapp.viewmodel.CharacterViewViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterViewFragment : Fragment() {

    private val characterViewViewModel: CharacterViewViewModel by viewModel()
    private lateinit var characterBinding: FragmentCharacterViewBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        characterBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_character_view, container, false
        )
        return characterBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.run {
            val id = getLong("id")
            characterViewViewModel.viewModelScope.launch {
                val dbCharacter = characterViewViewModel.getCharacter(id)
                dndAppActivity().title = dbCharacter.name
                characterBinding.character = dbCharacter
                characterBinding.executePendingBindings()
            }
        }
    }
}