package com.wk.projects.common

import android.os.Bundle
import androidx.fragment.app.FragmentManager
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.common_dialog_fragment_base_simple.*

/**
 * <pre>
 *      author : wk
 *      e-mail : 1226426603@qq.com
 *      time   : 2018/09/17
 *      desc   : 最简单的DialogFragment:上下结构，标题，ViewStub，两个按钮
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 * </pre>*/


abstract class BaseSimpleDialog : BaseDialogFragment(), View.OnClickListener {
    interface SimpleOnlyEtDialogListener {
        /**
         * @return 是否拦截 true，拦截，不使用父方法 即不消失
         */
        fun ok(bundle: Bundle? = null): Boolean

        /**
         * @return 是否拦截 true，拦截，不使用父方法 即不消失
         */
        fun cancel(bundle: Bundle? = null): Boolean
    }

    var simpleOnlyEtDialogListener: SimpleOnlyEtDialogListener? = null

    final override fun initResLayId() = R.layout.common_dialog_fragment_base_simple

    override fun bindView(savedInstanceState: Bundle?) {
        initView()
        initListener()
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnComSimpleDialogOk -> {
                if (simpleOnlyEtDialogListener?.ok(getOkBundle()) == true) {
                    return
                }
                disMiss()
            }
            R.id.btnComSimpleDialogCancel -> {
                if (simpleOnlyEtDialogListener?.cancel(getCancelBundle()) == true) {
                    return
                }
                disMiss()
            }
        }
    }



    open fun getOkBundle(): Bundle? = null

    open fun getCancelBundle(): Bundle? = null

    protected fun disMiss() {
        if (dialog != null && dialog.isShowing)
            dismiss()
    }

    fun show(manager: FragmentManager?) {
        super.show(manager, this::class.java.simpleName)
    }

    fun show(baseProjectsActivity: BaseProjectsActivity) {
        show(baseProjectsActivity.supportFragmentManager)
    }

    /**加载ViewSub中的View*/
    abstract fun initVSView(vsView: View)

    /**设置ViewSub被替换的布局*/
    abstract fun initViewSubLayout(): Int


    private fun initListener() {
        btnComSimpleDialogOk.setOnClickListener(this)
        btnComSimpleDialogCancel.setOnClickListener(this)
    }

    private fun initView() {
        vsComSimpleDialogContent.layoutResource = (initViewSubLayout())
        val vsView = vsComSimpleDialogContent.inflate()
        val p = ConstraintLayout.LayoutParams(
                mActivity.resources.getDimensionPixelOffset(R.dimen.d200dp),
                ConstraintLayout.LayoutParams.WRAP_CONTENT)
        p.topToBottom = R.id.tvComSimpleDialogTheme
        p.bottomToTop = R.id.btnComSimpleDialogOk
        p.leftToLeft = (vsView.parent as View).id
        p.rightToRight = (vsView.parent as View).id
        p.width = (vsView.parent as View).measuredWidth
        vsView.layoutParams = p
        (btnComSimpleDialogOk.layoutParams as ConstraintLayout.LayoutParams).topToBottom = vsView.id
        (btnComSimpleDialogCancel.layoutParams as ConstraintLayout.LayoutParams).topToBottom = vsView.id
        btnComSimpleDialogOk.visibility = getShowStatue(showOkButton())
        btnComSimpleDialogCancel.visibility = getShowStatue(showCancelButton())
        initVSView(vsView)
    }

    private fun getShowStatue(isShow: Boolean) = if (isShow) {
        View.VISIBLE
    } else {
        View.INVISIBLE
    }

    protected open fun showOkButton() = true
    protected open fun showCancelButton() = true

}
