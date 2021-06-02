package com.v.accounting.ui

import androidx.core.content.ContextCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.v.accounting.R
import com.v.accounting.ui.base.BaseActivity

class MainActivity : BaseActivity() {

    override fun getContentLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        val navView: BottomNavigationView = findViewById(R.id.bnv_main)
        val navController = findNavController(R.id.nav_host_main)
        navView.setupWithNavController(navController)
//        testBadgeView(navView)
    }


    private fun testBadgeView(navView: BottomNavigationView) {
        navView.getOrCreateBadge(R.id.nav_mine).run {
            number = 100
            maxCharacterCount = 3
            verticalOffset = 20
            backgroundColor = ContextCompat.getColor(this@MainActivity, R.color.purple_200)
        }

    }
}