package com.shahram.noorani.arcprogress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;


import java.text.DecimalFormat;

/**
 * Created by Shahram on 1/28/2018.
 */

public class CircleProgress extends android.support.v7.widget.AppCompatImageView {
    private Context mContext;
    private boolean showTextPercent = true;
    private Paint paintOne, paintTow, paintFree, painText;
    private Typeface fontText;
    int colorBace, colorPercent;
    int percent = 120;
    int min = 0, max = 100;
    int heght, width;
    int textSize = 14;
    int percentdraw = 0;
    boolean start = true;
    private DecimalFormat formater;
    private GlobalInterface<String> notSupportUiAndroidVersion;
    private int backColor=Color.WHITE;
    private int textColor=Color.BLACK;
    private int textCurentProgressColor=Color.BLACK;
    private int textSizeCurentProgress=14;

    public boolean isShowTextPercent() {
        return showTextPercent;
    }

    public void setShowTextPercent(boolean showTextPercent) {
        this.showTextPercent = showTextPercent;
        invalidate();
    }

    public void setNotSupportUiAndroidVersion(GlobalInterface<String> notSupportUiAndroidVersion) {
        this.notSupportUiAndroidVersion = notSupportUiAndroidVersion;
    }


    public CircleProgress(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public CircleProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        init();
    }


    public int getMin()
    {return min;}
    public int getMax()
    {return max;}
    public void setTextSizeCurentProgress(int size)
    {
        textSizeCurentProgress=size;
        invalidate();
    }

    public void setTextColorCurentProgress(int color)
    {
        textCurentProgressColor=color;
        invalidate();
    }

    public void setTextColor(int color)
    {
        textColor=color;
        painText.setColor(color);
        invalidate();
    }
    public void setBackgroundCircleColor(int color)
    {
        backColor=color;
        paintFree.setColor(backColor);
        invalidate();
    }

    private float step()
    {

        float s=max-min;
        s=180/s;



        return s;
    }
    public void setEmptyColor(int color) {
        this.colorBace = color;
        paintOne.setColor(color);
        invalidate();
    }

    /**
     * value betwin 180 and 0
     **/
    public void setProgress(int progress) {

        this.percent = progress;
        percentdraw = 0;
        start = true;
        anim();
        invalidate();
    }

    public void setTextSize(int size) {
        this.textSize = size;
        painText.setTextSize(dpToPixel(size));
        invalidate();

    }

    public void setFillColor(int color) {
        this.colorPercent = color;
        paintTow.setColor(colorPercent);
        invalidate();


    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        heght = getHeight();
        width = getWidth();


    }


    public void setTextTypface(Typeface typface) {
        painText.setTypeface(typface);
        invalidate();
    }

    private void init() {
        formater = new DecimalFormat("#,###,###.###");
        paintOne = new Paint();
        paintOne.setAntiAlias(true);
        paintOne.setColor(colorBace);
        paintOne.setStyle(Paint.Style.FILL);


        paintTow = new Paint();
        paintTow.setAntiAlias(true);
        paintTow.setColor(colorPercent);
        paintTow.setStyle(Paint.Style.FILL);

        paintFree = new Paint();
        paintFree.setAntiAlias(true);
        paintFree.setColor(backColor);
        paintFree.setStyle(Paint.Style.FILL);

        painText = new Paint();
        painText.setAntiAlias(true);
        painText.setColor(textColor);
        painText.setStyle(Paint.Style.FILL);
        painText.setTextAlign(Paint.Align.CENTER);
        painText.setTextSize(dpToPixel(textSize));

    }

    private void anim() {

        final Thread animt = new Thread(new Runnable() {

            @Override
            public void run() {


                while (percentdraw < percent) {


                    try {

                        percentdraw++;
                        Log.i("PDO", "draw=" + percentdraw);
                        Log.i("PDO", "per" + percent);


                        Thread.sleep(5);

                        if (percentdraw <= 180) {

                            postInvalidate();

                        }


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                }


            }
        });
        animt.start();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        if (percentdraw <= 181) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

                canvas.drawArc(10, 10, width - 10, (heght * 2) - 200, 0, -180, true, paintOne);
                canvas.drawArc(10, 10, width - 10, (heght * 2) - 200, -180, percentdraw, true, paintTow);

                canvas.drawArc(65, 65, width - 65, (heght * 2) - 255, 0, -180, true, paintFree);

                canvas.drawText(formater.format(max) + "", width - 50, heght - 40, painText);
                canvas.drawText(formater.format(min) + "", 50, heght - 40, painText);

                if (showTextPercent) {
                    int ppp= (int) (percentdraw/step());


                    painText.setTextSize(dpToPixel(textCurentProgressColor));
                    painText.setColor(textCurentProgressColor);
                    canvas.drawText(ppp+min + "", (width / 2), (heght / 2), painText);
                    painText.setTextSize(dpToPixel(textSize));
                    painText.setColor(textColor);

                }

            } else {
                if (notSupportUiAndroidVersion != null) {
                    notSupportUiAndroidVersion.on("");
                }
            }
        }
    }

    public void setMin(int min) {
        this.min = min;
        invalidate();
    }

    public void setMax(int max) {
        this.max = max;
        invalidate();
    }

    private int dpToPixel(int dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        int pixel = (int) (dp * scale + 0.5f);
        return pixel;
    }
}
