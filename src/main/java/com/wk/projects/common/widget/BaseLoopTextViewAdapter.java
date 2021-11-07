package com.wk.projects.common.widget;

import java.util.List;

/**
 * @author :wangkang_shenlong
 * email        :shenlong.wang@tuya.com
 * create date  : 2021/07/22
 * desc         :
 */


abstract class BaseLoopTextViewAdapter<T> {

    private List<T> mData;

    protected LoopTextView mLoopTextView;


    public BaseLoopTextViewAdapter(List<T> data) {
        this.mData = data;
    }


    void setLoopTextView(LoopTextView loopTextView) {
        mLoopTextView = loopTextView;
    }

    abstract String getText(int realPosition);




}
