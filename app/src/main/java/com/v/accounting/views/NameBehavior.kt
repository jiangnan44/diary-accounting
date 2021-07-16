package com.v.accounting.views

import android.content.Context
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.v.accounting.R

/**
 * Author:v
 * Time:2021/7/15
 */
class NameBehavior : CoordinatorLayout.Behavior<TextView> {
    private val TAG = "NameBehavior"

    private val mContext: Context

    private var startToolbarPosition = 0f


    private var deltaTextSize = 0f
    private var startHeight = 0
    private var startXPosition = 0
    private var startYPosition = 0

    private var finalTextSize = 0f
    private var finalXPosition = 0
    private var finalYPosition = 0

    constructor(context: Context, attrs: AttributeSet?) : super(
        context,
        attrs
    ) {
        mContext = context
        bindDimensions()
    }

    private fun bindDimensions() {
        mContext.resources?.let {
            finalXPosition =
                it.getDimensionPixelOffset(R.dimen.activity_horizontal_margin) * 2 +
                        it.getDimensionPixelOffset(R.dimen.avatar_small_width)
            finalTextSize = it.getDimension(R.dimen.name_end_size)
            deltaTextSize = it.getDimension(R.dimen.name_start_size) - finalTextSize
        }
    }


    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: TextView,
        dependency: View
    ): Boolean {
        return dependency is Toolbar
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: TextView,
        dependency: View
    ): Boolean {
        maybeInitProperties(child, dependency, parent)
        val maxScrollDistance = (startToolbarPosition - getStatusBarHeight()).toInt()
        val expandedPercentageFactor = dependency.y / maxScrollDistance
        val distanceYToSubtract =
            (startYPosition - finalYPosition) * (1f - expandedPercentageFactor) + child.height / 2

        val distanceXToAdd =
            (startXPosition - finalXPosition - child.width / 2) * (expandedPercentageFactor)

        val changeSize = deltaTextSize * (expandedPercentageFactor)

        child.y = startYPosition - distanceYToSubtract
        child.x = finalXPosition + distanceXToAdd

        child.setTextSize(TypedValue.COMPLEX_UNIT_PX, finalTextSize + changeSize)

        return true
    }

    private fun maybeInitProperties(child: TextView, dependency: View, parent: CoordinatorLayout) {
        if (startYPosition == 0) {
            startYPosition = (dependency.y + dependency.height + child.height / 2).toInt()
            startXPosition = (child.x + child.width / 2).toInt()
        }

        if (startHeight == 0) {
            startHeight = child.height
        }

        if (finalYPosition == 0) {
            finalYPosition = dependency.height / 2
        }

        if (startToolbarPosition == 0f) {
            startToolbarPosition = dependency.y + dependency.height / 2
        }
    }


    private fun getStatusBarHeight(): Int {
        val resId = mContext.resources.getIdentifier("status_bar_height", "dimen", "android")
        return if (resId > 0) {
            mContext.resources.getDimensionPixelSize(resId)
        } else {
            0
        }
    }
}