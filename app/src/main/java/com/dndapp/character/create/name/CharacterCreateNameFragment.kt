package com.dndapp.character.create.name

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dndapp.character.create.CharacterCreateViewModel
import com.dndapp.databinding.FragmentCharacterCreateNameBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterCreateNameFragment : Fragment() {

    private val sharedViewModel by sharedViewModel<CharacterCreateViewModel>()
    private val viewModel by viewModel<CharacterCreateNameViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentCharacterCreateNameBinding.inflate(inflater, container, false).also {
            it.sharedViewModel = sharedViewModel
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }
}