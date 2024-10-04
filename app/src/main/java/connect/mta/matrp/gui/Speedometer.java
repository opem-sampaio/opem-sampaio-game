package connect.mta.matrp.gui;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mikhaellopez.circularprogressbar.CircularProgressBar;

import java.util.Formatter;

import connect.mta.matrp.R;
import connect.mta.matrp.gui.util.Utils;
public class Speedometer {
    public Activity activity;
    public TextView mCarHP;
    public FrameLayout mStrela;
    public FrameLayout mStrela2;
    public ImageView mEngine;

    public ImageView carHP, carFuel, turnLeft, turnRight;
    public TextView mFuel;
    public LinearLayout mInputLayout;
    public ImageView mLight;
    public ImageView mLock;
    public TextView mMileage;
    public TextView mSpeed;

    public TextView sp0, sp20, sp40, sp60, sp80, sp100, sp120, sp140, sp160, sp180, sp200;
    public CircularProgressBar mSpeedLine;
    public ImageView povv, povv2;
    public int Pov, Pov2;

    native void sendClick(int clickId);

    public Speedometer(Activity activity){
        LinearLayout relativeLayout = activity.findViewById(R.id.speedometr_matrp_edgar);
        mInputLayout = relativeLayout;
        mSpeed = activity.findViewById(R.id.speed_text);
        mStrela = activity.findViewById(R.id.turn_left);
        mStrela2 = activity.findViewById(R.id.turn_right);
        mFuel = activity.findViewById(R.id.fuel_text);
        mCarHP = activity.findViewById(R.id.hp_text);
        mMileage = activity.findViewById(R.id.mileage);
        mSpeedLine = activity.findViewById(R.id.speed_line);
        mEngine = activity.findViewById(R.id.in_engine);
        mLock = activity.findViewById(R.id.in_key);
        povv = activity.findViewById(R.id.in_left);
        povv2 = activity.findViewById(R.id.in_right);


        turnLeft = activity.findViewById(R.id.turn_left_image);
        turnLeft = activity.findViewById(R.id.turn_right_image);
        carHP = activity.findViewById(R.id.carHPimage);
        carFuel = activity.findViewById(R.id.fuel_image);

        //speeds
        sp0 = activity.findViewById(R.id.speed_0);
        sp20 = activity.findViewById(R.id.speed_1);
        sp40 = activity.findViewById(R.id.speed_2);
        sp60 = activity.findViewById(R.id.speed_3);
        sp80 = activity.findViewById(R.id.speed_4);
        sp100 = activity.findViewById(R.id.speed_5);
        sp120 = activity.findViewById(R.id.speed_6);
        sp140 = activity.findViewById(R.id.speed_7);
        sp160 = activity.findViewById(R.id.speed_8);
        sp180 = activity.findViewById(R.id.speed_0);
        sp200 = activity.findViewById(R.id.speed_10);


        mStrela.setOnClickListener( view -> {
            if (Pov == 0)
            {
                view.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.button_click));
                sendClick(0);
                povv.setColorFilter(Color.parseColor("#00FF00"), PorterDuff.Mode.SRC_IN);
            }else{
                view.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.button_click));
                sendClick(0);
                povv.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_IN);
            }
        });
        mStrela2.setOnClickListener( view -> {
            if (Pov2 == 0)
            {
                view.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.button_click));
                sendClick(0);
                povv2.setColorFilter(Color.parseColor("#00FF00"), PorterDuff.Mode.SRC_IN);
            }else{
                view.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.button_click));
                sendClick(0);
                povv2.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_IN);
            }
        });
        Utils.HideLayout(relativeLayout, false);
    }

    public void UpdateSpeedInfo(int speed, int fuel, int hp, int mileage, int engine, int light, int belt, int lock){
        hp = (int) hp/10;
        mFuel.setText(new Formatter().format("%d", Integer.valueOf(fuel)).toString());
        mMileage.setText(new Formatter().format("%06d", Integer.valueOf(mileage)).toString());
        mCarHP.setText(new Formatter().format("%d%s", Integer.valueOf(hp), "%").toString());
        //mSpeedLine.setProgressMax(1000); )float) ((int) speed)
       // mSpeedLine.setProgress(prog);
        mSpeed.setText(String.valueOf(speed));

        if (hp == 100)
        {
            carHP.setColorFilter(Color.parseColor("#4CAF50"), PorterDuff.Mode.SRC_IN);
        }
        if (hp < 80){
            carHP.setColorFilter(Color.parseColor("#75AF4C"), PorterDuff.Mode.SRC_IN);
        }
        if (hp < 60){
            carHP.setColorFilter(Color.parseColor("#DA5517"), PorterDuff.Mode.SRC_IN);
        }
        if (hp < 35){
            carHP.setColorFilter(Color.parseColor("#F44336"), PorterDuff.Mode.SRC_IN);
        }

        if(speed == 0)
            ResetSpeedNumbers();
            sp0.setAlpha((float) 1.0);
            mSpeed.setAlpha((float) 0.3);
        if(speed > 1){
            ResetSpeedNumbers();
            sp0.setAlpha((float) 0.3);
            sp20.setAlpha((float) 1.0);
            mSpeed.setAlpha((float) 1.0);
        }
        if(speed > 20){
            ResetSpeedNumbers();
            sp20.setAlpha((float) 0.3);
            sp40.setAlpha((float) 1.0);
        }
        if(speed > 40){
            ResetSpeedNumbers();
            sp40.setAlpha((float) 0.3);
            sp60.setAlpha((float) 1.0);
        }
        if(speed > 60){
            ResetSpeedNumbers();
            sp60.setAlpha((float) 0.3);
            sp80.setAlpha((float) 1.0);
        }
        if(speed > 80){
            ResetSpeedNumbers();
            sp80.setAlpha((float) 0.3);
            sp100.setAlpha((float) 1.0);
        }
        if(speed > 100){
            ResetSpeedNumbers();
            sp100.setAlpha((float) 0.3);
            sp120.setAlpha((float) 1.0);
        }
        if(speed > 120){
            ResetSpeedNumbers();
            sp120.setAlpha((float) 0.3);
            sp140.setAlpha((float) 1.0);
        }
        if(speed > 140){
            ResetSpeedNumbers();
            sp140.setAlpha((float) 0.3);
            sp160.setAlpha((float) 1.0);
        }
        if(speed > 160){
            ResetSpeedNumbers();
            sp160.setAlpha((float) 0.3);
            sp180.setAlpha((float) 1.0);
        }
        if(speed > 180){
            ResetSpeedNumbers();
            sp180.setAlpha((float) 0.3);
            sp200.setAlpha((float) 1.0);
        }


        if(engine == 1)
            mEngine.setColorFilter(Color.parseColor("#00FF00"), PorterDuff.Mode.SRC_IN);
        else
            mEngine.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_IN);
        if(lock == 1)
            mLock.setColorFilter(Color.parseColor("#00FF00"), PorterDuff.Mode.SRC_IN);
        else
            mLock.setColorFilter(Color.parseColor("#FF0000"), PorterDuff.Mode.SRC_IN);
    }

    public void ResetSpeedNumbers() {
        sp0.setAlpha((float) 0.3);
        sp20.setAlpha((float) 0.3);
        sp40.setAlpha((float) 0.3);
        sp60.setAlpha((float) 0.3);
        sp80.setAlpha((float) 0.3);
        sp100.setAlpha((float) 0.3);
        sp120.setAlpha((float) 0.3);
        sp140.setAlpha((float) 0.3);
        sp160.setAlpha((float) 0.3);
        sp180.setAlpha((float) 0.3);
        sp200.setAlpha((float) 0.3);
    }

    public void ShowSpeed() {
        Utils.ShowLayout(mInputLayout, false);
    }

    public void HideSpeed() {
        Utils.HideLayout(mInputLayout, false);
    }
}