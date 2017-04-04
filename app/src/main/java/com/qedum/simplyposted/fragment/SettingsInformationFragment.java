package com.qedum.simplyposted.fragment;

import android.view.View;
import android.widget.EditText;

import com.qedum.simplyposted.R;
import com.qedum.simplyposted.activity.BaseActivity;
import com.qedum.simplyposted.util.Storage;

public class SettingsInformationFragment extends BaseFragment {

    private EditText etName;
    private EditText etPhone;

    @Override
    protected int getContentView() {
        return R.layout.fragment_registration_step2;
    }

    @Override
    protected void attachFragmentViews(View view) {
        etName = (EditText) view.findViewById(R.id.fragment_registration_step2_et_name);
        etPhone = (EditText) view.findViewById(R.id.fragment_registration_step2_et_phone);
    }

    @Override
    protected void initFragmentViews() {
        etName.setText(Storage.getInstance().getCompanyName());
        etPhone.setText(Storage.getInstance().getCompanyPhone());
    }

    public boolean isFormValid() {
        if (BaseActivity.isEditTextEmpty(etName)) {
            etName.requestFocus();
            showInformationDialog(R.string.fragment_step2_name_title, R.string.fragment_step2_name_text);
            return false;
        }
        if (BaseActivity.isEditTextEmpty(etPhone)) {
            etPhone.requestFocus();
            showInformationDialog(R.string.fragment_step2_phone_title, R.string.fragment_step2_phone_text);
            return false;
        }

        Storage.getInstance().setCompanyName(etName.getText().toString());
        Storage.getInstance().setCompanyPhone(etPhone.getText().toString());

        return true;
    }
}