package connect.mta.matrp.gui;

import android.app.Activity;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.nvidia.devtech.NvEventQueueActivity;

import java.nio.charset.StandardCharsets;

import connect.mta.matrp.R;
import connect.mta.matrp.gui.util.Utils;

public class Phone {
    public Activity activity;
    
    public ConstraintLayout phone_layout;

    public Phone(Activity aactivity) {
        activity = aactivity;
        phone_layout = activity.findViewById(R.id.phone);
        setListeners();
        close();
        hidecall();
    }

    public void setListeners() {
        
    }


    public void hidebuttons() {
        
    }
    public void showbuttons() {
        
    }

    public void showcall() {
        
    }
    public void hidecall() {
        
    }

    public void show() {

        Utils.ShowLayout(phone_layout, false);
        phone_layout.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.showphone));
    }

    public void close() {
        phone_layout.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.hidephone));
        Utils.HideLayout(phone_layout, false);
    }
}
