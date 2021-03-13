package com.example.crane.ui.choose_crane

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.crane.R
import com.example.ui_components.base.BaseFragment
import com.example.crane.databinding.FragmentChooseCraneBinding
import com.example.crane.ui.items.ChooseCraneTypeUi
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import org.koin.androidx.viewmodel.ext.android.viewModel

class ChooseCraneFragment : BaseFragment() {
    private val viewModel: ChooseCraneViewModel by viewModel()
    private lateinit var binding: FragmentChooseCraneBinding
    private val groupAdapter = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChooseCraneBinding.inflate(inflater, container, false).apply {
            viewModel = viewModel
            lifecycleOwner = this@ChooseCraneFragment.viewLifecycleOwner
        }
        initBackDispatcher()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }

    override fun onStart() {
        super.onStart()
        viewModel.items.observe(viewLifecycleOwner, Observer(::onItemsChanged))

    }

    private fun onItemsChanged(data: List<ChooseCraneTypeUi>) {
        groupAdapter.clear()
        groupAdapter.addAll(data)
        groupAdapter.setOnItemClickListener { item, _ ->
            item as ChooseCraneTypeUi
            findNavController().navigate(
                R.id.action_chooseCraneFragment_to_craneInfoFragment,
                bundleOf("id" to item.id)
            )
        }
    }


    private fun initRecyclerView() {
        binding.craneTypesRv.apply {
            adapter = groupAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.requestItems()
    }
}