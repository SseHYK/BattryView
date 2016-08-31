package superscene.com.mybattery.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by yhl on 2016/6/17.
 **/
public class BatteryView extends View {

    private int mPower = 80;

    private int battery_inside_margin = 2;
    private int battery_left = 10;
    private int battery_top = 10;
    private int battery_width = 30;
    private int battery_height = 60;

    private int battery_head_height = 5;
    private int battery_head_width = 15;

    public BatteryView(Context context) {
        super(context);
    }

    public BatteryView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width;
        int height;
        if (widthMode == MeasureSpec.EXACTLY) {
            width = widthSize;
        } else {
            width = 125;
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            height = heightSize;
        } else {
            height = 90;
        }

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);

        Paint paint2 = new Paint(paint);
        paint2.setStyle(Paint.Style.FILL);
        paint2.setTextSize(30);

        //画电池头
        int h_left = (battery_width - battery_head_width) / 2 + battery_left;
        int h_right = h_left + battery_head_width;
        int h_bottom = battery_head_height + battery_top;
        Rect rect3 = new Rect(h_left, battery_top, h_right, h_bottom);
        canvas.drawRect(rect3, paint2);

        //画外框
        Rect rect = new Rect(battery_left, h_bottom,
                battery_width + battery_left, battery_height + h_bottom);
        canvas.drawRect(rect, paint);

        float power_percent = mPower / 100.0f;

        int p_left = battery_left + battery_inside_margin;
        //画电量
        if (power_percent != 0) {
            int p_bottom = battery_height - battery_inside_margin + h_bottom;
            int p_top = p_bottom - (int) ((power_percent * (battery_height - 2 * battery_inside_margin)) + 0.5);
            int p_right = battery_width - battery_inside_margin + battery_left;
            Rect rect2 = new Rect(p_left, p_top, p_right, p_bottom);
            canvas.drawRect(rect2, paint2);
        }

        //画文字
        canvas.drawText(mPower + "%", battery_width + p_left, battery_height + h_bottom, paint2);
    }

    public void setPower(int power) {
        mPower = power;
        if (mPower < 0) {
            mPower = 0;
        }
        invalidate();
    }

    public int getPower() {
        return mPower;
    }
}