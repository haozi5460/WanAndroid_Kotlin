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
import com.android.haozi.wanandroid.R
import kotlinx.android.synthetic.main.login_fragment_layout.*

class LoginFragment : BaseFragment() {
    @BindView(R.id.login_layout_register_btn) lateinit var registerBtn : View

    override fun getLayoutId(): Int =
        R.layout.login_fragment_layout

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view: View? = super.onCreateView(inflater, container, savedInstanceState)
//        var registerBTN : View = view?.findViewById(R.id.login_layout_register_btn) as View
        Log.e("shihao","view =="+view);
        if (view != null) {
            ButterKnife.bind(view)
        }
        Log.e("shihao","login_layout_register_btn =="+login_layout_register_btn);
        login_layout_register_btn?.setOnClickListener {
//            NavHostFragment.findNavController(this).navigate(R.id.action_loginFragment_to_registerFragment)
            Navigation.findNavController(login_layout_register_btn).navigate(R.id.action_loginFragment_to_registerFragment)
        }
//        registerBTN.setOnClickListener(Navigation.createNavigateOnClickListener(R.id.action_loginFragment_to_registerFragment));
        return view
    }
}