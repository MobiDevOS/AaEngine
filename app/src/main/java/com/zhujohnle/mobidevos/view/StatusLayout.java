package com.zhujohnle.mobidevos.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class StatusLayout extends FrameLayout {
    private View contentView;
    Map<ViewTag, View> viewMap = new HashMap<>();
    private long durationMillis = 600;
    private Handler mHandler = new Handler();

    public StatusLayout(@NonNull Context context) {
        this(context, null);
    }

    public StatusLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusLayout(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /***
     * xml 初始化完成
     * 判断子View控件的个数，如果超过了2个则抛异常
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        int childCount = getChildCount();
        if (childCount > 1) {
            throw new IllegalStateException("PtrFrameLayout can only contains 1 children");
        } else {
            if (childCount == 1) {
                contentView = getChildAt(0);
                viewMap.put(ViewTag.NORMAL, contentView);
            }
        }
    }

    private void addViewInMyLayout(View childView) {
        ViewGroup.LayoutParams params = childView.getLayoutParams();
        if (params == null) {
            params = generateDefaultLayoutParams();
            if (params == null) {
                throw new IllegalArgumentException("generateDefaultLayoutParams() cannot return null");
            }
        }
        addViewInLayout(childView, -1, params);
    }


    private void show(ViewTag mapkey) {
        show(mapkey, false);
    }

    //  显示制定视图
    private void show(ViewTag mapKey, boolean isAnim) {
        Set<ViewTag> keys = viewMap.keySet();
        View view = viewMap.get(mapKey);
        if (view == null || view.getVisibility() == View.VISIBLE) {
            return;
        }

        if (isAnim) {
            AnimationController animationController = new AnimationController();
            animationController.fadeIn(view, durationMillis, 0);
        } else {
            view.setVisibility(View.VISIBLE);
        }
        for (ViewTag key : keys) {
            final View v = viewMap.get(key);
            if (v!=view&&v.getVisibility() == View.VISIBLE) {
                if (isAnim) {
                    if (key == ViewTag.LOADING) {
                        animOut(v,(long) (durationMillis * 0.5f));  //注意这里需要用属性动画，防止其他动画，冷启动出现问题
                    } else {
                        v.setVisibility(View.GONE);
                    }
                } else {
                    v.setVisibility(View.GONE);
                }
            }
        }

    }

    private void animOut(final View v,long duration) {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(1f, 0); // 动画
        valueAnimator.setDuration(duration);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                v.setAlpha(value);
            }
        });
        valueAnimator.start();
        valueAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                v.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                v.setVisibility(View.GONE);
                ViewCompat.animate(v).setStartDelay(0).setDuration(0).scaleX(1).scaleY(1).alpha(1).start();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                v.setVisibility(View.GONE);
                ViewCompat.animate(v).setStartDelay(0).setDuration(0).scaleX(1).scaleY(1).alpha(1).start();
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }


    private void addView(ViewTag empty, View view) {
        View view1 = viewMap.get(empty);
        if (view1 != null) {
            removeView(view1);
        }
        viewMap.put(empty, view);
        view.setVisibility(View.GONE);
        addView(view);
    }

    public void setEmptyView(View view) {
        addView(ViewTag.EMPTY, view);
    }


    public void setNormalView(View view) {
        addView(ViewTag.NORMAL, view);
    }

    public void setErrView(View view) {
        addView(ViewTag.ERR, view);
    }

    public void setNetView(View view) {
        addView(ViewTag.NET, view);
    }

    public void setLoading(View view) {
        addView(ViewTag.LOADING, view);
    }

    public void showEmpty() {
        show(ViewTag.EMPTY);
    }

    public void showEmpty(boolean isAnim) {
        show(ViewTag.EMPTY, isAnim);
    }

    public void showNormal(long duration){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                show(ViewTag.NORMAL);
            }
        },duration);
    }
    public void showNormal() {
        show(ViewTag.NORMAL);
    }

    public void showNet() {
        show(ViewTag.NET);
    }

    public void showLoading() {
        show(ViewTag.LOADING, false);
    }

    public void showErr() {
        show(ViewTag.ERR);
    }

    public View getErrView() {
        return viewMap.get(ViewTag.ERR);
    }

    public View getEmptyView() {
        return viewMap.get(ViewTag.EMPTY);
    }


    public View getViewByTag(ViewTag tag) {
        return viewMap.get(tag);
    }

    public void setViewLayout(ViewTag tag, int resId) {
        View view = LayoutInflater.from(getContext()).inflate(resId, null, false);
        addView(tag, view);
    }

    public void setViewClickListener(ViewTag tag, int id, OnClickListener onClickListener) {
        View viewByTag = getViewByTag(tag);
        View viewById = viewByTag.findViewById(id);
        viewById.setOnClickListener(onClickListener);
    }

    public void setViewTextById(ViewTag tag, int id, String text) {
        View viewByTag = getViewByTag(tag);
        View viewById = viewByTag.findViewById(id);
        ((TextView) viewById).setText(text);
    }

    public void setViewImageById(ViewTag tag, int id, int resId) {
        View viewByTag = getViewByTag(tag);
        View viewById = viewByTag.findViewById(id);
        ((ImageView) viewById).setImageResource(resId);
    }

    public void setVisibilityById(ViewTag tag, int id, int visibility) {
        View viewByTag = getViewByTag(tag);
        View viewById = viewByTag.findViewById(id);
        viewById.setVisibility(visibility);
    }

    public enum ViewTag {
        EMPTY,
        NORMAL,
        NET,
        LOADING,
        ERR
    }

}
