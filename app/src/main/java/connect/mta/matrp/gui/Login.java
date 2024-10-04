package connect.mta.matrp.gui;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import android.view.animation.Animation;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.CheckBox;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.nvidia.devtech.NvEventQueueActivity;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import org.json.JSONException;
import org.json.JSONObject;

import connect.mta.matrp.R;
import connect.mta.matrp.gui.util.Utils;

public class Login {
    public Activity activity;
    private ConstraintLayout login_layout;

    private LinearLayout item_home;
    private LinearLayout item_config;
    private LinearLayout bg_login_registro;
    private LinearLayout bg_config;
    private LinearLayout bg_registro; // Added missing declaration
    private LinearLayout bg_login; // Added missing declaration

    private CheckBox checkbox2;
    private CheckBox checkbox1;

    private EditText edtUsuario; // login
    private EditText edtSenha; // login
    private EditText edtUsuario_registro; // registro
    private EditText edtSenha_registro; // registro
    private EditText edtEmail_registro; // registro
    private LinearLayout linear_login;
    private LinearLayout linear_registrar;
    private TextView fazer_login; // login
    private TextView registrar_conta; // registro

    private static final String API_URL = "http://192.168.3.56:8080/api.php";
    private Intent intentname = new Intent();
    private SharedPreferences saveACCOUNTS; // Added missing declaration

    public Login(Activity aactivity) {
        activity = aactivity;
        login_layout = activity.findViewById(R.id.login);
        setListeners();
        close();
        inicial();
    }

    public void inicial() {
        saveACCOUNTS = activity.getSharedPreferences("saveACCOUNTS", Activity.MODE_PRIVATE); // Initialize saveACCOUNTS
        if (saveACCOUNTS.getString("usuario", "").equals("")) {

        } else {
            edtUsuario.setText(saveACCOUNTS.getString("usuario", ""));
            edtSenha.setText(saveACCOUNTS.getString("password", ""));
            checkbox1.setChecked(true);
            checkbox2.setChecked(true);
        }
    }

    public void setListeners() {
        checkbox1 = activity.findViewById(R.id.checkbox1);
        checkbox2 = activity.findViewById(R.id.checkbox2);
        bg_login_registro = activity.findViewById(R.id.bg_login_registro);
        bg_config = activity.findViewById(R.id.bg_config);
        bg_registro = activity.findViewById(R.id.bg_registro); // Initialize bg_registro
        bg_login = activity.findViewById(R.id.bg_login); // Initialize bg_login
        item_home = activity.findViewById(R.id.item_home);
        item_config = activity.findViewById(R.id.item_config);
        edtUsuario = activity.findViewById(R.id.edtUsuario); // login
        edtSenha = activity.findViewById(R.id.edtSenha); // login
        edtUsuario_registro = activity.findViewById(R.id.edtUsuario_registro); // registro
        edtSenha_registro = activity.findViewById(R.id.edtSenha_registro); // registro
        edtEmail_registro = activity.findViewById(R.id.edtEmail_registro); // registro
        linear_login = activity.findViewById(R.id.linear_login); // login
        linear_registrar = activity.findViewById(R.id.linear_registrar); // registro
        registrar_conta = activity.findViewById(R.id.registrar_conta); // registro
        fazer_login = activity.findViewById(R.id.fazer_login); // login

        fazer_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                bg_registro.setVisibility(View.GONE);
                bg_login.setVisibility(View.VISIBLE);
            }
        });

        registrar_conta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                bg_login.setVisibility(View.GONE);
                bg_registro.setVisibility(View.VISIBLE);
            }
        });

        item_config.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                item_home.setBackground(activity.getResources().getDrawable(R.drawable.invisible_borda));
                item_config.setBackground(activity.getResources().getDrawable(R.drawable.orange_borda));
                bg_login_registro.setVisibility(View.GONE);
                bg_config.setVisibility(View.VISIBLE);
                
                ScaleAnimation scaleAnimation = new ScaleAnimation(
                        1f, 0.9f, // Start and end values for the X axis scaling
                        1f, 0.9f, // Start and end values for the Y axis scaling
                        Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                        Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
                scaleAnimation.setDuration(100); // Duration in milliseconds
                scaleAnimation.setRepeatCount(1); // Repeat once (go back to original)
                scaleAnimation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end
                _view.startAnimation(scaleAnimation);
            }
        });

        item_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View _view) {
                item_home.setBackground(activity.getResources().getDrawable(R.drawable.orange_borda));
                item_config.setBackground(activity.getResources().getDrawable(R.drawable.invisible_borda));
                bg_login_registro.setVisibility(View.VISIBLE);
                bg_config.setVisibility(View.GONE);
                
                ScaleAnimation scaleAnimation = new ScaleAnimation(
                        1f, 0.9f, // Start and end values for the X axis scaling
                        1f, 0.9f, // Start and end values for the Y axis scaling
                        Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                        Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
                scaleAnimation.setDuration(100); // Duration in milliseconds
                scaleAnimation.setRepeatCount(1); // Repeat once (go back to original)
                scaleAnimation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end
                _view.startAnimation(scaleAnimation);
            }
        });

        linear_registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScaleAnimation scaleAnimation = new ScaleAnimation(
                        1f, 0.9f, // Start and end values for the X axis scaling
                        1f, 0.9f, // Start and end values for the Y axis scaling
                        Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                        Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
                scaleAnimation.setDuration(100); // Duration in milliseconds
                scaleAnimation.setRepeatCount(1); // Repeat once (go back to original)
                scaleAnimation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end

                view.startAnimation(scaleAnimation);

                if (checkbox2.isChecked()) {
                    saveACCOUNTS.edit().putString("usuario", edtUsuario_registro.getText().toString()).apply();
                    saveACCOUNTS.edit().putString("password", edtSenha_registro.getText().toString()).apply();
                } else {
                    saveACCOUNTS.edit().putString("usuario", "").apply();
                    saveACCOUNTS.edit().putString("password", "").apply();
                }

                String usuario = edtUsuario_registro.getText().toString();
                String senha = edtSenha_registro.getText().toString();
                String email = edtEmail_registro.getText().toString();
                new ApiRequest().execute("register", usuario, senha, email);
            }
        });

        linear_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ScaleAnimation scaleAnimation = new ScaleAnimation(
                        1f, 0.9f, // Start and end values for the X axis scaling
                        1f, 0.9f, // Start and end values for the Y axis scaling
                        Animation.RELATIVE_TO_SELF, 0.5f, // Pivot point of X scaling
                        Animation.RELATIVE_TO_SELF, 0.5f); // Pivot point of Y scaling
                scaleAnimation.setDuration(100); // Duration in milliseconds
                scaleAnimation.setRepeatCount(1); // Repeat once (go back to original)
                scaleAnimation.setRepeatMode(Animation.REVERSE); // Reverse animation at the end

                view.startAnimation(scaleAnimation);

                if (checkbox1.isChecked()) {
                    saveACCOUNTS.edit().putString("usuario", edtUsuario.getText().toString()).apply();
                    saveACCOUNTS.edit().putString("password", edtSenha.getText().toString()).apply();
                } else {
                    saveACCOUNTS.edit().putString("usuario", "").apply();                 
                    saveACCOUNTS.edit().putString("password", "").apply();
                }

                String usuario = edtUsuario.getText().toString();
                String senha = edtSenha.getText().toString();
                new ApiRequest().execute("login", usuario, senha, null);
            }
        });
    }

    private class ApiRequest extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String action = params[0];
            String usuario = params[1];
            String senha = params[2];
            String email = params.length > 3 ? params[3] : null;

            try {
                URL url = new URL(API_URL);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                conn.setDoOutput(true);

                String data = "action=" + action + "&usuario=" + usuario + "&senha=" + senha;
                if (email != null) {
                    data += "&email=" + email;
                }

                OutputStream os = conn.getOutputStream();
                os.write(data.getBytes(StandardCharsets.UTF_8));
                os.flush();
                os.close();

                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                return response.toString();

            } catch (Exception e) {
                Log.e("ApiRequest", "Erro ao fazer requisição", e);
                return null;
            }
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if (result != null) {
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    String message = jsonObject.getString("message");

                    switch (message) {
                        case "Login bem-sucedido.":
                            NvEventQueueActivity.getInstance().sendCommand("/haudwqnbv".getBytes(StandardCharsets.UTF_8));                            
                            close();
                            break;
                        case "Senha incorreta.":
                            NvEventQueueActivity.getInstance().sendCommand("/iqvdanaanb".getBytes(StandardCharsets.UTF_8));                       
                            break;
                        case "Usuario nao encontrado.":
                            NvEventQueueActivity.getInstance().sendCommand("/kajsnensnsn".getBytes(StandardCharsets.UTF_8));         
                            break;
                        case "Usuario ja registrado.":
                            NvEventQueueActivity.getInstance().sendCommand("/nsjsnsudbsh".getBytes(StandardCharsets.UTF_8));         
                            break;
                        case "Registro bem sucedido.":
                            NvEventQueueActivity.getInstance().sendCommand("/iwjwmwjwnu".getBytes(StandardCharsets.UTF_8));         
                            close();
                            break;
                        default:
                            Toast.makeText(activity, "Resposta desconhecida da API.", Toast.LENGTH_SHORT).show();
                            break;
                    }
                } catch (JSONException e) {
                    Log.e("ApiRequest", "Erro ao processar resposta JSON", e);
                    Toast.makeText(activity, "Erro ao processar resposta da API, Entre em contato com o desenvolvedor.", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(activity, "Erro ao conectar com a API Entre em contato com o desenvolvedor.", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void show() {
        Utils.ShowLayout(login_layout, false);
    }

    public void close() {
        Utils.HideLayout(login_layout, false);
    }
}
