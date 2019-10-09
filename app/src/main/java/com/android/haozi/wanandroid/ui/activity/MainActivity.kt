package com.android.haozi.wanandroid.ui.activity

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.core.view.GravityCompat
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.*
import com.android.haozi.wanandroid.R
import com.android.haozi.wanandroid.common.Constant
import com.android.haozi.wanandroid.common.WanCookieManager
import com.android.haozi.wanandroid.repository.LoginRepositroy
import com.android.haozi.wanandroid.ui.fragment.HomeFragment
import com.android.haozi.wanandroid.utils.PreferenceUtil
import com.android.haozi.wanandroid.viewmode.LoginViewMode
import kotlinx.android.synthetic.main.common_toolbar_layout.*
import kotlinx.android.synthetic.main.main_activity_draw_layout.*
import kotlinx.android.synthetic.main.main_activity_layout.*
import kotlinx.android.synthetic.main.main_activity_navigation_layout.*

class MainActivity : BaseActivity(), BottomNavigationView.OnNavigationItemSelectedListener
                    , View.OnClickListener{
    var logoutViewMode: LoginViewMode? = null
    lateinit var homeFragment: HomeFragment

    override fun getLayoutId(): Int {
        return R.layout.main_activity_layout
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initNavigation()
        initViewClick()
        initViewData()
        initViewModle()
        initFragment()
    }

    private fun initFragment() {
        homeFragment = HomeFragment.newInstance()
        with(supportFragmentManager){
            beginTransaction().add(R.id.main_activity_content_framelayout,homeFragment)
                .commit()
        }

    }

    private fun initViewModle() {
        logoutViewMode = ViewModelProviders.of(this, object: ViewModelProvider.Factory{
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return LoginViewMode(LoginRepositroy()) as T
            }
        })[LoginViewMode::class.java]

        logoutViewMode?.logOutData?.observe(this, Observer {
            Toast.makeText(this, it?.errorMsg,Toast.LENGTH_SHORT).show()
            if(it?.errorCode == 200){
                startActivity(Intent(this, LoginActivity::class.java))
//                var hasUserLogin by PreferenceUtil(Constant.HasUserLogin,false)
//                hasUserLogin = false
                WanCookieManager.clearAllCookies()
                this.finish()
            }
        })
    }

    private fun initViewData() {
        var userName by PreferenceUtil(Constant.UserName, "Android")
        account_name.text = userName
    }

    private fun initViewClick() {
        main_bottomnavigation.setOnNavigationItemSelectedListener(this)
        main_menu_collection.setOnClickListener(this)
        main_menu_logout.setOnClickListener(this)
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

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.main_menu_collection -> {
                var intent = Intent(this,CollectionActivity::class.java)
                var bundle = Bundle();
                startActivity(intent)
            }

            R.id.main_menu_todo -> {
            }

            R.id.main_menu_logout -> {
                logOutAccount()
            }
        }
    }

    private fun logOutAccount() {
        logoutViewMode?.logOut()
    }
}