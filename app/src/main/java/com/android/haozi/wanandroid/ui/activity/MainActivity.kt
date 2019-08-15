package com.android.haozi.wanandroid.ui.activity

import android.content.res.ColorStateList
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.content.ContextCompat
import android.support.v4.view.GravityCompat
import android.view.MenuItem
import com.android.haozi.wanandroid.R
import kotlinx.android.synthetic.main.common_toolbar_layout.*
import kotlinx.android.synthetic.main.main_activity_draw_layout.*
import kotlinx.android.synthetic.main.main_activity_layout.*

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener{

    override fun getLayoutId(): Int {
        return R.layout.main_activity_layout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigation()
        initViewClick()
    }

    private fun initViewClick() {
        main_bottomnavigation.setOnNavigationItemSelectedListener(this)
    }

    private fun initNavigation() {
        setSupportActionBar(main_activity_toolbar_true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_toolbar_menu)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null;
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId){
            android.R.id.home ->
                if(main_drawerlayout.isDrawerVisible(GravityCompat.START)){
                    main_drawerlayout.closeDrawer(GravityCompat.START)
                }else{
                    main_drawerlayout.openDrawer(GravityCompat.START)
                }
        }
        return true
    }

    override fun onNavigationItemSelected(menuItem: MenuItem): Boolean {
        when(menuItem.itemId){
            R.id.home_page -> {
                toolbar_title.text = getString(R.string.home_page)
            }

            R.id.home_project -> {
                toolbar_title.text = getString(R.string.home_project)
            }

            R.id.home_navigation -> {
                toolbar_title.text = getString(R.string.home_navigation)
            }

            R.id.home_public_number -> {
                toolbar_title.text = getString(R.string.home_public_number)
            }
        }
        return true;
    }
}