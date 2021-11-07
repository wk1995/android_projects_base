package com.wk.projects.common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.Nullable;

import java.util.List;

/**
 * @author :wangkang_shenlong
 * email        :shenlong.wang@tuya.com
 * create date  : 2021/07/21
 * desc         :
 */


public class LoopTextView extends View {

    private static final String TAG="LoopTextView";

    private ILoopListener mILoopListener;

    /**
     * 显示个数
     */
    private int showSize = 3;

    private List<String> data;


    private BaseLoopTextViewAdapter<String> wheelGearsAdapter;

    /**
     * 文字颜色，默认白色
     */
    @ColorInt
    private int mTextColor = Color.WHITE;

    /**
     * 文字 paint
     */
    Paint mTextPaint;

    /**
     * 居中文本的透明度
     */
    private float centerTextAlpha = 255f;


    /**
     * 文字大小，默认为 24 sp
     */
    int mCenterTextSize = 64;

    /**
     * 边界文字大小
     */
    int boundaryTextSize = 52;

    /**
     * 控件宽高
     */
    int mWidth, mHeight;

    /**
     * 是否循环显示
     */
    boolean isLoopShow = true;


    /**
     * 选中第几个,从0开始
     */
    private int mCurrentPosition = 0;

    /**
     * 当前选中的纵坐标
     */
    private double mCurrentYPosition = 0d;


    public LoopTextView(Context context) {
        super(context);
        init();
    }

    public LoopTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public LoopTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {

        mTextPaint = new Paint();
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mCenterTextSize);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextAlign(Paint.Align.CENTER);
    }


    public void setTuyaLoopListener(ILoopListener tuyaLoopListener){
        mILoopListener =tuyaLoopListener;
    }

    /**
     * 根据数据指针，获取真实显示数据
     */
    private String getRealDate(int position) {
        int realPosition = position;
        int size = data.size();
        if (isLoopShow) {
            realPosition = (realPosition + size) % data.size();
        }
        return data.get(realPosition);
    }


    public void setData(List<String> data) {
        this.data = data;
        invalidate();
    }

    /**
     * 获取数据对应的纵坐标的差值
     */
    private double getUnit() {
        return mHeight * 1.0 / (showSize - 1);
    }

    /**
     * 是否有效
     *
     * @return true 有效
     */
    private boolean isValidPosition(double position) {
        return !(position < 0) && !(position > mHeight);
    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        mHeight = getHeight();
        mCurrentYPosition = mHeight / 2f;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawText(canvas);
    }


    private void drawText(Canvas canvas) {
        if (data == null) {
            return;
        }

        mTextPaint.setAlpha(getTextAlpha(mCurrentYPosition));
        mTextPaint.setTextSize(getTextSize(mCurrentYPosition));
        canvas.drawText(getRealDate(mCurrentPosition), mWidth / 2f, (float) mCurrentYPosition, mTextPaint);


        double lastYPosition = mCurrentYPosition - getUnit();
        int i = 1;
        while (isValidPosition(lastYPosition) && (mCurrentPosition - i >= 0 || isLoopShow)) {
            mTextPaint.setAlpha(getTextAlpha(lastYPosition));
            mTextPaint.setTextSize(getTextSize(lastYPosition));
            canvas.drawText(getRealDate(mCurrentPosition - i), mWidth / 2f, (float) lastYPosition, mTextPaint);
            lastYPosition -= getUnit();
            i++;
        }

        double nextYPosition = mCurrentYPosition + getUnit();
        i = 1;
        while (isValidPosition(nextYPosition) && (mCurrentPosition + i < data.size() || isLoopShow)) {
            mTextPaint.setTextSize(getTextSize(nextYPosition));
            mTextPaint.setAlpha(getTextAlpha(nextYPosition));
            canvas.drawText(getRealDate(mCurrentPosition + i), mWidth / 2f, (float) nextYPosition, mTextPaint);
            nextYPosition += getUnit();
            i++;
        }
    }

    /**
     *
     * 确定当前的选中index
     * 判断原理：
     * 1：对应的纵坐标为mHeight/2
     * 2：对应的纵坐标<mHeight/2, next纵坐标> mHeight/2
     */
    private void checkCurrentPosition(boolean hasChange) {
        int size = data.size();
        mCurrentPosition = (mCurrentPosition + size) % size;
        double selectY = mHeight / 2f;
        if (mCurrentYPosition == selectY) {
            if(hasChange){
                if(mILoopListener !=null){
                    mILoopListener.loopListener(1,mCurrentPosition);
                }
            }
            return;
        }
        if (mCurrentYPosition < selectY) {
            double nextYPosition = mCurrentYPosition + getUnit();
            if (nextYPosition <= selectY) {
                mCurrentYPosition = nextYPosition;
                ++mCurrentPosition;
                checkCurrentPosition(true);
            }
        } else {
            if (mCurrentPosition == 0) {
                return;
            }
            double lastYPosition = mCurrentYPosition - getUnit();
            if (lastYPosition >= selectY) {
                mCurrentYPosition = lastYPosition;
                --mCurrentPosition;
                checkCurrentPosition(true);
            }
        }
    }


    public void loop() {
        mCurrentYPosition -= 1;
        checkCurrentPosition(false);
        invalidate();
    }

    /**
     * 根据纵坐标，获取文本字体大小
     */
    private int getTextSize(double y) {
        if (y < 0f || y > mHeight) {
            return 0;
        }

        if (y <= mHeight / 2f) {
            return (int) Math.abs((mCenterTextSize - boundaryTextSize) * y * 2 / mHeight) + boundaryTextSize;
        }
        return mCenterTextSize - (int) Math.abs((mCenterTextSize - boundaryTextSize) * (y - mHeight / 2f) * 2 / mHeight);
    }

    /**
     * 根据纵坐标，获取文本字体透明度
     */
    private int getTextAlpha(double y) {
        if (y < 0f || y > mHeight) {
            return 0;
        }
        if (y <= mHeight / 2f) {
            return (int) Math.abs(centerTextAlpha * y * 2 / mHeight);
        }

        return (int) centerTextAlpha - (int) Math.abs(centerTextAlpha * (y - mHeight / 2f) * 2 / mHeight);
    }




}



