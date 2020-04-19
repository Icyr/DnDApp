package com.dndapp.character.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.dndapp.R
import kotlinx.android.synthetic.main.fragment_character_view.*

class CharacterViewFragment : Fragment() {

    private val args by navArgs<CharacterViewFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_character_view, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        character_view_name.text = args.character.name
        character_view_race.text = args.character.race.name
    }
}
