package org.kscy.db.kscydatabase.activity;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.kscy.db.kscydatabase.R;
import org.kscy.db.kscydatabase.model.AuthResult;
import org.kscy.db.kscydatabase.module.Auth;

import java.util.ArrayList;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private final static String TAG = "LoginActivity";

    private ProgressDialog loginDialog;

    @BindView(R.id.login_btn) CircularProgressButton login_btn;
    @BindView(R.id.edit_id) EditText edit_id;
    @BindView(R.id.edit_pw) EditText edit_pw;
    @BindView(R.id.auto_login) CheckBox auto_login;
    @BindView(R.id.login_background) ImageView login_background;

    @BindString(R.string.api_url) String api_url;
    @BindString(R.string.login_failed) String login_failed;
    @BindString(R.string.close_app) String close_app;
    @BindString(R.string.confirm) String confirm;
    @BindString(R.string.cancel) String cancel;
    @BindString(R.string.progress_login) String progress_login;

    @OnClick(R.id.login_btn) void onLoginClicked(View view) {
        if(edit_id.getText().toString().equals("")) {
            Snackbar.make(findViewById(R.id.activity_login), "ID를 입력해주세요.", Snackbar.LENGTH_SHORT).show();
        } else if(edit_pw.getText().toString().equals("")) {
            Snackbar.make(findViewById(R.id.activity_login), "PW를 입력해주세요.", Snackbar.LENGTH_SHORT).show();
        } else {
            login_btn.startAnimation();
            login(edit_id.getText().toString(), edit_pw.getText().toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);

        init();

        requestPermission();
        checkAutoLogin();
    }

    private void init() {
        Glide.with(this).load(R.drawable.login_main).into(login_background);
    }

    private void requestPermission() {
        PermissionListener permissionlistener = new PermissionListener() {
            @Override
            public void onPermissionGranted() {
            }

            @Override
            public void onPermissionDenied(ArrayList<String> deniedPermissions) {
            }
        };

        new TedPermission(this)
                .setPermissionListener(permissionlistener)
                .setDeniedMessage("If you reject permission,you can not use this service\n\nPlease turn on permissions at [Setting] > [Permission]")
                .setPermissions(Manifest.permission.INTERNET)
                .check();
    }

    void checkAutoLogin() {
        SharedPreferences sp = getSharedPreferences(getPackageName(), MODE_PRIVATE);

        String email = sp.getString("email", null);
        String password = sp.getString("password", null);

        if(email != null && password != null) {
            loginDialog = new ProgressDialog(LoginActivity.this);
            loginDialog.setMessage(progress_login);
            loginDialog.show();
            login(email, password);
        }
    }

    void login(final String email, final String password) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(api_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Auth auth = retrofit.create(Auth.class);

        Call<AuthResult> call = auth.auth(email, password);

        call.enqueue(new Callback<AuthResult>() {
            @Override
            public void onResponse(Call<AuthResult> call, Response<AuthResult> response) {
                AuthResult result = response.body();

                Log.w(TAG, result.toString());

                if(result.getCode().equals("200") && result.getStatus().equals("success")) {
                    if (loginDialog != null && loginDialog.isShowing())
                        loginDialog.dismiss();

                    if (auto_login.isChecked()) {
                        SharedPreferences sp = getSharedPreferences(getPackageName(), MODE_PRIVATE);
                        SharedPreferences.Editor edit = sp.edit();

                        edit.putString("email", email);
                        edit.putString("password", password);
                        edit.putString("token", result.getData().getToken());

                        edit.commit();
                    }

                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                } else {
                    Snackbar.make(getWindow().getDecorView().getRootView(), login_failed, Snackbar.LENGTH_LONG).show();
                    login_btn.revertAnimation();
                }
            }

            @Override
            public void onFailure(Call<AuthResult> call, Throwable t) {
                Snackbar.make(getWindow().getDecorView().getRootView(), login_failed, Snackbar.LENGTH_LONG).show();
                t.printStackTrace();
                login_btn.revertAnimation();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
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
