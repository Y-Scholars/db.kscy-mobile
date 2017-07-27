package org.kscy.db.kscydatabase.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yalantis.guillotine.animation.GuillotineAnimation;

import org.kscy.db.kscydatabase.R;
import org.kscy.db.kscydatabase.fragment.BookmarkFragment;
import org.kscy.db.kscydatabase.fragment.ProfileFragment;
import org.kscy.db.kscydatabase.fragment.SearchFragment;
import org.kscy.db.kscydatabase.fragment.SettingFragment;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String TAG = "MainActivity";
    private static final long RIPPLE_DURATION = 250;
    private boolean check = false;
    private View guillotineMenu;
    private GuillotineAnimation guillotineAnimation;

    @BindView(R.id.root_main) FrameLayout root;
    @BindView(R.id.toolbar) RelativeLayout toolbar;
    @BindView(R.id.content_hamburger) ImageView contentHamburger;
    @BindView(R.id.content_search) ImageView search_btn;
    @BindView(R.id.actionbar_title) TextView actionbar_title;
    @BindView(R.id.search_edit) EditText search_edit;

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

        guillotineMenu.findViewById(R.id.search_group).setOnClickListener(this);
        guillotineMenu.findViewById(R.id.bookmark_group).setOnClickListener(this);
        guillotineMenu.findViewById(R.id.profile_group).setOnClickListener(this);
        guillotineMenu.findViewById(R.id.logout_group).setOnClickListener(this);
//        guillotineMenu.findViewById(R.id.settings_group).setOnClickListener(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.content, SearchFragment.newInstance()).commit();

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search_edit.setVisibility(View.VISIBLE);
                actionbar_title.setVisibility(View.INVISIBLE);

                search_edit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                        switch (i) {
                            case EditorInfo.IME_ACTION_SEARCH:
                                if(!search_edit.getText().toString().equals("")) {
                                    SearchFragment fragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.content);
                                    fragment.getList(search_edit.getText().toString());
                                }
                                break;
                            default:
                                return false;
                        }
                        return true;
                    }
                });

                if(!search_edit.getText().toString().equals("") && check) {
                    SearchFragment fragment = (SearchFragment) getSupportFragmentManager().findFragmentById(R.id.content);
                    fragment.getList(search_edit.getText().toString());
                }
                check = true;
            }
        });
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

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.search_group :
                search_btn.setVisibility(View.VISIBLE);
                guillotineAnimation.close();
                getSupportFragmentManager().beginTransaction().replace(R.id.content, SearchFragment.newInstance()).commit();
                break;
            case R.id.bookmark_group :
                check = false;
                search_edit.setVisibility(View.INVISIBLE);
                actionbar_title.setVisibility(View.VISIBLE);
                search_btn.setVisibility(View.GONE);
                guillotineAnimation.close();
                getSupportFragmentManager().beginTransaction().replace(R.id.content, BookmarkFragment.newInstance()).commit();
                break;
            case R.id.profile_group :
                check = false;
                search_edit.setVisibility(View.INVISIBLE);
                actionbar_title.setVisibility(View.VISIBLE);
                search_btn.setVisibility(View.GONE);
                guillotineAnimation.close();
                getSupportFragmentManager().beginTransaction().replace(R.id.content, ProfileFragment.newInstance()).commit();
                break;
            case R.id.logout_group :
                check = false;
                search_edit.setVisibility(View.INVISIBLE);
                actionbar_title.setVisibility(View.VISIBLE);
                search_btn.setVisibility(View.GONE);
                logout();
                break;
//            case R.id.settings_group :
//                check = false;
//                search_edit.setVisibility(View.INVISIBLE);
//                actionbar_title.setVisibility(View.VISIBLE);
//                search_btn.setVisibility(View.GONE);
//                guillotineAnimation.close();
//                getSupportFragmentManager().beginTransaction().replace(R.id.content, SettingFragment.newInstance()).commit();
//                break;
        }
    }
}
