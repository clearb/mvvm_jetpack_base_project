package com.zyn.lib_base.binding.viewAdapter.recyclerview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.drawable.Drawable
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class RecycleViewDivider(context: Context, orientation: Int) : RecyclerView.ItemDecoration() {
    private var mPaint: Paint? = null
    private var mDivider: Drawable?
    private var mDividerHeight = 2 //分割线高度，默认为1px
    private val mOrientation //列表的方向：LinearLayoutManager.VERTICAL或LinearLayoutManager.HORIZONTAL
            : Int
    private var mOffsetLeft = 0 //分割线偏离左边界
    private var mOffsetRight = 0 //分割线偏离右边界

    /**
     * 默认分割线：高度为2px，颜色为灰色
     *
     * @param context
     * @param orientation 列表方向
     */
    init {
        require(!(orientation != LinearLayoutManager.VERTICAL && orientation != LinearLayoutManager.HORIZONTAL)) { "请输入正确的参数！" }
        mOrientation = orientation
        val a = context.obtainStyledAttributes(ATTRS)
        mDivider = a.getDrawable(0)
        a.recycle()
    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param orientation 列表方向
     * @param drawableId  分割线图片
     */
    constructor(context: Context, orientation: Int, drawableId: Int) : this(context, orientation) {
        mDivider = ContextCompat.getDrawable(context, drawableId)
        mDividerHeight = mDivider!!.intrinsicHeight
    }

    /**
     * 自定义分割线
     *
     * @param context
     * @param orientation   列表方向
     * @param dividerHeight 分割线高度
     * @param offsetLeft    分割线距离左边距
     * @param offsetRight   分割线距离右边距
     * @param dividerColor  分割线颜色
     */
    constructor(context: Context, orientation: Int, dividerHeight: Int, offsetLeft: Int, offsetRight: Int, dividerColor: Int) : this(
        context,
        orientation
    ) {
        mDividerHeight = dividerHeight
        mOffsetLeft = offsetLeft
        mOffsetRight = offsetRight
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint!!.color = dividerColor
        mPaint!!.style = Paint.Style.FILL
    }

    //获取分割线尺寸
    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            outRect[0, 0, 0] = mDividerHeight
        } else {
            outRect[0, 0, mDividerHeight] = 0
        }
    }

    //绘制分割线
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        if (mOrientation == LinearLayoutManager.VERTICAL) {
            drawVertical(c, parent)
        } else {
            drawHorizontal(c, parent)
        }
    }

    /**
     * 绘制纵向列表时的分隔线  这时分隔线是横着的
     * 每次 left相同，top根据child变化，right相同，bottom也变化
     * @param canvas
     * @param parent
     */
    private fun drawVertical(canvas: Canvas, parent: RecyclerView) {
        val left: Int = parent.getPaddingLeft()
        val right: Int = parent.getMeasuredWidth() - parent.getPaddingRight()
        val childSize: Int = parent.getChildCount()
        for (i in 0 until childSize) {
            val child: View = parent.getChildAt(i)
            val layoutParams: RecyclerView.LayoutParams = child.layoutParams as RecyclerView.LayoutParams
            val top: Int = child.bottom + layoutParams.bottomMargin
            val bottom = top + mDividerHeight
            if (mDivider != null) {
                mDivider!!.setBounds(left + mOffsetLeft, top, right - mOffsetRight, bottom)
                mDivider!!.draw(canvas)
            }
            if (mPaint != null) {
                canvas.drawRect((left + mOffsetLeft).toFloat(), top.toFloat(), (right - mOffsetRight).toFloat(), bottom.toFloat(), mPaint!!)
            }
        }
    }

    /**
     * 绘制横向列表时的分隔线  这时分隔线是竖着的
     * l、r 变化； t、b 不变
     * @param canvas
     * @param parent
     */
    private fun drawHorizontal(canvas: Canvas, parent: RecyclerView) {
        val top: Int = parent.getPaddingTop()
        val bottom: Int = parent.getMeasuredHeight() - parent.getPaddingBottom()
        val childSize: Int = parent.getChildCount()
        for (i in 0 until childSize) {
            val child: View = parent.getChildAt(i)
            val layoutParams: RecyclerView.LayoutParams = child.layoutParams as RecyclerView.LayoutParams
            val left: Int = child.right + layoutParams.rightMargin
            val right = left + mDividerHeight
            if (mDivider != null) {
                mDivider!!.setBounds(left, top, right, bottom)
                mDivider!!.draw(canvas)
            }
            if (mPaint != null) {
                canvas.drawRect(left.toFloat(), top.toFloat(), right.toFloat(), bottom.toFloat(), mPaint!!)
            }
        }
    }

    companion object {
        private val ATTRS = intArrayOf(android.R.attr.listDivider)
    }
}