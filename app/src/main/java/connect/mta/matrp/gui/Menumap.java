package connect.mta.matrp.gui;

import android.app.Activity;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;

import connect.mta.matrp.R;
import connect.mta.matrp.gui.util.Utils;

public class Menumap {
    public ConstraintLayout main;
    public TextView counttext;

    public ImageView hud;

    public AppCompatButton go;
    public CountUpTimer countUpTimer;

    public Menumap(Activity aactivity) {
        main = aactivity.findViewById(R.id.main_wait);
        //hud = aactivity.findViewById(R.id.radar_hude);
        hide();
        counttext = aactivity.findViewById(R.id.schetchik);
        counttext.setText(String.valueOf(counttext));


        go = aactivity.findViewById(R.id.button_playgo);
        go.setOnClickListener(view -> {
            hide();
        });
    }

    public void show() {

        Utils.ShowLayout(main, true);
        startTimer();
    }

    public void hide() {
        Utils.HideLayout(main, true);
        stopTimer();
    }

    private void startTimer() {
        countUpTimer = new CountUpTimer(counttext);
        countUpTimer.start();
    }

    private void stopTimer() {
        if (countUpTimer != null) {
            countUpTimer.stop();
            counttext.setText("00:00");
            // counttext.setGravity(View.TEXT_ALIGNMENT_CENTER);
        }
    }

    // Внутренний класс для реализации счетчика времени
    private class CountUpTimer {
        private TextView textView;
        private Handler handler;
        private int seconds;

        public CountUpTimer(TextView textView) {
            this.textView = textView;
            this.seconds = 0;
            this.handler = new Handler();
        }

        public void start() {
            handler.postDelayed(timerRunnable, 1000);
        }

        public void stop() {
            handler.removeCallbacks(timerRunnable);
        }

        private Runnable timerRunnable = new Runnable() {
            @Override
            public void run() {
                seconds++;
                updateTimer();
                handler.postDelayed(this, 1000);
            }
        };

        private void updateTimer() {
            int minutes = seconds / 60;
            int remainingSeconds = seconds % 60;
            String time = String.format("%02d:%02d", minutes, remainingSeconds);
            textView.setText(time);
        }
    }
}
