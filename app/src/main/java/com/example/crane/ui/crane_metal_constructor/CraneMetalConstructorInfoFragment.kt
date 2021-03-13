package com.example.crane.ui.crane_metal_constructor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.crane.R
import com.example.ui_components.custom_view.CustomToast
import com.example.crane.databinding.FragmentCraneMetalConstructorInfoBinding
import com.example.crane.ui.items.CraneInfoUi
import com.example.crane.ui.items.CranePartsUi
import com.example.crane.ui.items.CraneTypeUi
import com.example.ui_components.base.BaseFragment
import com.example.ui_components.events.Event
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.fragment_crane_full_info.*
import kotlinx.android.synthetic.main.fragment_crane_info.*
import kotlinx.android.synthetic.main.fragment_crane_metal_constructor_info.*
import kotlinx.android.synthetic.main.fragment_crane_metal_constructor_info.btn_apply
import kotlinx.android.synthetic.main.fragment_crane_metal_constructor_info.btn_save
import kotlinx.android.synthetic.main.fragment_crane_metal_constructor_info.ic_back
import kotlinx.android.synthetic.main.fragment_crane_metal_constructor_info.root_cl
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class CraneMetalConstructorInfoFragment : BaseFragment() {

    private val viewModel: CraneMetalConstructorInfoViewModel by viewModel()
    private lateinit var binding: FragmentCraneMetalConstructorInfoBinding
    private val groupAdapterConstr = GroupAdapter<GroupieViewHolder>()
    private val groupAdapterFormulas = GroupAdapter<GroupieViewHolder>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCraneMetalConstructorInfoBinding.inflate(inflater, container, false).apply {
                viewModel = viewModel
                lifecycleOwner = this@CraneMetalConstructorInfoFragment.viewLifecycleOwner
            }
        initBackDispatcher()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.i("onViewCreated")
        binding.headerTitle.text = arguments?.getString("header_title")!!
        viewModel.requestItems(
            arguments?.getInt("id")!!
        )
        initRecyclerView()
        initOnClickListener()

    }

    private fun initOnClickListener() {
        ic_back.setOnClickListener {
            viewModel.saveData()
            activity?.onBackPressed()
        }
        btn_apply.setOnClickListener {
            if (viewModel.checkOnCompleteness()) {
                viewModel.saveData()
                findNavController().navigate(R.id.action_craneMetalConstructorFragment_to_navigation_crane_types)
            } else {
                CustomToast(root_cl).showMessage("Заполните поля")
            }
        }
        btn_save.setOnClickListener {
            CustomToast(root_cl).showMessage("Сохранено")
            viewModel.saveData()
        }
    }

    private fun initRecyclerView() {
        binding.constrRv.apply {
            adapter = groupAdapterConstr
        }
        binding.formulasRv.apply {
            adapter = groupAdapterFormulas
        }
    }

    override fun onStart() {
        super.onStart()
        viewModel.itemsConstr.observe(viewLifecycleOwner, Observer(::onItemsConstrChanged))
        viewModel.hideKeyboardEvent.observe(viewLifecycleOwner, Observer(::hideKeyboard))
        viewModel.saveEvent.observe(viewLifecycleOwner, Observer(::onSave))

    }

    private fun onSave(event: Event<Boolean>) {
        CustomToast(root_cl).showMessage("Сохранено")
    }

    private fun hideKeyboard(event: Event<Boolean>) {
        hideKeyBoard()
    }

    private fun onItemsConstrChanged(data: List<CranePartsUi>) {
        Timber.i("OnItemsChanged")
        groupAdapterConstr.addAll(data)
        groupAdapterConstr.notifyDataSetChanged()

    }

}