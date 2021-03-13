package com.example.crane.ui.registration

import android.content.Intent
import android.os.Bundle
import com.example.crane.R
import com.example.ui_components.base.BaseActivity
import com.example.ui_components.custom_view.CustomToast
import com.example.crane.ui.MainActivity
import com.example.data.common.SharedPreferencesSetting
import kotlinx.android.synthetic.main.activity_registration.*
import org.koin.android.ext.android.inject
import timber.log.Timber

class RegistrationActivity : BaseActivity() {

    private val sharedPreferencesSetting: SharedPreferencesSetting by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registration)
        initOnClickListener()
    }

    private fun initOnClickListener() {
        btn_apply.setOnClickListener {
            if (!firstNameEditText.text.isNullOrEmpty() && !secondNameEditText.text.isNullOrEmpty() &&
                !lastNameEditText.text.isNullOrEmpty() && !jobPositionEditText.text.isNullOrEmpty()
            ) {
                sharedPreferencesSetting.firstName = firstNameEditText.text.toString()
                sharedPreferencesSetting.secondName = secondNameEditText.text.toString()
                sharedPreferencesSetting.lastName = lastNameEditText.text.toString()
                sharedPreferencesSetting.jobPosition = jobPositionEditText.text.toString()
                openMainActivity()
            } else {
                CustomToast(root_cl).showMessage("Заполните поля")
            }

        }
    }

    private fun openMainActivity() {
        Timber.i("openMainActivity")
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}