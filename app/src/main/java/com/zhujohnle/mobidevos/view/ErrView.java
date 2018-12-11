package com.zhujohnle.mobidevos.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.zhujohnle.mobidevlibrary.R;

public class ErrView extends FrameLayout {
    TextView tvDesc;

    public ErrView(@NonNull Context context) {
        super(context);
        init();
    }

    public ErrView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ErrView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        View contentView = LayoutInflater.from(getContext()).inflate(R.layout.status_err, this, false);

        this.tvDesc = contentView.findViewById(R.id.tv_dsc);

        addView(contentView);
    }


    public void setErrMsg(CharSequence text) {
        tvDesc.setText(text);

    }

}
