package com.dndapp.character.create

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dndapp.databinding.FragmentCharacterCreateBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterCreateFragment : Fragment() {

    private val viewModel by viewModel<CharacterCreateViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentCharacterCreateBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }
}