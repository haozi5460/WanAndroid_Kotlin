package com.android.haozi.wanandroid.viewmode

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.android.haozi.wanandroid.bean.DataBean
import com.android.haozi.wanandroid.bean.ResponseBean
import com.android.haozi.wanandroid.common.RetrofitManager
import com.android.haozi.wanandroid.common.WanAndroidAPi
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer
import io.reactivex.schedulers.Schedulers
import kotlin.math.log

class LoginViewMode : ViewModel(){
    var loginData = MutableLiveData<ResponseBean<DataBean>>()
    var registerData = MutableLiveData<ResponseBean<DataBean>>()
    var logOutData = MutableLiveData<ResponseBean<DataBean>>()

    fun postLogin(username: String , password: String){
        var wanAndroidApi = RetrofitManager.get().getWanAndroidAPI()

        wanAndroidApi.login(username,password)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object: Observer<ResponseBean<DataBean>>{
                override fun onComplete() {
                    //To change body of created functions use File | Settings | File Templates.
                }

                override fun onSubscribe(d: Disposable) {
                    //To change body of created functions use File | Settings | File Templates.
                }

                override fun onNext(t: ResponseBean<DataBean>) {
                    loginData.value = t
                }

                override fun onError(e: Throwable) {
                    loginData.value = ResponseBean(DataBean(), 500, e.message)
                }

            })
//            .subscribe(Consumer {
//                loginData.value = it
//            }
//            , Consumer {
//                    loginData.value = ResponseBean(DataBean(), 500, it.message)
//                })

    }

    fun registerAccount(username: String, password: String, repassword: String){
        var wanAndroidAPi = RetrofitManager.get().getWanAndroidAPI()
        wanAndroidAPi.registerAccount(username,password,repassword)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<ResponseBean<DataBean>>{
                override fun onComplete() {
                    //To change body of created functions use File | Settings | File Templates.
                }

                override fun onSubscribe(d: Disposable) {
                    //To change body of created functions use File | Settings | File Templates.
                }

                override fun onNext(t: ResponseBean<DataBean>) {
                    registerData.value = t
                }

                override fun onError(e: Throwable) {
                    registerData.value = ResponseBean(DataBean(), 500, e.message)
                }
            })
    }

    fun logOut(){
        var wanAndroidAPi = RetrofitManager.get().getWanAndroidAPI()
        wanAndroidAPi.logout()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe(Consumer {
                logOutData.value = ResponseBean(DataBean(), 200, "Logout Successfully")
            }, Consumer {
                logOutData.value = ResponseBean(DataBean(), 500, "Logout Failed")
            })
    }
}