package com.qq281982108.tallylight.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import com.qq281982108.tallylight.R;
import com.qq281982108.tallylight.util.DensityUtils;

/**
 * 项目名称：TallyLight
 * 创建人：yh
 * 创建时间：2016-04-15 9:25
 * 类名：DashedLine
 * 修改备注：
 */
public class DashedLine extends View {
    //    private final String namespace = "http://com.smartmap.driverbook";
    private float startX;
    private float startY;
    private float endX;
    private float endY;
    private Rect mRect;

    public DashedLine(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(getResources().getColor(R.color.colorBlue));
        paint.setStrokeWidth(DensityUtils.dp2px(getContext(), 2.0f));
        Path path = new Path();
        path.moveTo(0, 0);//起始坐标
        path.lineTo(0, 500);//终点坐标
        float[] arrayOfFloat = new float[4];
        arrayOfFloat[0] = DensityUtils.dp2px(getContext(), 10.0F);
        arrayOfFloat[1] = DensityUtils.dp2px(getContext(), 10.0F);
        arrayOfFloat[2] = DensityUtils.dp2px(getContext(), 10.0F);
        arrayOfFloat[3] = DensityUtils.dp2px(getContext(), 10.0F);
        PathEffect effects = new DashPathEffect(arrayOfFloat, DensityUtils.dp2px(getContext(), 10.0f));//设置虚线的间隔和点的长度
        paint.setPathEffect(effects);

        canvas.drawPath(path, paint);
    }
}
