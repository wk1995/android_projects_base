package com.wk.projects.common

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.wk.projects.common.communication.IFragmentToActivity
import com.wk.projects.common.log.WkLog
import com.wk.projects.common.ui.WkToast

/**
 * <pre>
 *      author : wk
 *      e-mail : 122642603@qq.com
 *      time   : 2018/11/23
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 *      desc   :
 * </pre>
 */
abstract class BaseProjectsActivity : AppCompatActivity(), IFragmentToActivity,
        View.OnClickListener,View.OnLongClickListener {
    companion object {
        private const val TAG = "BaseProjectsActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WkLog.d(TAG," ${this::class.java.simpleName}  onCreate")
//        beforeSetContentView()
        val resLayout=initResLayId()
        if(resLayout is Int ){
            setContentView(resLayout)
        }
        if(resLayout is View){
            setContentView(resLayout)
        }
        bindView(savedInstanceState, this)
    }

    override fun onStart() {
        super.onStart()
        WkLog.d("${this::class.java.simpleName} onStart",TAG)
    }

    override fun onResume() {
        super.onResume()
        WkLog.d("${this::class.java.simpleName} onResume",TAG)
    }

    override fun onRestart() {
        super.onRestart()
        WkLog.d("${this::class.java.simpleName} onRestart",TAG)
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        WkLog.d("${this::class.java.simpleName} onNewIntent",TAG)
    }

    override fun onPause() {
        super.onPause()
        WkLog.d("${this::class.java.simpleName} onPause",TAG)
    }

    override fun onStop() {
        super.onStop()
        WkLog.d("${this::class.java.simpleName} onStop",TAG)
    }

    override fun onDestroy() {
        super.onDestroy()
        WkLog.d("${this::class.java.simpleName} onDestroy",TAG)
    }


    override fun communication(flag: Int, bundle: Bundle?, any: Any?) {}

    open fun beforeSetContentView() {
        if(hideSupportActionBar()){
            supportActionBar?.hide()
        }
    }

    open fun hideSupportActionBar():Boolean=true

    override fun onClick(v: View?) {
    }

    override fun onLongClick(v: View?): Boolean {
        return false
    }

    abstract fun initResLayId(): Any
    abstract fun bindView(savedInstanceState: Bundle?, mBaseProjectsActivity: BaseProjectsActivity)

    fun showToast(msg:String){
        WkToast.showToast(msg)
    }

    protected fun startActivity(`class`:Class<*>){
        startActivity(Intent(this,`class`))
    }

}