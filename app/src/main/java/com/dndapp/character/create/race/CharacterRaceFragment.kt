package com.dndapp.character.create.race

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dndapp.R
import com.dndapp.character.create.CharacterCreateViewModel
import com.dndapp.databinding.FragmentCharacterCreateBinding
import com.dndapp.databinding.FragmentCharacterRaceBinding
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class CharacterRaceFragment : Fragment() {

    private val viewModel by sharedViewModel<CharacterCreateViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentCharacterRaceBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }

}
