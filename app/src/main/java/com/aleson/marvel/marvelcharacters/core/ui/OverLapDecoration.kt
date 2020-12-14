package br.com.nishizaki.mercados.app.core.ui

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class OverLapDecoration : RecyclerView.ItemDecoration() {

    private val vertOverlap = -100

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        super.getItemOffsets(outRect, view, parent, state)

        val itemPosition = parent.getChildAdapterPosition(view)
        if (itemPosition == 0) {
            outRect.set(0, 0, 0, 0)
        } else {
            outRect.set(vertOverlap, 0, 0, 0)
        }
    }
}