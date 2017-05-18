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
import com.qedum.simplyposted.model.api.UserResponse;
import com.qedum.simplyposted.util.Storage;
import com.qedum.simplyposted.util.Validator;

import okhttp3.ResponseBody;

public class ForgotPasswordActivity extends BaseActivity implements View.OnClickListener {

    private EditText etEmail;
    private Button btnSend;
    private TextView tvLogin;

    public static Intent getLaunchIntent(Context context) {
        return new Intent(context, ForgotPasswordActivity.class);
    }

    @Override
    protected void attachActivityViews(Bundle savedInstanceState) {
        etEmail = (EditText) findViewById(R.id.activity_forgot_pass_edt_email);
        btnSend = (Button) findViewById(R.id.activity_forgot_pass_btn_send);
        tvLogin = (TextView) findViewById(R.id.activity_forgot_pass_tv_login);
    }

    @Override
    protected void initActivityViews(Bundle savedInstanceState) {
        btnSend.setOnClickListener(this);
        tvLogin.setOnClickListener(this);

    }

    @Override
    protected int getContentView() {
        return R.layout.activity_forgot_pass;
    }

    protected boolean isHomeAsUpEnabledShown() {
        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_forgot_pass_btn_send:
                tryLogin();
                break;
            case R.id.activity_forgot_pass_tv_login:
                startActivity(LoginActivity.getLaunchIntent(this));
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
        return true;
    }

    private void tryLogin() {
        if (isFormValid()) {
            showWaitingDialog();
            ApiClient.getSharedInstance().resetPassword(etEmail.getText().toString().trim(), new ApiCallback<ResponseBody>(this) {
                @Override
                public void onSuccess(ResponseBody responseObject) {
                    dismissWaitingDialog();
                    Toast.makeText(ForgotPasswordActivity.this, getResources().getString(R.string.activity_forgot_alert_success), Toast.LENGTH_SHORT).show();
                    startActivity(LoginActivity.getLaunchIntent(ForgotPasswordActivity.this));
                    finish();
                }

                @Override
                public void onFailure(String errorResponse, String rejectReason) {
                    dismissWaitingDialog();
                    Toast.makeText(ForgotPasswordActivity.this, rejectReason, Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
