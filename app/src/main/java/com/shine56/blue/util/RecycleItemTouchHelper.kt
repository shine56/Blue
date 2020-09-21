package com.shine56.blue.util

import android.graphics.Canvas
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

class RecycleItemTouchHelper : ItemTouchHelper.Callback(){
    override fun getMovementFlags(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        return makeMovementFlags(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        )
    }

    override fun isLongPressDragEnabled(): Boolean {
        return false
    }

    override fun isItemViewSwipeEnabled(): Boolean {
        return super.isItemViewSwipeEnabled()
    }

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        return false
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        val max = getMaxWidth(viewHolder)
        val scrollX = viewHolder.itemView.scrollX
        //LogUtil.LogD("dx = "+dX+"      sX = " + scrollX);

        //LogUtil.LogD("dx = "+dX+"      sX = " + scrollX);
        if (dX >= 0) {
            viewHolder.itemView.scrollTo(max - dX.toInt(), 0)
            if (scrollX <= max * 2 / 3) {
                viewHolder.itemView.scrollTo(0, 0)
            }
        } else {
            if (scrollX < max * 1 / 2) {
                viewHolder.itemView.scrollTo((-dX).toInt(), 0)
            } else {
                viewHolder.itemView.scrollTo(max, 0)
            }
        }
    }

    private fun getMaxWidth(viewHolder: RecyclerView.ViewHolder): Int {
        val viewGroup = viewHolder.itemView as ViewGroup
        return viewGroup.getChildAt(1).width
    }

}