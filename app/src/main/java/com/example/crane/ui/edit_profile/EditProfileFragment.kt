package com.example.crane.ui.edit_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.crane.R
import com.example.ui_components.base.BaseFragment
import com.example.ui_components.custom_view.CustomToast
import com.example.crane.databinding.FragmentEditProfileBinding
import com.example.data.common.SharedPreferencesSetting
import kotlinx.android.synthetic.main.fragment_edit_profile.*
import org.koin.android.ext.android.inject

class EditProfileFragment : BaseFragment() {
    private lateinit var binding: FragmentEditProfileBinding
    private val sharedPreferencesSetting: SharedPreferencesSetting by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditProfileBinding.inflate(inflater, container, false).apply {
            lifecycleOwner = this@EditProfileFragment.viewLifecycleOwner
        }
        initBackDispatcher()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstNameEditText.setText(sharedPreferencesSetting.firstName)
        secondNameEditText.setText(sharedPreferencesSetting.secondName)
        lastNameEditText.setText(sharedPreferencesSetting.lastName)
        jobPositionEditText.setText(sharedPreferencesSetting.jobPosition)

        initOnClickListener()
    }

    private fun initOnClickListener() {
        ic_back.setOnClickListener {
            activity?.onBackPressed()
        }
        btn_apply.setOnClickListener {
            if (!firstNameEditText.text.isNullOrEmpty() && !secondNameEditText.text.isNullOrEmpty() &&
                !lastNameEditText.text.isNullOrEmpty() && !jobPositionEditText.text.isNullOrEmpty()
            ) {
                sharedPreferencesSetting.firstName = firstNameEditText.text.toString()
                sharedPreferencesSetting.secondName = secondNameEditText.text.toString()
                sharedPreferencesSetting.lastName = lastNameEditText.text.toString()
                sharedPreferencesSetting.jobPosition = jobPositionEditText.text.toString()
                findNavController().navigate(R.id.action_editProfileFragment_to_navigation_profile)
            } else {
                CustomToast(root_cl).showMessage("Заполните поля")
            }

        }

    }
}