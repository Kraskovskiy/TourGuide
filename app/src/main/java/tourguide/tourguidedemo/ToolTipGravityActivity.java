package tourguide.tourguidedemo;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.BounceInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;

import tourguide.tourguide.Overlay;
import tourguide.tourguide.Pointer;
import tourguide.tourguide.ToolTip;
import tourguide.tourguide.TourGuide;


public class ToolTipGravityActivity extends ActionBarActivity {
    public TourGuide mTutorialHandler;
    public Activity mActivity;
    public static final String TOOLTIP_NUM = "tooltip_num";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        /* Get parameters from main activity */
        Intent intent = getIntent();
        int tooltip_num = intent.getIntExtra(TOOLTIP_NUM, 1);

        super.onCreate(savedInstanceState);
        mActivity = this;
        int gravity;

        if (tooltip_num == 1) {
            setContentView(R.layout.activity_tooltip_gravity_i);
            gravity = Gravity.RIGHT | Gravity.BOTTOM;
        } else if (tooltip_num == 2) {
            setContentView(R.layout.activity_tooltip_gravity_ii);
            gravity = Gravity.LEFT | Gravity.BOTTOM;
        } else if (tooltip_num == 3) {
            setContentView(R.layout.activity_tooltip_gravity_iii);
            gravity = Gravity.LEFT | Gravity.TOP;
        } else {
            setContentView(R.layout.activity_tooltip_gravity_iv);
            gravity = Gravity.RIGHT | Gravity.TOP;
        }
        Button button = (Button)findViewById(R.id.button);


        ToolTip toolTip = new ToolTip()
                .setTitle("title")
                .setWidth(650)
                .setDescription("Click on Get Started to begin...")
                .setTextColor(Color.RED)
                .setTextColorDescription(Color.GRAY)
                .setBackgroundColor(Color.WHITE)
                .setShadow(true)
                .setBottomMargin(50)
                .setGravity(gravity)
               // .setOnClickListener(null)
                .setEnterAnimation(returnAnimation());

        Overlay overlay = new Overlay()
                .setStyle(Overlay.Style.CIRCLE)
                .setHoleRadius(50)
                .setHoleOffsets(0,0);
               // .disableClickThroughHole(true);
               // .setBackgroundColor(activity.getResources().getColor(R.color.colorTutorialBackgroundLight));

        Pointer pointer = new Pointer().setShowAnimation(false);

      /*  return TourGuide.init(activity).with(TourGuide.Technique.CLICK)
                .setPointer(pointer)
                // .setPointer(new Pointer())
                .setToolTip(toolTip)
                .setOverlay(overlay)
                .playOn(view);*/

        mTutorialHandler = TourGuide.init(this).with(TourGuide.Technique.CLICK)
                .setPointer(pointer)
               //  .setPointer(new Pointer())
                .setToolTip(toolTip)
                .setOverlay(overlay)
                .playOn(button);

/*
        ToolTip toolTip = new ToolTip().
                setTitle("Welcome!").
                setDescription("Click on Get Started to begin...").
                setBackgroundColor(Color.parseColor("#2980b9")).
                setTextColor(Color.parseColor("#FFFFFF")).
                setGravity(gravity).
                setShadow(true);

        mTutorialHandler = TourGuide.init(this).with(TourGuide.Technique.CLICK)
              //  .setPointer(null)
                .setPointer(new Pointer())
                .setToolTip(toolTip)
                .setOverlay(new Overlay())
                .playOn(button);*/

        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mTutorialHandler.cleanUp();
            }
        });
    }

    public static Animation returnAnimation() {
        Animation animation = new TranslateAnimation(0f, 0f, 200f, 0f);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation.setInterpolator(new BounceInterpolator());
        return animation;
    }
}
