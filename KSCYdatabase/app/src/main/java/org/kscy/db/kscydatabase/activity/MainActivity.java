package org.kscy.db.kscydatabase.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.yalantis.guillotine.animation.GuillotineAnimation;

import org.kscy.db.kscydatabase.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private final static String TAG = "MainActivity";
    private static final long RIPPLE_DURATION = 250;
    private View guillotineMenu;
    private GuillotineAnimation guillotineAnimation;

    @BindView(R.id.root_main) FrameLayout root;
    @BindView(R.id.toolbar) RelativeLayout toolbar;
    @BindView(R.id.content_hamburger) ImageView contentHamburger;
    @BindView(R.id.content_search) ImageView search_btn;

    @BindString(R.string.logout_alert) String logout;
    @BindString(R.string.confirm) String confirm;
    @BindString(R.string.cancel) String cancel;
    @BindString(R.string.close_app) String close_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        guillotineMenu = LayoutInflater.from(this).inflate(R.layout.guillotine, null);
        root.addView(guillotineMenu);

        guillotineAnimation = new GuillotineAnimation.GuillotineBuilder(guillotineMenu, guillotineMenu.findViewById(R.id.guillotine_hamburger), contentHamburger)
                .setStartDelay(RIPPLE_DURATION)
                .setActionBarViewForAnimation(toolbar)
                .setClosedOnStart(true)
                .build();
//
//        guillotineMenu.findViewById(R.id.share_group).setOnClickListener(this);
//        guillotineMenu.findViewById(R.id.library_group).setOnClickListener(this);
//        guillotineMenu.findViewById(R.id.mybook_group).setOnClickListener(this);
//        guillotineMenu.findViewById(R.id.logout_group).setOnClickListener(this);
//        guillotineMenu.findViewById(R.id.settings_group).setOnClickListener(this);
//
//        getSupportFragmentManager().beginTransaction().replace(R.id.content, new ShareFragment()).commit();
    }

    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(logout)
                .setCancelable(false)
                .setPositiveButton(confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                        SharedPreferences sp = getSharedPreferences(getPackageName(), MODE_PRIVATE);
                        SharedPreferences.Editor edit = sp.edit();

                        edit.putString("email", null);
                        edit.putString("password", null);

                        edit.commit();

                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
                        finish();
                    }
                })
                .setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(close_app)
                .setCancelable(false)
                .setPositiveButton(confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        System.exit(0);
                    }
                })
                .setNegativeButton(cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
