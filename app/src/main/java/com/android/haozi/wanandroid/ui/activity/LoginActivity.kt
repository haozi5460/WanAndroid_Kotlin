package com.android.haozi.wanandroid.ui.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.navigation.Navigation
import com.android.haozi.wanandroid.R
import com.android.haozi.wanandroid.common.Constant
import com.android.haozi.wanandroid.common.WanCookieManager
import com.android.haozi.wanandroid.common.baseUrl
import com.android.haozi.wanandroid.utils.PreferenceUtil

class LoginActivity : BaseActivity() {
    override fun getLayoutId() = R.layout.login_activity_layout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        var hasUserLogin by PreferenceUtil(Constant.HasUserLogin,false)
//        if(hasUserLogin){
//            startActivity(Intent(this,MainActivity::class.java))
//            this.finish()
//        }
//        lifecycle.add

    }

    override fun onResume() {
        super.onResume()
        initLoginView()
    }

    private fun initLoginView() {
        var cookiesList = WanCookieManager.getAllCookieByHost(baseUrl)
        if(cookiesList != null && cookiesList.size > 0){
            cookiesList?.forEach {
                if(it.name().equals("loginUserName")){
                    if(it.persistent()){
                        startActivity(Intent(this,MainActivity::class.java))
                        this.finish()
                    }else{
                        WanCookieManager.clearAllCookies()
                    }
                    return@forEach
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        Navigation.findNavController(this,R.id.fragmentNavigation).navigateUp();
        return super.onSupportNavigateUp()
    }
}
