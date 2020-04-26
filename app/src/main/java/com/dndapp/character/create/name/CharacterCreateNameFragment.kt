package com.dndapp.character.create.name

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dndapp.R
import com.dndapp.character.create.CharacterCreateViewModel
import com.dndapp.databinding.FragmentCharacterCreateNameBinding
import com.dndapp.extensions.sharedGraphViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterCreateNameFragment : Fragment() {

    private val sharedViewModel by sharedGraphViewModel<CharacterCreateViewModel>(
        R.id.character_create_graph
    )
    private val viewModel by viewModel<CharacterCreateNameViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentCharacterCreateNameBinding.inflate(inflater, container, false).also {
            it.sharedViewModel = sharedViewModel
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }
}