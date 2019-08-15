package com.android.haozi.wanandroid.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import butterknife.BindView
import butterknife.ButterKnife
import butterknife.OnClick
import com.android.haozi.wanandroid.R
import kotlinx.android.synthetic.main.login_fragment_layout.*

class LoginFragment : BaseFragment() {

    override fun getLayoutId(): Int =
        R.layout.login_fragment_layout


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_layout_register_btn?.setOnClickListener {
            //            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_registerFragment)
            Navigation.findNavController(login_layout_register_btn).navigate(R.id.action_loginFragment_to_registerFragment)
        }
//        login_layout_register_btn.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registerFragment))
    }
}