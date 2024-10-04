package connect.mta.matrp.gui;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nvidia.devtech.NvEventQueueActivity;

import java.util.Formatter;

import connect.mta.launcher.MainActivity;
import connect.mta.matrp.R;
import connect.mta.matrp.gui.util.Utils;

public class HudManager {
    public Activity activity;
    public FrameLayout hudLayout;
    public TextView weaponAmmoText;
    public ImageView hudWeapon;

    public ImageView vida;
    public ImageView fome;
    public ImageView sede;

    public LinearLayout progressBarContainer;
    public ProgressBar progressbarVida;
    public ProgressBar progressbarFome;
    public ProgressBar progressbarSede;

    public View icon1;
    public View icon2;
    public View icon3;
    public View icon4;
    public View icon5;
    public View icon6;
    public View icon7;
    public View icon8;
    public View icon9;
    public View icon10;

    public HudManager(Activity activity) {
        this.activity = activity;
        hudLayout = activity.findViewById(R.id.hud_main);
        weaponAmmoText = activity.findViewById(R.id.weapon_text);
        hudWeapon = activity.findViewById(R.id.fist_icon);

        progressBarContainer = activity.findViewById(R.id.progressBarContainer);
        progressbarSede = activity.findViewById(R.id.progressbar_sede);
        progressbarVida = activity.findViewById(R.id.progressbar_vida);
        progressbarFome = activity.findViewById(R.id.progressbar_fome);

        sede = activity.findViewById(R.id.sede);
        vida = activity.findViewById(R.id.vida);
        fome = activity.findViewById(R.id.fome);

        icon1 = activity.findViewById(R.id.icon_1);
        icon2 = activity.findViewById(R.id.icon_2);
        icon3 = activity.findViewById(R.id.icon_3);
        icon4 = activity.findViewById(R.id.icon_4);
        icon5 = activity.findViewById(R.id.icon_5);
        icon6 = activity.findViewById(R.id.icon_6);
        icon7 = activity.findViewById(R.id.icon_7);
        icon8 = activity.findViewById(R.id.icon_8);
        icon9 = activity.findViewById(R.id.icon_9);
        icon10 = activity.findViewById(R.id.icon_10);

        fome.setImageResource(R.drawable.ic_fome);
        vida.setImageResource(R.drawable.ic_vida);
        sede.setImageResource(R.drawable.ic_sede);

        progressbarSede.setProgress(90);
        progressbarVida.setProgress(100);
        progressbarFome.setProgress(60);
    }

    private void updateProgress(int progress) {
        int totalSegments = progressBarContainer.getChildCount();
        int filledSegments = (totalSegments * progress) / 100;
        for (int i = 0; i < totalSegments; i++) {
            View segment = progressBarContainer.getChildAt(i);
            if (i < filledSegments) {
                segment.setBackgroundResource(R.drawable.barra_vida);
            } else {
                segment.setBackgroundResource(R.drawable.background_progress_vida);
            }
        }
    }
    
    public void UpdateHudInfo(int health, int armour, int hunger, int weaponidweik, int ammo, int playerid, int money, int wanted)
    {
        updateProgress(health);
        weaponAmmoText.setText(String.valueOf(armour));            
        int id = activity.getResources().getIdentifier(new Formatter().format("weapon_%d", Integer.valueOf(weaponidweik)).toString(), "drawable", activity.getPackageName());
        hudWeapon.setImageResource(id);
        hudWeapon.setOnClickListener(v -> NvEventQueueActivity.getInstance().onWeaponChanged());

    }
    
    public void ShowGps()
    {

    }

    public void HideGps()
    {

    }

    public void ShowX2()
    {

    }

    public void HideX2()
    {

    }

    public void ShowZona()
    {

    }

    public void HideZona()
    {

    }

    public void ShowRadar()
    {

    }

    public void HideRadar()
    {

    }
    
    public void ShowLogin() {
      
    }
    
    public void HideLogin() { 
      
    }

    public void ShowHud() {
        Utils.ShowLayout(hudLayout, true);
    }

    public void HideHud()
    {
        Utils.HideLayout(hudLayout, true);       
    }
}
