package com.android.haozi.wanandroid.ui.fragment

import com.android.haozi.wanandroid.R
import android.content.Intent
import android.os.Bundle
import com.android.haozi.wanandroid.ui.activity.MainActivity
import android.view.View
import kotlinx.android.synthetic.main.register_fragment_layout.*

class RegisterFragment : BaseFragment() {
    override fun getLayoutId(): Int = R.layout.register_fragment_layout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        register_btn?.setOnClickListener {
            startActivity(Intent(activity,MainActivity::class.java))
        }
    }
}