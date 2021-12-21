package com.wk.projects.common.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.wk.projects.common.R

/**
 * author : wk <br></br>
 * e-mail : 1226426603@qq.com<br></br>
 * time   : 2020/6/7<br></br>
 * desc   : 统一的actionBar  <br></br>
 * GitHub : https://github.com/wk1995 <br></br>
 * CSDN   : http://blog.csdn.net/qq_33882671 <br></br>
 */
class WkCommonActionBar : ConstraintLayout {
    private lateinit var ivActionBarLeft:ImageView
    private lateinit var tvActionBarMiddle:TextView
    private lateinit var tvActionBarRight:TextView
    private val mLayoutInflater by lazy { LayoutInflater.from(context) }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    override fun onFinishInflate() {
        super.onFinishInflate()
        initLayout(R.layout.common_action_bar_default)
        initView()
    }

    private fun initLayout(layoutResId:Int){
        mLayoutInflater.inflate(layoutResId,this,true)
    }

    private fun initView(){
        ivActionBarLeft=findViewById(R.id.ivActionBarLeft)
        tvActionBarMiddle=findViewById(R.id.tvActionBarMiddle)
        tvActionBarRight=findViewById(R.id.tvActionBarRight)
    }

    fun setLeftViewClickListener(onClickListener: OnClickListener){
        ivActionBarLeft.setOnClickListener(onClickListener)
    }

    fun setMiddleViewClickListener(onClickListener: OnClickListener){
        tvActionBarMiddle.setOnClickListener(onClickListener)
    }

    fun setMiddleViewText(resId:Int){
        tvActionBarMiddle.setText(resId)
    }

    fun setMiddleViewGravity(gravity:Int){
        tvActionBarMiddle.gravity=gravity
    }

    fun setMiddleViewTextColor(colorId: Int){
        tvActionBarMiddle.setTextColor(colorId)
    }

    fun setRightViewText(resId: Int){
        tvActionBarRight.setText(resId)
        tvActionBarRight.visibility=View.VISIBLE
    }

    fun setRightViewClickListener(onClickListener: OnClickListener){
        tvActionBarRight.setOnClickListener(onClickListener)
    }

    fun getRightViewId()=tvActionBarRight.id






}
