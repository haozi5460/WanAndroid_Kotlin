package com.android.haozi.wanandroid.repository

import androidx.lifecycle.MutableLiveData
import com.android.haozi.wanandroid.bean.UserDataBean
import com.android.haozi.wanandroid.bean.ResponseBean
import com.android.haozi.wanandroid.common.RetrofitManager
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers

class LoginRepositroy {
    var loginData = MutableLiveData<ResponseBean<UserDataBean>>()
    var registerData = MutableLiveData<ResponseBean<UserDataBean>>()
    var logOutData = MutableLiveData<ResponseBean<UserDataBean>>()

    fun postLogin(username: String , password: String){
        var wanAndroidApi = RetrofitManager.get().getWanAndroidAPI()

        wanAndroidApi.login(username,password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<ResponseBean<UserDataBean>> {
                override fun onComplete() {
                    //To change body of created functions use File | Settings | File Templates.
                }

                override fun onSubscribe(d: Disposable) {
                    //To change body of created functions use File | Settings | File Templates.
                }

                override fun onNext(t: ResponseBean<UserDataBean>) {
                    loginData.value = t
                }

                override fun onError(e: Throwable) {
                    loginData.value = ResponseBean(UserDataBean(), 500, e.message)
                }

            })
    }

    fun registerAccount(username: String, password: String, repassword: String){
        var wanAndroidAPi = RetrofitManager.get().getWanAndroidAPI()
        wanAndroidAPi.registerAccount(username,password,repassword)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<ResponseBean<UserDataBean>> {
                override fun onComplete() {
                    //To change body of created functions use File | Settings | File Templates.
                }

                override fun onSubscribe(d: Disposable) {
                    //To change body of created functions use File | Settings | File Templates.
                }

                override fun onNext(t: ResponseBean<UserDataBean>) {
                    registerData.value = t
                }

                override fun onError(e: Throwable) {
                    registerData.value = ResponseBean(UserDataBean(), 500, e.message)
                }
            })
    }

    fun logOut(){
        var wanAndroidAPi = RetrofitManager.get().getWanAndroidAPI()
        wanAndroidAPi.logout()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(Consumer {
                logOutData.value = ResponseBean(UserDataBean(), 200, "Logout Successfully")
            }, Consumer {
                logOutData.value = ResponseBean(UserDataBean(), 500, "Logout Failed")
            })
    }
}