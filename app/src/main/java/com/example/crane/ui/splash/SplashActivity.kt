package com.example.crane.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.example.crane.R
import com.example.ui_components.base.BaseActivity
import com.example.crane.ui.MainActivity
import com.example.crane.ui.registration.RegistrationActivity
import com.example.data.common.SharedPreferencesSetting
import org.koin.android.ext.android.inject
import timber.log.Timber

class SplashActivity : BaseActivity() {

    companion object {
        private const val SCREEN_DURATION_VISIBILITY = 2000L
    }

    private val sharedPreferencesSetting: SharedPreferencesSetting by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        navigateToAnotherScreen()
    }

    private fun navigateToAnotherScreen() {
        Timber.i("navigateToAnotherScreen")
        Handler().postDelayed(::openAnotherScreen, SCREEN_DURATION_VISIBILITY)
    }

    private fun openAnotherScreen() {
        Timber.i("openAnotherScreen")
        if (sharedPreferencesSetting.firstName.isNullOrEmpty() || sharedPreferencesSetting.secondName.isNullOrEmpty()
            || sharedPreferencesSetting.lastName.isNullOrEmpty() || sharedPreferencesSetting.jobPosition.isNullOrEmpty()
        ) {
            openRegistrationActivity()
        } else {
            openMainActivity()
        }

    }

    private fun openMainActivity() {
        Timber.i("openMainActivity")
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun openRegistrationActivity() {
        Timber.i("openRegistrationActivity")
        startActivity(Intent(this, RegistrationActivity::class.java))
        finish()
    }

}