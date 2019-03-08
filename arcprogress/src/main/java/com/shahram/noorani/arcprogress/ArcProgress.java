package com.shahram.noorani.arcprogress;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


/**
 * Created by Shahram on 1/28/2018.
 */

public class ArcProgress extends LinearLayout {


    private int Percent = 0;
    private ImageView img_precent;
    private CircleProgress circleProgress;
    private NotSupportListner notSupportAndroidVersion;



    public ArcProgress(Context context) {
        super(context);
        init(context,null);
    }

    public ArcProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public ArcProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }


    public void setNotSupportAndroidVersionListner(NotSupportListner notSupportAndroidVersion) {
        this.notSupportAndroidVersion = notSupportAndroidVersion;
    }

    public void setTextColorCurentProgress(int color){
        circleProgress.setTextColorCurentProgress(color);
    }
    public void setTextColor(int color){
        circleProgress.setTextColor(color);
    }
    public void setBackgroundCircleColor(int color)
    {
        circleProgress.setBackgroundCircleColor(color);
    }
    private void init(Context context, AttributeSet attrs) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View customView = inflater.inflate(R.layout.arc_progress, this, true);

        img_precent = (ImageView) customView.findViewById(R.id.image_per);
        circleProgress = (CircleProgress) customView.findViewById(R.id.circle_progress);
        circleProgress.setNotSupportUiAndroidVersion(new GlobalInterface<String>() {
            @Override
            public void on(String data) {
                if (notSupportAndroidVersion!=null)
                {
                    notSupportAndroidVersion.notSupport();
                }
            }
        });


        if (attrs!=null)
        {

            TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ArcProgress);
            setFillColor(ta.getColor(R.styleable.ArcProgress_arc_fill_color, Color.parseColor("#f26c4f")));
            setEmptyColor(ta.getColor(R.styleable.ArcProgress_arc_empty_color,Color.parseColor("#57ca87")));
            setTextColorCurentProgress(ta.getColor(R.styleable.ArcProgress_arc_progress_text_color,Color.BLACK));
            setTextSizeCurentProgress((int) ta.getDimension(R.styleable.ArcProgress_arc_progress_text_size,20));
            setTextSizeMinMax((int) ta.getDimension(R.styleable.ArcProgress_arc_min_max_text_size,15));
            setTextColor(ta.getColor(R.styleable.ArcProgress_arc_min_max_color,Color.BLACK));
            setBackgroundCircleColor(ta.getColor(R.styleable.ArcProgress_arc_background_color,Color.WHITE));
            setmin(ta.getInteger(R.styleable.ArcProgress_arc_min,1));
            setMax(ta.getInteger(R.styleable.ArcProgress_arc_max,100));
            setImageProgressDegree(ta.getResourceId(R.styleable.ArcProgress_arc_image_degree,R.drawable.arrow));
            setProgress(ta.getInteger(R.styleable.ArcProgress_arc_progress,1));






            ta.recycle();
        }


    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


    }

    public void setMax(int max) {
        circleProgress.setMax(max);
    }

    public void setmin(int min) {
        circleProgress.setMin(min);
    }

   private float step()
   {
       int min=circleProgress.getMin();
       int max=circleProgress.getMax();
       float s=max-min;
       s=180/s;



       return s;
   }
    public void setProgress(int progress) {




        this.Percent = progress;
        float angle=progress*step();

        if (angle>180)
        {
            angle=180;
        }
        circleProgress.setProgress((int) Math.ceil(angle));
        RotateAnimation rotate = new RotateAnimation(0, angle,
                Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                0.5f);

        rotate.setDuration(800);
        rotate.setRepeatCount(0);
        rotate.setFillAfter(true);
        img_precent.startAnimation(rotate);
        invalidate();
    }

    public void setTextTypface(Typeface fontTypface) {
        circleProgress.setTextTypface(fontTypface);
    }

    public void setEmptyColor(int color) {
        circleProgress.setEmptyColor(color);
    }

    public void setFillColor(int color) {
        circleProgress.setFillColor(color);

    }

    public void setTextSizeMinMax(int size) {
        circleProgress.setTextSize(size);
    }
    public void setTextSizeCurentProgress(int size) {
        circleProgress.setTextSizeCurentProgress(size);
    }

    public void showCenterProgressNumber(boolean show)
    {
        circleProgress.setShowTextPercent(show);
    }
    public boolean isShowingCenterProgressNumber()
    {
        return circleProgress.isShowTextPercent();
    }
    public void setImageProgressDegree(@DrawableRes int drawableid)
    {
        img_precent.setImageResource(drawableid);
    }

}
