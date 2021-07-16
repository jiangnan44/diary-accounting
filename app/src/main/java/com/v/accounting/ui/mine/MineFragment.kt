package com.v.accounting.ui.mine

import android.util.Log
import android.view.View
import androidx.navigation.fragment.findNavController
import com.google.android.material.appbar.AppBarLayout
import com.v.accounting.R
import com.v.accounting.databinding.FragmentMineBinding
import com.v.accounting.ui.base.BaseVMFragment
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.math.abs

/**
 * Author:v
 * Time:2021/6/2
 */
class MineFragment : BaseVMFragment<FragmentMineBinding, MineViewModel>() {


    override fun getRootViewId(): Int {
        return R.layout.fragment_mine
    }

    override fun initView(root: View) {
        bindingApply {
            vm = getViewModel()
            adapter = MineAdapter()
            setupViewModel(vm!!)
            appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBar, verticalOffset ->
                val percentage = abs(verticalOffset).toFloat() / appBar.totalScrollRange.toFloat()
                updateSquarePosition(
                    viewSquare,
                    percentage
                )
            })
        }


    }

    private var startXPosition = 0f
    private var startYPosition = 0f
    private var endXPosition = 150
    private var endYPosition = 84f

    private fun updateSquarePosition(view: View, percentage: Float) {

        if (startXPosition == 0f) {
            startXPosition = view.x
            startYPosition = view.y
        }
        view.x = endXPosition - (startXPosition - endXPosition) * percentage
        view.y = endYPosition + (startYPosition - endYPosition) * percentage
        Log.d(
            "vvv",
            "startX=$startXPosition  startY=$startYPosition percentage=$percentage  v.x=${view.x} v.y=${view.y}"
        )

    }


    override fun setupViewModel(vm: MineViewModel) {
        vm.navigate2Add.observe(viewLifecycleOwner, {
            if (true == it) {
                findNavController().navigate(R.id.nav_action_mine_to_add_user)
                vm.onNavigate2AddDone()
            }
        })
    }

}