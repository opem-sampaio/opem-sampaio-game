package connect.mta.matrp.gui;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

import connect.mta.matrp.R;
import connect.mta.matrp.gui.util.Utils;

public class Notification {
    private Activity activity;
    private LinearLayout notificationContainer;
    private int type, duration;
    private String text;
    private Timer timer;
    private MediaPlayer mediaPlayer;

    public Notification(Activity activity) {
        this.activity = activity;
        this.notificationContainer = activity.findViewById(R.id.bg_nots);
        Utils.HideLayout(notificationContainer, false);
        timer = new Timer();
        mediaPlayer = MediaPlayer.create(activity, R.raw.sound);
    }

    public void ShowNotification(int type, String text, int duration, String actionforBtn, String textBtn) {
        Utils.HideLayout(notificationContainer, false);
        clearData();

        this.type = type;
        this.text = text;
        this.duration = duration;

        addNotification(text);

        final View notificationView = notificationContainer.getChildAt(notificationContainer.getChildCount() - 1);
        final ImageView png_noty_type = notificationView.findViewById(R.id.png_noty_type);
        final TextView title = notificationView.findViewById(R.id.title);
        final ProgressBar customProgressBar = notificationView.findViewById(R.id.customProgressBar);

        switch (this.type) {
            case 0:
                title.setText("Error");
                png_noty_type.setImageResource(R.drawable.ic_noty_type_error);
                customProgressBar.setProgressDrawable(activity.getResources().getDrawable(R.drawable.progress_noty_error));
                break;
            case 1:
                title.setText("Sucesso");
                png_noty_type.setImageResource(R.drawable.ic_noty_type_success);
                customProgressBar.setProgressDrawable(activity.getResources().getDrawable(R.drawable.progress_noty_success));
                break;
            default:
                // Lida com outros tipos, se necessário
                break;
        }

        playSound();

        Utils.ShowLayout(notificationContainer, true);
    }

    private void addNotification(final String message) {
        LayoutInflater inflater = LayoutInflater.from(activity);
        final View notificationView = inflater.inflate(R.layout.not, null);

        final ProgressBar customProgressBar = notificationView.findViewById(R.id.customProgressBar);
        final TextView notificationText = notificationView.findViewById(R.id.notification_text);
        notificationText.setText(formatMessage(message));

        Animation slideInAnimation = AnimationUtils.loadAnimation(activity, R.anim.slide_in_left_noty);
        notificationView.startAnimation(slideInAnimation);

        notificationContainer.addView(notificationView, notificationContainer.getChildCount());

        final int duration = 500;
        final int initialProgress = 100;

        final Timer localTimer = new Timer();
        localTimer.scheduleAtFixedRate(new TimerTask() {
            int progress = initialProgress;

            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (progress > 0) {
                            progress -= 1;
                            customProgressBar.setProgress(progress);
                        } else {
                            localTimer.cancel();

                            Animation slidebackLeft = AnimationUtils.loadAnimation(activity, R.anim.slide_back_left_noty);
                            notificationView.startAnimation(slidebackLeft);

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    notificationContainer.removeView(notificationView);
                                }
                            }, duration);
                        }
                    }
                });
            }
        }, 0, 50);
    }

    private void playSound() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(activity, R.raw.sound);
        }
        mediaPlayer.start();
    }

    private String formatMessage(String message) {
        int maxLength = 40;
        if (message.length() > maxLength) {
            StringBuilder formatted = new StringBuilder();
            int start = 0;
            while (start < message.length()) {
                int end = Math.min(start + maxLength, message.length());
                formatted.append(message, start, end).append("\n");
                start += maxLength;
            }
            return formatted.toString().trim();
        } else {
            return message + " ".repeat(maxLength - message.length());
        }
    }

    private void clearData() {
        this.text = "";
        this.type = -1;
        this.duration = -1;
    }

    public void HideNotification() {
        notificationContainer.setVisibility(View.GONE);
    }
}
