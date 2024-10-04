package connect.mta.matrp.gui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.nvidia.devtech.NvEventQueueActivity;


import org.ini4j.Wini;

import java.io.File;
import java.io.IOException;

import connect.mta.matrp.R;
import connect.mta.matrp.gui.util.Utils;

public class ChooseServer {
    
    private FrameLayout carregando;
    private VideoView videoView1;
    private LinearLayout linear4;
    private LinearLayout linear2;
    private TextView statusTextView;
    private ProgressBar progressbar1;
    private Activity activity;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private String host;
    private int port;
    int i = 0;

    public ChooseServer(Activity activity) {
        this.activity = activity;
       // this.bg = activity.findViewById(R.id.bg);
        this.videoView1 = activity.findViewById(R.id.videoView1);
        this.linear4 = activity.findViewById(R.id.linear4);
        this.linear2 = activity.findViewById(R.id.linear2);
        this.statusTextView = activity.findViewById(R.id.textview1);
        this.progressbar1 = activity.findViewById(R.id.progressbar1);
        this.carregando = activity.findViewById(R.id.carregamento);
        Utils.HideLayout(carregando, false);
        loadServerSettings();
        OnCreate();
    }

    private void OnCreate() {
        String videoPath = "android.resource://" + activity.getPackageName() + "/" + R.raw.video;
        Uri uri = Uri.parse(videoPath);
        videoView1.setVideoURI(uri);
        videoView1.setMediaController(null);
        videoView1.start();
        videoView1.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setVolume(1.0f, 1.0f); // Volume normal
                mp.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                    @Override
                    public void onBufferingUpdate(MediaPlayer mp1, int percent) {
                        // Faz nada
                    }
                });
                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp1) {
                        Toast.makeText(activity.getApplicationContext(), "O vídeo terminou!", Toast.LENGTH_SHORT).show();
                    }
                });
                mp.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                    @Override
                    public boolean onInfo(MediaPlayer mp1, int what, int extra) {
                        return true; // Prevenir controles de mídia
                    }
                });
            }
        });

        videoView1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });
    }

    private void loadServerSettings() {
        try {
            File iniFile = new File(Environment.getExternalStorageDirectory() + "/.ConnectMTA/SAMP/localsettings.ini");
            Wini ini = new Wini(iniFile);
            this.host = ini.get("server", "host");
            this.port = Integer.parseInt(ini.get("server", "port"));
        } catch (IOException e) {
            Log.e("ChooseServer", "Failed to load server settings", e);
            showToast("Falha ao carregar configurações do servidor");
        }
    }

    public void Update(int percent, int pon) {
        i = pon;
        if (percent <= 100) {
            statusTextView.setText("Carregando o jogo...");
        } else {
            statusTextView.setText("Conectando ao servidor...");
        }
        if (i == 2) {
            statusTextView.setText("Conectado. Preparando para o jogo...");
            mHandler.postDelayed(new HideSplashRunnable(), 500);
        }
        if (i == 3) {
            statusTextView.setText("Servidor cheio. Reconectando ao jogo...");
        }
        if (i == 4) {
            statusTextView.setText("Problemas de rede, reconectando...");
        }
        if (i == 1) {
            new NetworkTask().execute();
        }
    }

    private class NetworkTask extends AsyncTask<Void, Void, Boolean> {
        @Override
        protected void onPreExecute() {
            statusTextView.setText("Conectando ao servidor...");
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            try {
                NvEventQueueActivity.getInstance().EdgarConnect(host, port);
                return true;
            } catch (Exception e) {
                showToast("Erro ao conectar ao servidor...");
                Log.e("ChooseServer", "Erro ao conectar ao servidor", e);
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean success) {
            if (success) {
                statusTextView.setText("Conectado. Preparando para o jogo...");
                mHandler.postDelayed(new HideSplashRunnable(), 500);
            } else {
                statusTextView.setText("Erro ao conectar. Verifique sua conexão.");
                showToast("Erro ao conectar. Verifique sua conexão.");
            }
        }
    }

    private class HideSplashRunnable implements Runnable {
        @Override
        public void run() {
            carregando.animate().setDuration(400).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    carregando.setVisibility(View.GONE);
                    super.onAnimationEnd(animation);
                }
            }).alpha(0.0f);
        }
    }

    public void Show() {
        Utils.ShowLayout(carregando, false);
    }

    private void showToast(String message) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show();
    }
}
