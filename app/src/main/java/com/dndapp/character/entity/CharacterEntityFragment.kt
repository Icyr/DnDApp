package com.dndapp.character.entity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.dndapp.R


class CharacterEntityFragment : Fragment() {

    companion object {
        fun newInstance() = CharacterEntityFragment()
    }

    private lateinit var viewModel: CharacterEntityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_entity, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CharacterEntityViewModel::class.java)
        val charName: TextView = view!!.findViewById(R.id.fragment_character_entity)
        val characterName = arguments?.let { CharacterEntityFragmentArgs.fromBundle(it).characterName }
        charName.text = characterName
    }

}
