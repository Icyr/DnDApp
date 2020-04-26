package com.dndapp.character.create.race

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dndapp.R
import com.dndapp.character.create.CharacterCreateViewModel
import com.dndapp.databinding.ItemRaceBinding
import com.dndapp.extensions.sharedGraphViewModel
import com.dndapp.model.race.Race
import com.dndapp.model.race.RaceRepository
import com.dndapp.utils.*
import com.dndapp.viewmodel.Back
import com.dndapp.viewmodel.NavigationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class RaceListFragment : BaseListFragment<Long, Race, RaceViewHolder>() {

    override val viewModel: BaseListViewModel<Race> by viewModel<RaceListViewModel>()
    private val sharedViewModel by sharedGraphViewModel<CharacterCreateViewModel>(
        R.id.character_create_graph
    )

    override fun getAdapter(): GenericAdapter<Long, Race, RaceViewHolder> {
        return RaceListAdapter(sharedViewModel)
    }
}

class RaceListViewModel(
    raceRepository: RaceRepository
) : BaseListViewModel<Race>(raceRepository)

class RaceListAdapter(
    private val sharedViewModel: CharacterCreateViewModel
) : GenericAdapter<Long, Race, RaceViewHolder>(), OnItemClickHandler<Race>, KoinComponent {

    private val navigationViewModel by inject<NavigationViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RaceViewHolder {
        val binding = ItemRaceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RaceViewHolder(binding, this)
    }

    override fun onClick(item: Race) {
        sharedViewModel.state.value?.race = item
        navigationViewModel.navigate(Back())
    }
}

class RaceViewHolder(
    private val binding: ItemRaceBinding,
    private val clickHandler: OnItemClickHandler<Race>
) : BindableViewHolder<Long, Race>(binding.root) {

    override fun bind(item: Race) {
        binding.race = item
        binding.clickHandler = clickHandler
        binding.executePendingBindings()
    }
}