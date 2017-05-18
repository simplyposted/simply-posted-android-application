package com.qedum.simplyposted.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qedum.simplyposted.R;
import com.qedum.simplyposted.api.ApiCallback;
import com.qedum.simplyposted.api.ApiClient;
import com.qedum.simplyposted.model.api.LoginResponse;
import com.qedum.simplyposted.model.api.UserResponse;
import com.qedum.simplyposted.util.Storage;
import com.qedum.simplyposted.util.Validator;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Button btnLogin;
    private EditText etEmail;
    private EditText etPassword;
    private TextView tvCreateAccount;
    private TextView tvResetPassword;

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void attachActivityViews(Bundle savedInstanceState) {
        btnLogin = (Button) findViewById(R.id.activity_login_btn_login);
        etEmail = (EditText) findViewById(R.id.activity_login_et_email);
        etPassword = (EditText) findViewById(R.id.activity_login_et_password);
        tvCreateAccount = (TextView) findViewById(R.id.activity_login_tv_create_account);
        tvResetPassword = (TextView) findViewById(R.id.activity_login_tv_forgot_pass);
    }

    @Override
    protected void initActivityViews(Bundle savedInstanceState) {
        btnLogin.setOnClickListener(this);
        tvCreateAccount.setOnClickListener(this);
        tvResetPassword.setOnClickListener(this);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_login;
    }

    protected boolean isHomeAsUpEnabledShown() {
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_login_btn_login:
                tryLogin();
                break;
            case R.id.activity_login_tv_create_account:
                createAccount();
                break;
            case R.id.activity_login_tv_forgot_pass:
                startActivity(ForgotPasswordActivity.getLaunchIntent(this));
                break;
        }
    }

    private boolean isFormValid() {
        if (isEditTextEmpty(etEmail)) {
            etEmail.requestFocus();
            showInformationDialog(R.string.login_email_dialog_title, R.string.login_email_dialog_text);
            return false;
        }
        if (!Validator.isEmailValid(etEmail.getText().toString())) {
            etEmail.requestFocus();
            showInformationDialog(R.string.wrong_email_format_dialog_title, R.string.wrong_email_format_dialog_text);
            return false;
        }
        if (isEditTextEmpty(etPassword)) {
            etPassword.requestFocus();
            showInformationDialog(R.string.login_password_dialog_title, R.string.login_password_dialog_text);
            return false;
        }
        return true;
    }

    private void tryLogin() {
        if (isFormValid()) {
            showWaitingDialog();
            String email = etEmail.getText().toString().trim();
            String password = (etPassword.getText().toString().trim());
            ApiClient.getSharedInstance().login(email, password, new ApiCallback<LoginResponse>(LoginActivity.this) {
                @Override
                public void onSuccess(LoginResponse responseObject) {
                    Storage.getInstance().setUserLoggedIn(true);
                    Storage.getInstance().setAuthToken(responseObject.getKey());
                    getUserData();
                }

                @Override
                public void onFailure(String errorResponse, String rejectReason) {
                    dismissWaitingDialog();
                    Toast.makeText(LoginActivity.this, rejectReason, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private void getUserData() {
        ApiClient.getSharedInstance().getUser(new ApiCallback<UserResponse>() {
            @Override
            public void onSuccess(UserResponse responseObject) {
                Storage.getInstance().setUser(responseObject);
                dismissWaitingDialog();
                startActivity(MainActivity.getLaunchIntent(LoginActivity.this));
            }

            @Override
            public void onFailure(String errorResponse, String rejectReason) {
                dismissWaitingDialog();
                Storage.getInstance().clearAll();
                Toast.makeText(LoginActivity.this, rejectReason, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void createAccount() {
        startActivity(RegistrationActivity.getLaunchIntent(this));
    }


}
