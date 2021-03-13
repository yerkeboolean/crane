package com.example.crane.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.crane.R
import com.example.ui_components.base.BaseFragment
import com.example.crane.databinding.FragmentProfileBinding
import com.example.data.common.SharedPreferencesSetting
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.ext.android.inject

class ProfileFragment : BaseFragment() {

    private lateinit var binding: FragmentProfileBinding
    private val sharedPreferencesSetting: SharedPreferencesSetting by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@ProfileFragment.viewLifecycleOwner
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        first_name_tv.text = sharedPreferencesSetting.firstName
        second_name_tv.text = sharedPreferencesSetting.secondName
        last_name_tv.text = sharedPreferencesSetting.lastName
        job_position_tv.text = sharedPreferencesSetting.jobPosition

        initOnClickListener()
    }

    private fun initOnClickListener() {
        btn_change.setOnClickListener {
            findNavController().navigate(R.id.action_navigation_profile_to_editProfileFragment)
        }
    }
}