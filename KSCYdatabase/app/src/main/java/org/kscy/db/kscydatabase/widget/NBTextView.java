package org.kscy.db.kscydatabase.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by pyh42 on 2017-07-27.
 */

public class NBTextView extends android.support.v7.widget.AppCompatTextView {
    public NBTextView(Context context) {
        super(context);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "NanumBarunGothic.otf");
        this.setTypeface(face);
    }

    public NBTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "NanumBarunGothic.otf");
        this.setTypeface(face);
    }

    public NBTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Typeface face=Typeface.createFromAsset(context.getAssets(), "NanumBarunGothic.otf");
        this.setTypeface(face);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
