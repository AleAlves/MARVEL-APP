package br.com.nishizaki.mercados.app.core.ui

import android.content.Context
import android.util.Log
import android.view.View
import androidx.viewpager2.widget.ViewPager2


class ViewPagerStack(var context: Context) : ViewPager2.PageTransformer {


    override fun transformPage(page: View, position: Float) {

        page.apply {
            if (position >= 0) {
                page.scaleX = 0.9f - 0.02f * position
                page.scaleY = 0.9f
                page.translationX = -position
                page.translationZ = -position
                page.translationY = page.height.toFloat() * (-position / 0.82f)
                page.rotation = 0.0f
            } else {
                page.scaleX = 0.9f + 0.02f * position
                page.scaleY = 0.9f
                page.translationZ = position
                page.translationX = position
                page.translationY = -page.height.toFloat() * (position / 0.86f)
                page.rotation = 0.5f  * position
            }
            Log.d("TRANSFORMPAGE", position.toString())
            when {
                position < -1 ->
                    page.alpha = 0.8f
                position <= 1 -> {
                    page.alpha = 1.0f
                }
                else -> page.alpha = 0.7f
            }
        }


    }
}