package com.xll.mvplib.view.materialRefreshLayout;

import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;

public class MaterialFooterView extends FrameLayout implements MaterialHeadListener {
    private MaterialWaveView materialWaveView;
    private CircleProgressBar circleProgressBar;
    private int waveColor;
    private int progressTextColor;
    private int[] progress_colors;
    private int progressStokeWidth;
    private boolean isShowArrow, isShowProgressBg;
    private int progressValue, progressValueMax;
    private int textType;
    private int progressBg;
    private int progressSize;
    private static float density;
    private MaterialHeadListener listener;

    public MaterialFooterView(Context context) {
        this(context, null);
    }

    public MaterialFooterView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MaterialFooterView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(attrs, defStyle);
    }

    public CircleProgressBar getCircleProgressBar() {
        return circleProgressBar;
    }

    protected void init(AttributeSet attrs, int defStyle) {
        if (isInEditMode()) return;
        setClipToPadding(false);
        setWillNotDraw(false);
    }

    public int getWaveColor() {
        return waveColor;
    }

    public void setWaveColor(int waveColor) {
        this.waveColor = waveColor;
        if (null != materialWaveView) {
            materialWaveView.setColor(this.waveColor);
        }
    }

    public void setProgressSize(int progressSize) {
        this.progressSize = progressSize;

        // add by wcz 2017/3/3
        LayoutParams layoutParams = new LayoutParams((int) density * progressSize, (int) density * progressSize);
        layoutParams.gravity = Gravity.CENTER;
        if(circleProgressBar!=null)
            circleProgressBar.setLayoutParams(layoutParams);
    }

    public void setProgressBg(int progressBg) {
        this.progressBg = progressBg;
        if(circleProgressBar!=null)
        circleProgressBar.setProgressBackGroundColor(progressBg);
    }

    public void setIsProgressBg(boolean isShowProgressBg) {
        this.isShowProgressBg = isShowProgressBg;
        if(circleProgressBar!=null)
        circleProgressBar.setCircleBackgroundEnabled(isShowProgressBg);
    }

    public void setProgressTextColor(int textColor) {
        this.progressTextColor = textColor;
    }

    public void setProgressColors(int[] colors) {
        this.progress_colors = colors;
        if(circleProgressBar!=null)
        circleProgressBar.setColorSchemeColors(progress_colors);
    }

    public void setTextType(int textType) {
        this.textType = textType;
    }

    public void setProgressValue(int value) {
        this.progressValue = value;
        this.post(new Runnable() {
            @Override
            public void run() {
                if (circleProgressBar != null) {
                    circleProgressBar.setProgress(progressValue);
                }
            }
        });

    }

    public void setProgressValueMax(int value) {
        this.progressValueMax = value;
    }

    public void setProgressStokeWidth(int w) {
        this.progressStokeWidth = w;
        if(circleProgressBar!=null)
        circleProgressBar.setProgressStokeWidth(progressStokeWidth);
    }

    public void showProgressArrow(boolean isShowArrow) {
        this.isShowArrow = isShowArrow;
        if(circleProgressBar!=null)
        circleProgressBar.setShowArrow(isShowArrow);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        density = getContext().getResources().getDisplayMetrics().density;

        materialWaveView = new MaterialWaveView(getContext());
        materialWaveView.setColor(waveColor);
        addView(materialWaveView);

        circleProgressBar = new CircleProgressBar(getContext());
        LayoutParams layoutParams = new LayoutParams((int) density * progressSize, (int) density * progressSize);
        layoutParams.gravity = Gravity.CENTER;
        circleProgressBar.setLayoutParams(layoutParams);
        circleProgressBar.setColorSchemeColors(progress_colors);
        circleProgressBar.setProgressStokeWidth(progressStokeWidth);
        circleProgressBar.setShowArrow(isShowArrow);
        circleProgressBar.setShowProgressText(textType == 0);
        circleProgressBar.setTextColor(progressTextColor);
        circleProgressBar.setProgress(progressValue);
        circleProgressBar.setMax(progressValueMax);
        circleProgressBar.setCircleBackgroundEnabled(isShowProgressBg);
        circleProgressBar.setProgressBackGroundColor(progressBg);
        addView(circleProgressBar);
    }

    @Override
    public void onComlete(MaterialRefreshLayout materialRefreshLayout) {
        if (materialWaveView != null) {
            materialWaveView.onComlete(materialRefreshLayout);
        }
        if (circleProgressBar != null) {
            circleProgressBar.onComlete(materialRefreshLayout);

            ViewCompat.setTranslationY(circleProgressBar, 0);
            ViewCompat.setScaleX(circleProgressBar, 0);
            ViewCompat.setScaleY(circleProgressBar, 0);
        }
    }

    @Override
    public void onBegin(MaterialRefreshLayout materialRefreshLayout) {
        if (materialWaveView != null) {
            materialWaveView.onBegin(materialRefreshLayout);
        }
        if (circleProgressBar != null) {
            // delete by wcz 2017/3/3
//            ViewCompat.setScaleX(circleProgressBar, 1f);
//            ViewCompat.setScaleY(circleProgressBar, 1f);
            // add by wcz 2017/3/3
            ViewCompat.setScaleX(circleProgressBar, 0.001f);
            ViewCompat.setScaleY(circleProgressBar, 0.001f);
            circleProgressBar.onBegin(materialRefreshLayout);
        }
    }

    @Override
    public void onPull(MaterialRefreshLayout materialRefreshLayout, float fraction) {
        if (materialWaveView != null) {
            materialWaveView.onPull(materialRefreshLayout, fraction);
        }
        if (circleProgressBar != null) {
            circleProgressBar.onPull(materialRefreshLayout, fraction);
            float a = Util.limitValue(1, fraction);

                // delete by wcz 2017/3/3
//            ViewCompat.setScaleX(circleProgressBar, 1);
//            ViewCompat.setScaleY(circleProgressBar, 1);

            // add by wcz 2017/3/3
            ViewCompat.setScaleX(circleProgressBar, a);
            ViewCompat.setScaleY(circleProgressBar, a);
            ViewCompat.setAlpha(circleProgressBar, a);
        }
    }

    @Override
    public void onRelease(MaterialRefreshLayout materialRefreshLayout, float fraction) {

    }

    @Override
    public void onRefreshing(MaterialRefreshLayout materialRefreshLayout) {
        if (materialWaveView != null) {
            materialWaveView.onRefreshing(materialRefreshLayout);
        }
        if (circleProgressBar != null) {
            circleProgressBar.onRefreshing(materialRefreshLayout);
        }
    }

}


