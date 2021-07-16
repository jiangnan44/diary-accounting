package com.v.accounting.views

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageView
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.v.accounting.R

/**
 * Author:v
 * Time:2021/7/15
 * @see
 * http://www.devexchanges.info/2016/03/android-tip-custom-coordinatorlayout.html
 */
class AvatarBehavior : CoordinatorLayout.Behavior<ImageView> {
    private val TAG = "AvatarBehavior"

    private val mContext: Context
    private var avatarMaxSize = 100f

    private var finalLeftAvatarPadding = 0f
    private var startToolbarPosition = 0f


    private var startHeight = 0
    private var startXPosition = 0
    private var startYPosition = 0

    private var finalHeight = 0
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
            avatarMaxSize = it.getDimension(R.dimen.avatar_width)
            finalLeftAvatarPadding = it.getDimension(R.dimen.activity_horizontal_margin)
        }
    }


    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: ImageView,
        dependency: View
    ): Boolean {
        return dependency is Toolbar
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: ImageView,
        dependency: View
    ): Boolean {
        maybeInitProperties(child, dependency)
        val maxScrollDistance = (startToolbarPosition - getStatusBarHeight()).toInt()
        val expandedPercentageFactor = dependency.y / maxScrollDistance
        val distanceYToSubtract =
            (startYPosition - finalYPosition) * (1f - expandedPercentageFactor) + child.height / 2
        val distanceXToSubtract =
            (startXPosition - finalXPosition) * (1f - expandedPercentageFactor) + child.width / 2
        val heightToSubtract = (startHeight - finalHeight) * (1f - expandedPercentageFactor)

        child.y = startYPosition - distanceYToSubtract
        child.x = startXPosition - distanceXToSubtract

        val lp = (child.layoutParams as CoordinatorLayout.LayoutParams).apply {
            width = (startHeight - heightToSubtract).toInt()
            height = width
        }
        child.layoutParams = lp
        return true
    }

    private fun maybeInitProperties(child: ImageView, dependency: View) {
        if (startYPosition == 0) {
            startYPosition = dependency.y.toInt()
        }

        if (finalYPosition == 0) {
            finalYPosition = dependency.height / 2
        }

        if (startHeight == 0) {
            startHeight = child.height
        }

        if (finalHeight == 0) {
            finalHeight = mContext.resources.getDimensionPixelOffset(R.dimen.avatar_small_width)
        }

        if (startXPosition == 0) {
            startXPosition = (child.x + child.width / 2).toInt()
        }

        if (finalXPosition == 0) {
            finalXPosition =
                mContext.resources.getDimensionPixelOffset(com.google.android.material.R.dimen.abc_action_bar_content_inset_material) + finalHeight / 2
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