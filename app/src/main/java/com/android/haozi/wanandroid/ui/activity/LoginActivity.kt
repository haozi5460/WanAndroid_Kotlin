package com.android.haozi.wanandroid.ui.activity

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.Navigation
import com.android.haozi.wanandroid.R

import kotlinx.android.synthetic.main.activity_main.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp(): Boolean {
        Navigation.findNavController(this,R.id.fragmentNavigation).navigateUp();
        return super.onSupportNavigateUp()
    }
}
