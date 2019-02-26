package com.appetizer.exam

import android.content.SharedPreferences
import android.os.Bundle
import androidx.navigation.findNavController
import com.appetizer.exam.core.AppConstants.Companion.LAST_SCREEN
import com.appetizer.exam.core.AppConstants.Companion.SCREEN_ITEM_DETAIL
import com.appetizer.exam.core.BaseActivity
import kotlinx.android.synthetic.main.activity_root_navigation.*
import javax.inject.Inject

class RootNavigationActivity : BaseActivity() {
    @Inject
    lateinit var preferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_root_navigation)
        setSupportActionBar(toolbar)
        initiateInjection()
        navigateToLastScreen()
    }

    private fun initiateInjection() {
        DaggerRootNavigationComponent
                .builder()
                .coreComponent(coreComponent)
                .build()
                .apply {
                    inject(this@RootNavigationActivity)
                }
    }

    private fun navigateToLastScreen() {
        val lastScreen = preferences.getString(LAST_SCREEN, "") as String

        if (lastScreen == SCREEN_ITEM_DETAIL) {
            findNavController(R.id.rootNavHost).navigate(R.id.ITunesDetailFragment)
        }
    }
}
