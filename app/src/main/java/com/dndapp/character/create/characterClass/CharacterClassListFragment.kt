package com.dndapp.character.create.characterClass

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dndapp.R
import com.dndapp.character.create.CharacterCreateViewModel
import com.dndapp.databinding.ItemCharacterClassBinding
import com.dndapp.databinding.ItemRaceBinding
import com.dndapp.extensions.sharedGraphViewModel
import com.dndapp.model.characterClass.CharacterClass
import com.dndapp.model.characterClass.CharacterClassRepository
import com.dndapp.model.race.Race
import com.dndapp.model.race.RaceRepository
import com.dndapp.utils.*
import com.dndapp.viewmodel.Back
import com.dndapp.viewmodel.NavigationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class CharacterClassListFragment : BaseListFragment<Long, CharacterClass, CharacterClassViewHolder>() {

    override val viewModel: BaseListViewModel<CharacterClass> by viewModel<CharacterClassListViewModel>()
    private val sharedViewModel by sharedGraphViewModel<CharacterCreateViewModel>(
        R.id.character_create_graph
    )

    override fun getAdapter(): GenericAdapter<Long, CharacterClass, CharacterClassViewHolder> {
        return CharacterClassListAdapter(sharedViewModel)
    }
}

class CharacterClassListViewModel(
    characterClassRepository: CharacterClassRepository
) : BaseListViewModel<CharacterClass>(characterClassRepository)

class CharacterClassListAdapter(
    private val sharedViewModel: CharacterCreateViewModel
) : GenericAdapter<Long, CharacterClass, CharacterClassViewHolder>(), OnItemClickHandler<CharacterClass>, KoinComponent {

    private val navigationViewModel by inject<NavigationViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterClassViewHolder {
        val binding = ItemCharacterClassBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CharacterClassViewHolder(binding, this)
    }

    override fun onClick(item: CharacterClass) {
        sharedViewModel.state.value?.characterClass = item
        navigationViewModel.navigate(Back())
    }
}

class CharacterClassViewHolder(
    private val binding: ItemCharacterClassBinding,
    private val clickHandler: OnItemClickHandler<CharacterClass>
) : BindableViewHolder<Long, CharacterClass>(binding.root) {

    override fun bind(item: CharacterClass) {
        binding.characterClass = item
        binding.clickHandler = clickHandler
        binding.executePendingBindings()
    }
}