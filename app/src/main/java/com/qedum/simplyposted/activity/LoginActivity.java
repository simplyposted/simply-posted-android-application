package com.qedum.simplyposted.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.qedum.simplyposted.R;
import com.qedum.simplyposted.util.Validator;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Button btnLogin;
    private EditText etEmail;
    private EditText etPassword;
    private TextView tvCreateAccount;

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, LoginActivity.class);
    }

    @Override
    protected void attachActivityViews(Bundle savedInstanceState) {
        btnLogin = (Button) findViewById(R.id.activity_login_btn_login);
        etEmail = (EditText) findViewById(R.id.activity_login_et_email);
        etPassword = (EditText) findViewById(R.id.activity_login_et_password);
        tvCreateAccount = (TextView) findViewById(R.id.activity_login_tv_create_account);
    }

    @Override
    protected void initActivityViews(Bundle savedInstanceState) {
        btnLogin.setOnClickListener(this);
        tvCreateAccount.setOnClickListener(this);
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
        }
    }

    private boolean isFormValid() {
        if (isEditTextEmpty(etEmail)) {
            etEmail.requestFocus();
            showInformationDialog(R.string.login_email_dialog_title, R.string.login_email_dialog_text);
            return false;
        }
        if (isEditTextEmpty(etPassword)) {
            etPassword.requestFocus();
            showInformationDialog(R.string.login_password_dialog_title, R.string.login_password_dialog_text);
            return false;
        }
        if (!Validator.isEmailValid(etEmail.getText().toString())) {
            etEmail.requestFocus();
            showInformationDialog(R.string.wrong_email_format_dialog_title, R.string.wrong_email_format_dialog_text);
            return false;
        }
        if (!Validator.isPasswordValid(etPassword.getText().toString())) {
            etPassword.requestFocus();
            showInformationDialog(R.string.wrong_password_format_dialog_title, R.string.wrong_password_format_dialog_text);
            return false;
        }
        return true;
    }

    private void tryLogin() {
        if (isFormValid()) {
            startActivity(MainActivity.getLaunchIntent(this));
        }
    }

    private void createAccount() {
        // start registration activity
        startActivity(null, null);
    }
}
