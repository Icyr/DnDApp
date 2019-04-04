package com.dndapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dndapp.R
import com.dndapp.activity.DndAppActivity
import kotlinx.android.synthetic.main.fragment_character_list.*

class CharacterListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        button_create_character.setOnClickListener {
            (activity as DndAppActivity).navController.navigate(R.id.create_character)
        }
    }
}
