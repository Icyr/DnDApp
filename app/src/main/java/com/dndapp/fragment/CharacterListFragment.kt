package com.dndapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.*
import com.dndapp.R
import com.dndapp.adapter.CharacterDiffUtilCallback
import com.dndapp.adapter.CharacterHolder
import com.dndapp.adapter.CharacterListAdapter
import com.dndapp.dndAppActivity
import com.dndapp.viewmodel.CharacterListViewModel
import kotlinx.android.synthetic.main.fragment_character_list.*
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class CharacterListFragment : Fragment() {

    private val characterListViewModel: CharacterListViewModel by viewModel()
    private val characterListAdapter: CharacterListAdapter by inject()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_character_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dndAppActivity().setTitle(R.string.characters)
        character_list.apply {
            layoutManager = LinearLayoutManager(context).apply {
                orientation = LinearLayoutManager.VERTICAL
            }
            adapter = characterListAdapter
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            itemAnimator = DefaultItemAnimator()
        }
        val callback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (viewHolder is CharacterHolder) {
                    characterListAdapter.deleteCharacter(viewHolder.adapterPosition)
                }
            }
        }
        ItemTouchHelper(callback).attachToRecyclerView(character_list)
        button_create_character.setOnClickListener {
            dndAppActivity().navController.navigate(R.id.create_character)
        }
        characterListAdapter.onClickHandler = {
            dndAppActivity().navController.navigate(R.id.view_character, bundleOf("id" to it.id))
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        characterListViewModel.characters.observe(viewLifecycleOwner, Observer {
            val diffCallback = CharacterDiffUtilCallback(characterListAdapter.characters, it)
            val result = DiffUtil.calculateDiff(diffCallback)
            characterListAdapter.characters = it
            result.dispatchUpdatesTo(characterListAdapter)
        })
    }

}
