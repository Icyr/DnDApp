package com.dndapp.character.create.background

import android.view.LayoutInflater
import android.view.ViewGroup
import com.dndapp.R
import com.dndapp.character.create.CharacterCreateViewModel
import com.dndapp.databinding.ItemBackgroundBinding
import com.dndapp.extensions.sharedGraphViewModel
import com.dndapp.model.background.Background
import com.dndapp.model.background.BackgroundRepository
import com.dndapp.utils.*
import com.dndapp.viewmodel.Back
import com.dndapp.viewmodel.NavigationViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.KoinComponent
import org.koin.core.inject

class BackgroundListFragment : BaseListFragment<Long, Background, BackgroundViewHolder>() {

    override val viewModel: BaseListViewModel<Background> by viewModel<BackgroundListViewModel>()
    private val sharedViewModel by sharedGraphViewModel<CharacterCreateViewModel>(
        R.id.character_create_graph
    )

    override fun getAdapter(): GenericAdapter<Long, Background, BackgroundViewHolder> {
        return BackgroundListAdapter(sharedViewModel)
    }
}

class BackgroundListViewModel(
    backgroundRepository: BackgroundRepository
) : BaseListViewModel<Background>(backgroundRepository)

class BackgroundListAdapter(
    private val sharedViewModel: CharacterCreateViewModel
) : GenericAdapter<Long, Background, BackgroundViewHolder>(), OnItemClickHandler<Background>, KoinComponent {

    private val navigationViewModel by inject<NavigationViewModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackgroundViewHolder {
        val binding = ItemBackgroundBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BackgroundViewHolder(binding, this)
    }

    override fun onClick(item: Background) {
        sharedViewModel.state.value?.background = item
        navigationViewModel.navigate(Back())
    }
}

class BackgroundViewHolder(
    private val binding: ItemBackgroundBinding,
    private val clickHandler: OnItemClickHandler<Background>
) : BindableViewHolder<Long, Background>(binding.root) {
    override fun bind(item: Background) {
        binding.background = item
        binding.clickHandler = clickHandler
        binding.executePendingBindings()
    }
}