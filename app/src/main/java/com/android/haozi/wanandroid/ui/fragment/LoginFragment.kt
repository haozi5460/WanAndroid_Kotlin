package com.android.haozi.wanandroid.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Toast
import androidx.navigation.Navigation
import com.android.haozi.wanandroid.R
import com.android.haozi.wanandroid.ui.activity.MainActivity
import com.android.haozi.wanandroid.viewmode.LoginViewMode
import kotlinx.android.synthetic.main.login_fragment_layout.*

class LoginFragment : BaseFragment(), View.OnClickListener {
    private lateinit var loginViewMode: LoginViewMode

    override fun getLayoutId(): Int =
        R.layout.login_fragment_layout


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        login_layout_register_btn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registerFragment))
        initViewClick()
        initViewMode()
    }

    private fun initViewMode() {
        loginViewMode = ViewModelProviders.of(this)[LoginViewMode::class.java]
        loginViewMode.loginData?.observe(this, Observer {
            if(it?.errorCOde == 0){
                startActivity(Intent(activity, MainActivity::class.java))
                activity?.finish()
                Toast.makeText(context, "Login Success.", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(context,it?.errorMsg,Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun initViewClick() {
        login_btn.setOnClickListener(this)
        login_layout_register_btn.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.login_btn -> {
                loginWanAndroid()
            }

            R.id.login_layout_register_btn -> {
                //            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_registerFragment)
                Navigation.findNavController(login_layout_register_btn).navigate(R.id.action_loginFragment_to_registerFragment)
            }
        }
    }

    private fun loginWanAndroid() {
        val username = login_account_name_edit.text.toString()
        val password = login_account_secret_edit.text.toString()

        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            Toast.makeText(context,"请输入账号或者密码！", Toast.LENGTH_SHORT).show()
        }else{
            loginViewMode?.postLogin(username,password)
        }
    }
}