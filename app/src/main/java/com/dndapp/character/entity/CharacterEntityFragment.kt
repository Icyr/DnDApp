package com.dndapp.character.entity

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

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
        return inflater.inflate(R.layout.character_entity_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CharacterEntityViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
