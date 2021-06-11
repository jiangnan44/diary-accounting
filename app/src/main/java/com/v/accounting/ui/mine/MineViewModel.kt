package com.v.accounting.ui.mine

import android.view.View
import androidx.navigation.findNavController
import com.v.accounting.R
import com.v.accounting.ui.base.BaseViewModel

/**
 * Author:v
 * Time:2021/6/2
 */
class MineViewModel() : BaseViewModel() {


    fun toAddUser(view: View) {
        view.findNavController()
            .navigate(R.id.nav_action_mine_to_add_user)
    }
}