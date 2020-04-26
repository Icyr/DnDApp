package com.dndapp.character.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import com.dndapp.R
import com.dndapp.databinding.FragmentCharacterListBinding
import com.dndapp.model.character.Character
import com.dndapp.model.character.CharacterRepository
import com.dndapp.utils.BaseListViewModel
import com.dndapp.viewmodel.Destination
import com.dndapp.viewmodel.NavigationViewModel
import kotlinx.android.synthetic.main.fragment_character_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterListFragment : Fragment() {

    private val viewModel by viewModel<CharacterListViewModel>()

    private val characterListAdapter: CharacterListAdapter?
        get() = character_list.adapter as? CharacterListAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return FragmentCharacterListBinding.inflate(inflater, container, false).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (characterListAdapter == null) {
            character_list.adapter = CharacterListAdapter()
        }
        character_list.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, Observer {
            characterListAdapter?.setItems(it.list)
        })
    }
}

class CharacterListViewModel(
    characterRepository: CharacterRepository,
    private val navigationViewModel: NavigationViewModel
) : BaseListViewModel<Character>(characterRepository) {

    fun onCreate() = navigationViewModel.navigate(Destination(R.id.character_create_graph))
}