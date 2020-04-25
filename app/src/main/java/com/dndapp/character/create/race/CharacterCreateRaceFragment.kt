package com.dndapp.character.create.race

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dndapp.R
import com.dndapp.character.create.CharacterCreateViewModel
import com.dndapp.databinding.FragmentCharacterCreateRaceBinding
import com.dndapp.extensions.sharedGraphViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharacterCreateRaceFragment : Fragment() {

    private val sharedViewModel by sharedGraphViewModel<CharacterCreateViewModel>(
        R.id.character_create_graph
    )
    private val viewModel by viewModel<CharacterCreateRaceViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentCharacterCreateRaceBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.sharedViewModel = sharedViewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }
}
