package com.android.haozi.wanandroid.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.navigation.Navigation
import com.android.haozi.wanandroid.R
import com.android.haozi.wanandroid.common.Constant
import com.android.haozi.wanandroid.utils.PreferenceUtil

class LoginActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.login_activity_layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var hasUserLogin by PreferenceUtil(Constant.HasUserLogin,false)
        if(hasUserLogin){
            startActivity(Intent(this,MainActivity::class.java))
            this.finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        Navigation.findNavController(this,R.id.fragmentNavigation).navigateUp();
        return super.onSupportNavigateUp()
    }
}
