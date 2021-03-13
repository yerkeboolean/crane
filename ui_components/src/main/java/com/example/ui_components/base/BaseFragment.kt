package com.example.ui_components.base

import android.app.Activity
import android.view.inputmethod.InputMethodManager
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.ui_components.modal.ModalBottomSheetDialog
import timber.log.Timber

abstract class BaseFragment : Fragment() {
    lateinit var modalBottomSheetDialogService: ModalBottomSheetDialog

    fun hideKeyBoard() {
        val imm =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    fun bottomIsShowing(): Boolean {
        return try {
            modalBottomSheetDialogService.isShowing
        } catch (e: Exception) {
            false
        }
    }

    fun initBackDispatcher() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().popBackStack()
                    Timber.i("handleOnBackPressed")
                }
            }
        )
    }

}