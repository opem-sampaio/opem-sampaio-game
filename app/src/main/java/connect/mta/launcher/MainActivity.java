package connect.mta.launcher;

import android.Manifest;
import android.graphics.drawable.*;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import connect.mta.matrp.R;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private Timer timer;
    private File settingsFile;
    private LinearLayout bg_connectar;
    private LinearLayout bg_login;
    private LinearLayout linear1;
    private LinearLayout linear2;
    private LinearLayout linear17;
    private ImageView imageview1;
    private LinearLayout bg_opcoes;
    private TextView textview26;
    private TextView textview23;
    private TextView textview25;
    private TextView textview24;
    private LinearLayout bg_item_servers;
    private LinearLayout linear23;
    private LinearLayout linear22;
    private ImageView bt_play2;
    private TextView bt_play;
    private LinearLayout bg_apoiador;
    private LinearLayout bg_historixo;
    private ImageView imageview2;
    private TextView textview27;
    private ImageView imageview4;
    private TextView textview31;
    private TextView textview32;
    private LinearLayout bg_topserver;
    private LinearLayout linear47;
    private LinearLayout linear31;
    private LinearLayout linear43;
    private LinearLayout linear44;
    private ImageView imageview16;
    private LinearLayout linear49;
    private LinearLayout linear46;
    private ImageView imageview19;
    private TextView player;
    private ImageView imageview17;
    private TextView textview35;
    private TextView name;
    private TextView descricao;
    private LinearLayout bg_connectmta;
    private LinearLayout linear33;
    private LinearLayout linear34;
    private LinearLayout linear35;
    private LinearLayout linear37;
    private LinearLayout linear38;
    private LinearLayout linear40;
    private ImageView imageview6;
    private TextView bg_mta;
    private ImageView imageview11;
    private ImageView imageview7;
    private ImageView imageview10;
    private ImageView imageview9;
    private ImageView imageview13;
    private ImageView imageview14;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bg_login = findViewById(R.id.bg_login);
        linear1 = findViewById(R.id.linear1);
        linear2 = findViewById(R.id.linear2);
        linear17 = findViewById(R.id.linear17);
        imageview1 = findViewById(R.id.imageview1);
        bg_opcoes = findViewById(R.id.bg_opcoes);
        textview26 = findViewById(R.id.textview26);
        textview23 = findViewById(R.id.textview23);
        textview25 = findViewById(R.id.textview25);
        textview24 = findViewById(R.id.textview24);
        bg_item_servers = findViewById(R.id.bg_item_servers);
        linear23 = findViewById(R.id.linear23);
        linear22 = findViewById(R.id.linear22);
        bt_play2 = findViewById(R.id.bt_play2);
        bt_play = findViewById(R.id.bt_play);
        bg_apoiador = findViewById(R.id.bg_apoiador);
        bg_historixo = findViewById(R.id.bg_historixo);
        imageview2 = findViewById(R.id.imageview2);
        textview27 = findViewById(R.id.textview27);
        imageview4 = findViewById(R.id.imageview4);
        textview31 = findViewById(R.id.textview31);
        textview32 = findViewById(R.id.textview32);
        bg_topserver = findViewById(R.id.bg_topserver);
        linear47 = findViewById(R.id.linear47);
        linear31 = findViewById(R.id.linear31);
        linear43 = findViewById(R.id.linear43);
        linear44 = findViewById(R.id.linear44);
        imageview16 = findViewById(R.id.imageview16);
        linear49 = findViewById(R.id.linear49);
        linear46 = findViewById(R.id.linear46);
        imageview19 = findViewById(R.id.imageview19);
        player = findViewById(R.id.player);
        bg_connectar = findViewById(R.id.bg_connectar);
        imageview17 = findViewById(R.id.imageview17);
        textview35 = findViewById(R.id.textview35);
        name = findViewById(R.id.name);
        descricao = findViewById(R.id.descricao);
        bg_connectmta = findViewById(R.id.bg_connectmta);
        linear33 = findViewById(R.id.linear33);
        linear34 = findViewById(R.id.linear34);
        linear35 = findViewById(R.id.linear35);
        linear37 = findViewById(R.id.linear37);
        linear38 = findViewById(R.id.linear38);
        linear40 = findViewById(R.id.linear40);
        imageview6 = findViewById(R.id.imageview6);
        bg_mta = findViewById(R.id.bg_mta);
        imageview11 = findViewById(R.id.imageview11);
        imageview7 = findViewById(R.id.imageview7);
        imageview10 = findViewById(R.id.imageview10);
        imageview9 = findViewById(R.id.imageview9);
        imageview13 = findViewById(R.id.imageview13);
        imageview14 = findViewById(R.id.imageview14);

        bg_item_servers.setBackground(new GradientDrawable() {
            public GradientDrawable getIns(int a, int b) {
                this.setCornerRadius(a);
                this.setColor(b);
                return this;
            }
        }.getIns((int) 10, 0xCC111D24));
        bg_apoiador.setBackground(new GradientDrawable() {
            public GradientDrawable getIns(int a, int b) {
                this.setCornerRadius(a);
                this.setColor(b);
                return this;
            }
        }.getIns((int) 10, 0xCC111D24));
        bg_historixo.setBackground(new GradientDrawable() {
            public GradientDrawable getIns(int a, int b) {
                this.setCornerRadius(a);
                this.setColor(b);
                return this;
            }
        }.getIns((int) 10, 0xCC111D24));
        bg_connectmta.setBackground(new GradientDrawable() {
            public GradientDrawable getIns(int a, int b) {
                this.setCornerRadius(a);
                this.setColor(b);
                return this;
            }
        }.getIns((int) 10, 0xCC111D24));
        bg_mta.setBackground(new GradientDrawable() {
            public GradientDrawable getIns(int a, int b) {
                this.setCornerRadius(a);
                this.setColor(b);
                return this;
            }
        }.getIns((int) 10, 0xF12A2935));
        bg_topserver.setBackground(new GradientDrawable() {
            public GradientDrawable getIns(int a, int b) {
                this.setCornerRadius(a);
                this.setColor(b);
                return this;
            }
        }.getIns((int) 10, 0xCC111D24));
        bg_connectar.setBackground(new GradientDrawable() {
            public GradientDrawable getIns(int a, int b) {
                this.setCornerRadius(a);
                this.setColor(b);
                return this;
            }
        }.getIns((int) 10, 0xFFE91E63));

        settingsFile = new File(Environment.getExternalStorageDirectory() + "/.ConnectMTA/SAMP/localsettings.ini");

        // Verificar e solicitar permissões necessárias
        checkPermissions();

        // Configurar botão "play"
        bt_play.setOnClickListener(v -> startGame());
    }

    private void checkPermissions() {
        // Verificar se as permissões estão concedidas
        if (Build.VERSION.SDK_INT >= 23) {
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                    || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED
                    || checkSelfPermission(Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {
                // Solicitar permissões
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.RECORD_AUDIO}, 1000);
            }
        }
    }
    
    private void deleteAppData() {
    File dir = getFilesDir();
    if (deleteDir(dir)) {
        //Toast.makeText(this, "Dados do aplicativo excluídos com sucesso.", Toast.LENGTH_SHORT).show();
    } else {
        //Toast.makeText(this, "Falha ao excluir os dados do aplicativo.", Toast.LENGTH_SHORT).show();
    }
}

private boolean deleteDir(File dir) {
    if (dir != null && dir.isDirectory()) {
        String[] children = dir.list();
        for (String child : children) {
            boolean success = deleteDir(new File(dir, child));
            if (!success) {
                return false;
            }
        }
    }
    return dir.delete();
}



    private void startGame() {
        if (isGameInstalled()) {
            // Preparar o jogo
            prepareGame();
        } else {
        Toast.makeText(getApplicationContext(), "Instale o jogo primeiro.", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean isGameInstalled() {
        String checkFilePath = Environment.getExternalStorageDirectory() + "/.ConnectMTA/texdb/gta3.img";
        File gameFile = new File(checkFilePath);
        return gameFile.exists();
    }

    private void prepareGame() {
        // Código para preparar o jogo
        deleteAppData();
        startActivity(new Intent(getApplicationContext(), connect.mta.matrp.core.GTASA.class));
        // Aqui você deve iniciar a atividade principal do jogo após a preparação necessária
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1000) {
            boolean allPermissionsGranted = true;
            for (int result : grantResults) {
                if (result == PackageManager.PERMISSION_DENIED) {
                    allPermissionsGranted = false;
                    break;
                }
            }
            if (allPermissionsGranted) {
                // Permissões concedidas, nada a fazer
            } else {
                Toast.makeText(this, "Permissões são necessárias para iniciar o jogo.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (timer != null) {
            timer.cancel();
        }
    }
}
