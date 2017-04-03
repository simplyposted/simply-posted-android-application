package com.qedum.simplyposted.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.qedum.simplyposted.R;
import com.qedum.simplyposted.util.Validator;

public class RegistrationActivity extends BaseActivity implements View.OnClickListener {

    private EditText etEmail;
    private EditText etPassword;
    private EditText etConfirmPassword;
    private Button btnOk;
    private Button btnCancel;

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
    }

    @Override
    protected void initActivityViews(Bundle savedInstanceState) {
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.activity_registration_btn_ok:
                tryOkBtn();
                break;
            case R.id.activity_registration_btn_cancel:
                cancelBtn();
                break;
        }
    }

    private void tryOkBtn() {
        if (isFormValid()) {
            startActivity(RegistrationFormActivity.getLaunchIntent(this));
        }
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
        if (isEditTextEmpty(etPassword)) {
            etPassword.requestFocus();
            showInformationDialog(R.string.login_password_dialog_title, R.string.login_password_dialog_text);
            return false;
        }

        if (isEditTextEmpty(etConfirmPassword)) {
            etConfirmPassword.requestFocus();
            showInformationDialog(R.string.registration_confirm_password_dialog_title, R.string.registration_confirm_password_dialog_text);
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

        if (!Validator.isPasswordValid(etConfirmPassword.getText().toString())) {
            etConfirmPassword.requestFocus();
            showInformationDialog(R.string.wrong_password_format_dialog_title, R.string.wrong_password_format_dialog_text);
            return false;
        }

        if (!(etPassword.getText().toString().equals(etConfirmPassword.getText().toString()))) {
            showInformationDialog(R.string.not_match_passwords_dialog_title, R.string.not_match_passwords__dialog_text);
            return false;
        }
        return true;
    }
}
