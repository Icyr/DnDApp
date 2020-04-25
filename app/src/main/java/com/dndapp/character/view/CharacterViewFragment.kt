package com.dndapp.character.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.dndapp.databinding.FragmentCharacterViewBinding

class CharacterViewFragment : Fragment() {

    private val args by navArgs<CharacterViewFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return FragmentCharacterViewBinding.inflate(inflater, container, false).also {
            it.character = args.character
        }.root
    }
}
