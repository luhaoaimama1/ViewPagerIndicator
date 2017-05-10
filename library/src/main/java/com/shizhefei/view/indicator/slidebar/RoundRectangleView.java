package com.shizhefei.view.indicator.slidebar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * [2017] by Zone
 */

public class RoundRectangleView extends View {
    private final float rx;
    private Paint paint;

    public RoundRectangleView(Context context, float rx) {
        super(context);
        this.rx = rx;
        paint = new Paint();
        paint.setDither(true);
        paint.setAntiAlias(true);

    }

    @Override
    public void setBackgroundColor(int color) {
        super.setBackgroundColor(color);
        paint.setColor(color);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRoundRect(new RectF(0, 0, getWidth(), getHeight()), rx, rx, paint);
    }
}
