package com.dndapp.utils

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.dndapp.BR
import com.dndapp.databinding.FragmentBaseListBinding
import com.dndapp.model.Repository
import kotlinx.android.synthetic.main.fragment_base_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Suppress("UNCHECKED_CAST")
abstract class BaseListFragment<K, T : HasId<K>, VH : BindableViewHolder<K, T>> : Fragment() {

    abstract val viewModel: BaseListViewModel<T>

    private val viewAdapter: GenericAdapter<K, T, VH>?
        get() = base_list.adapter as? GenericAdapter<K, T, VH>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return FragmentBaseListBinding.inflate(inflater, container, false).also {
            // the only way I have found to set this to layout is casting
            it.viewModel = viewModel as BaseListViewModel<Any>
            it.lifecycleOwner = viewLifecycleOwner
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        if (viewAdapter == null) {
            base_list.adapter = getAdapter()
        }
        base_list.addItemDecoration(
            DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL)
        )
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.state.observe(viewLifecycleOwner, Observer {
            viewAdapter?.setItems(it.list)
        })
    }

    abstract fun getAdapter(): GenericAdapter<K, T, VH>
}

abstract class BaseListViewModel<T>(
    private val repository: Repository<T>
) : ViewModel() {

    val state = BaseObservableLiveData(BaseListState<T>())

    init {
        initLoad()
    }

    private fun initLoad() {
        state.value?.apply {
            viewModelScope.launch(Dispatchers.IO) {
                loading = true
                list = repository.loadAll()
                loading = false
            }
        }
    }
}

class BaseListState<T> : BaseObservable() {
    @get:Bindable
    var loading = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.loading)
        }

    @get:Bindable
    var list: List<T> = emptyList()
        set(value) {
            field = value
            notifyPropertyChanged(BR.list)
        }
}

interface OnItemClickHandler<T> {
    fun onClick(item: T)
}