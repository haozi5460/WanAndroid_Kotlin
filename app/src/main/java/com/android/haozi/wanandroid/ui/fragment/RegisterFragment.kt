package com.android.haozi.wanandroid.ui.fragment

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import com.android.haozi.wanandroid.R
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import com.android.haozi.wanandroid.ui.activity.MainActivity
import android.view.View
import android.widget.Toast
import com.android.haozi.wanandroid.bean.UserDataBean
import com.android.haozi.wanandroid.bean.ResponseBean
import com.android.haozi.wanandroid.common.Constant
import com.android.haozi.wanandroid.utils.PreferenceUtil
import com.android.haozi.wanandroid.viewmode.LoginViewMode
import kotlinx.android.synthetic.main.register_fragment_layout.*

class RegisterFragment : BaseFragment() {
    var registerViewModel: LoginViewMode? = null

    override fun getLayoutId(): Int = R.layout.register_fragment_layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViewClick()
        initViewModel()
        recordRegisterAccount()
    }

    private fun initViewModel() {
        registerViewModel = ViewModelProviders.of(this)[LoginViewMode::class.java]
    }

    private fun initViewClick() {
        register_btn.setOnClickListener {
            registerWanAndroid()
        }
    }

    private fun registerWanAndroid() {
        var userName = register_account_name_edit.text.toString()
        var password = register_account_secret_edit.text.toString()
        var repassword = confirm_account_secret_edit.text.toString()

        if(TextUtils.isEmpty(userName)){
            Toast.makeText(context, "请输入用户名！", Toast.LENGTH_SHORT).show()
            register_account_name_edit.requestFocus()
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(context, "请设置密码！", Toast.LENGTH_SHORT).show()
            register_account_secret_edit.requestFocus()
        }else if(TextUtils.isEmpty(repassword)){
            Toast.makeText(context, "请验证您设置的密码！", Toast.LENGTH_SHORT).show()
            confirm_account_secret_edit.requestFocus()
        }else if(!password.equals(repassword)){
            Toast.makeText(context, "两次设置的密码不一致！", Toast.LENGTH_SHORT).show()
        }else{
            registerViewModel?.registerAccount(userName,password,repassword)
        }
    }

    private fun recordRegisterAccount() {
        registerViewModel?.registerData?.observe(this, Observer {
            Log.e("shihao","response ="+it?.toString());
            if(it?.errorCode == 0 && TextUtils.isEmpty(it.errorMsg)){
                recordAccount(it)
            }else{
                Toast.makeText(context, it?.errorMsg, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun recordAccount(responseBean: ResponseBean<UserDataBean>) {
        var userName by PreferenceUtil(Constant.UserName, "")
        var userId by PreferenceUtil(Constant.UserId, 0)
        var hasLogin by PreferenceUtil(Constant.HasUserLogin, false)

        userId = responseBean.data.id
        userName = responseBean.data?.username!!
        hasLogin = true

        Toast.makeText(context, "WanAndroid账号注册成功！", Toast.LENGTH_SHORT).show()
        startActivity(Intent(activity,MainActivity::class.java))
        activity?.finish()
    }
}