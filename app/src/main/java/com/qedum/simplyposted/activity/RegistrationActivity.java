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
import com.qedum.simplyposted.util.Validator;

import okhttp3.ResponseBody;

public class RegistrationActivity extends BaseActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnOk;
    private Button btnCancel;
    private TextView tvSignIn;

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, RegistrationActivity.class);
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_registration;
    }

    @Override
    protected void attachActivityViews(Bundle savedInstanceState) {
        etEmail = (EditText) findViewById(R.id.activity_registration_email);
        etPassword = (EditText) findViewById(R.id.activity_registration_password);
        etConfirmPassword = (EditText) findViewById(R.id.activity_registration_password_confirm);
        btnOk = (Button) findViewById(R.id.activity_registration_btn_ok);
        btnCancel = (Button) findViewById(R.id.activity_registration_btn_cancel);
        tvSignIn = (TextView) findViewById(R.id.activity_registration_tv_signin);
    }

    @Override
    protected void initActivityViews(Bundle savedInstanceState) {
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        tvSignIn.setOnClickListener(this);

        tvSignIn.setText(setUnderlineText(getString(R.string.activity_registration_tv_signin), getString(R.string.activity_registration_bottom_text2)));
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_registration_tv_signin:
                logIn();
                break;

            case R.id.activity_registration_btn_ok:
                tryRegister();
                break;
            case R.id.activity_registration_btn_cancel:
                cancelBtn();
                break;
        }
    }

    private void tryRegister() {
        if (isFormValid()) {
            showWaitingDialog();
            String email = etEmail.getText().toString().trim();
            String password = (etPassword.getText().toString().trim());
            ApiClient.getSharedInstance().register(email, password, new ApiCallback<ResponseBody>(RegistrationActivity.this) {
                @Override
                public void onSuccess(ResponseBody responseObject) {
                    tryLogin();
                }

                @Override
                public void onFailure(String errorResponse, String rejectReason) {
                    dismissWaitingDialog();
                    Toast.makeText(RegistrationActivity.this, rejectReason, Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private void tryLogin() {
        ApiClient.getSharedInstance().login(etEmail.getText().toString().trim(), etPassword.getText().toString().trim(), new ApiCallback<LoginResponse>() {
            @Override
            public void onSuccess(LoginResponse responseObject) {
                dismissWaitingDialog();
                startActivity(RegistrationFormActivity.getLaunchIntent(RegistrationActivity.this));
            }

            @Override
            public void onFailure(String errorResponse, String rejectReason) {
                Toast.makeText(RegistrationActivity.this, "Server error: " + rejectReason, Toast.LENGTH_SHORT).show();
                dismissWaitingDialog();
            }
        });
    }

    private void cancelBtn() {
        this.finish();
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
        if (!Validator.isPasswordValid(etPassword.getText().toString())) {
            etPassword.requestFocus();
            showInformationDialog(R.string.wrong_password_format_dialog_title, R.string.wrong_password_format_dialog_text);
            return false;
        }
        if (!(etPassword.getText().toString().equals(etConfirmPassword.getText().toString()))) {
            showInformationDialog(R.string.not_match_passwords_dialog_title, R.string.not_match_passwords__dialog_text);
            return false;
        }
        return true;
    }

    private void logIn() {
        startActivity(LoginActivity.getLaunchIntent(this));
    }

}
