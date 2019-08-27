package com.android.haozi.wanandroid.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
//import butterknife.ButterKnife

abstract class BaseFragment : Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootView = inflater.inflate(getLayoutId(),container,false);
//        ButterKnife.bind(rootView);
        return rootView;
    }

    abstract fun getLayoutId():Int;
}