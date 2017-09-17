package com.luseen.ribble.presentation.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View
import com.luseen.ribble.utils.AnimationUtils

/**
 * Created by Chatikyan on 17.09.2017.
 */
interface AnimatedView {

    fun <V : View> animate(view: V, duration: Long = 170, startDelay: Long, acton: V.() -> Unit) {
        val scaleFactor = 0.75f
        with(view) {
            clearAnimation()
            animate()
                    .alpha(0f)
                    .scaleX(scaleFactor)
                    .setDuration(duration)
                    .withLayer()
                    .setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR)
                    .setStartDelay(startDelay)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator) {
                            super.onAnimationEnd(animation)
                            acton(view)
                            scaleX = scaleFactor
                            animate()
                                    .scaleX(1f)
                                    .alpha(1f)
                                    .setListener(null)
                                    .withLayer()
                                    .setDuration(duration)
                                    .start()
                        }
                    })
                    .start()
        }
    }
}