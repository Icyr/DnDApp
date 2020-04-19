package com.dndapp.character.create.race

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.dndapp.character.create.CharacterCreateViewModel
import com.dndapp.databinding.FragmentCharacterCreateRaceBinding
import com.dndapp.model.race.Race
import kotlinx.android.synthetic.main.fragment_character_create_race.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CharacterCreateRaceFragment : Fragment() {

    private val sharedViewModel by sharedViewModel<CharacterCreateViewModel>()
    private val viewModel by viewModel<CharacterCreateRaceViewModel>()

    private val adapter: RaceListAdapter?
        get() = create_race_spinner.adapter as? RaceListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentCharacterCreateRaceBinding.inflate(inflater, container, false).also {
            it.sharedViewModel = sharedViewModel
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (adapter == null) {
            create_race_spinner.adapter = RaceListAdapter(requireContext())
        }
        // TODO make a binding adapter for this
        create_race_spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                sharedViewModel.state.value?.race = adapter?.getItem(position) as Race
            }
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.races.observe(viewLifecycleOwner, Observer { list ->
            adapter?.setItems(list)
            val selectedRace = sharedViewModel.state.value?.race
            create_race_spinner.setSelection(selectedRace?.run {
                list.indexOfFirst { it.id == selectedRace.id }
            } ?: 0)
        })
    }
}
