package com.wk.projects.common.collapse

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.view.View
import android.view.Window
import com.wk.projects.common.BaseProjectsActivity
import com.wk.projects.common.R
import com.wk.projects.common.communication.constant.BundleKey
import com.wk.projects.common.communication.constant.BundleKey.FILE
import kotlinx.android.synthetic.main.common_activity_collapse.*
import java.io.File
import java.util.*
import kotlin.system.exitProcess

/**
 * <pre>
 *      author : wk
 *      e-mail : 122642603@qq.com
 *      time   : 2018/11/28
 *      GitHub : https://github.com/wk1995
 *      CSDN   : http://blog.csdn.net/qq_33882671
 *      desc   :
 * </pre>
 */
abstract class BaseCollapseActivity : BaseProjectsActivity(), View.OnClickListener {
    override fun initResLayId() = R.layout.common_activity_collapse
    private lateinit var collapseAdapter: CollapseRvAdapter

    override fun initContentView() {
        super.initContentView()
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        if(supportActionBar!=null)
            supportActionBar?.hide()

    }

    override fun bindView(savedInstanceState: Bundle?, mBaseProjectsActivity: BaseProjectsActivity) {
        setFinishOnTouchOutside(false)
        btn_appRestart.setOnClickListener(this)
        btn_appClose.setOnClickListener(this)
        val logPath:String = intent.getStringExtra(BundleKey.PATH)?:return

        val fileList = File(logPath).listFiles()?.toList()?:ArrayList()
        Collections.reverse(fileList)
        collapseAdapter = CollapseRvAdapter(fileList)
        collapseAdapter.setOnItemChildClickListener { _, _, position ->
            val watchIntent = Intent(this, initWatchActivityClass())
            watchIntent.putExtra(FILE, fileList[position])
            startActivity(watchIntent)

        }
        rvCollapseFile.layoutManager = LinearLayoutManager(this)
        rvCollapseFile.adapter = collapseAdapter
    }

    override fun onClick(v: View?) {
        when (v) {
            btn_appRestart -> {
                val intent = Intent(this, reStartActivityClass())
                intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(intent)
                android.os.Process.killProcess(android.os.Process.myPid())
                exitProcess(0)
            }
            btn_appClose -> {
                android.os.Process.killProcess(android.os.Process.myPid())
                exitProcess(0)
            }
        }
    }


    /**重启后打开的界面*/
    abstract fun reStartActivityClass(): Class<out BaseProjectsActivity>

    /**查看日志的界面*/
    abstract fun initWatchActivityClass(): Class<out BaseWatchActivity>

}