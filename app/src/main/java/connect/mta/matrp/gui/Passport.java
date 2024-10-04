package connect.mta.matrp.gui;

import android.app.Activity;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import connect.mta.matrp.R;
import connect.mta.matrp.gui.util.Utils;

public class Passport {
    public Activity activity;
    public ConstraintLayout passport_layout;

    public TextView tnick, tpol, tvozrast;

    public static int pol, vozrast;

    public static String nick;


    public Passport(Activity aactivity) {
        activity = aactivity;
        passport_layout = activity.findViewById(R.id.passportlayout);
        setListeners();
        close();

    }

    public void setListeners() {

    }

    public void show(String nick, int pol, int vozrast) {

        Utils.ShowLayout(passport_layout, true);

        this.nick = nick;
        this.pol = pol;
        this.vozrast = vozrast;

        this.tnick.setText(this.nick);
        this.tpol.setText(this.pol);
        if (vozrast == 0){
            this.tvozrast.setText("Мужской");
        }else{
            this.tvozrast.setText("Женский");
        }

    }

    public void close() {
        Utils.HideLayout(passport_layout, true);
    }
}