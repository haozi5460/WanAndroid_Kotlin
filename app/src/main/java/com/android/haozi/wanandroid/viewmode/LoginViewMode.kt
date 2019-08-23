package com.android.haozi.wanandroid.viewmode

import android.arch.lifecycle.Transformations
import android.arch.lifecycle.ViewModel
import com.android.haozi.wanandroid.repository.LoginRepositroy

class LoginViewMode(val loginRepositroy: LoginRepositroy) : ViewModel(){
    var loginData = loginRepositroy.loginData
    var registerData = Transformations.map(loginRepositroy.registerData){it}
    var logOutData = loginRepositroy.logOutData

    fun postLogin(username: String , password: String){
        loginRepositroy.postLogin(username, password)
    }

    fun registerAccount(username: String, password: String, repassword: String){
        loginRepositroy.registerAccount(username,password, repassword)
    }

    fun logOut(){
        loginRepositroy.logOut()
    }
}