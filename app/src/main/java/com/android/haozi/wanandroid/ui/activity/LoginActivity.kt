package com.android.haozi.wanandroid.ui.activity

import androidx.navigation.Navigation
import com.android.haozi.wanandroid.R

class LoginActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.login_activity_layout

    override fun onSupportNavigateUp(): Boolean {
        Navigation.findNavController(this,R.id.fragmentNavigation).navigateUp();
        return super.onSupportNavigateUp()
    }
}
