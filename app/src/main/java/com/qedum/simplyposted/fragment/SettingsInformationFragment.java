package com.qedum.simplyposted.fragment;

import android.view.View;
import android.widget.EditText;

import com.qedum.simplyposted.R;
import com.qedum.simplyposted.activity.BaseActivity;
import com.qedum.simplyposted.util.Storage;
import com.qedum.simplyposted.view.MultiSpinner;

import java.util.Arrays;

public class SettingsInformationFragment extends BaseFragment implements MultiSpinner.MultiSpinnerListener {

    private EditText etName;
    private EditText etPhone;
    private EditText etZip;
    private View imvProgress;

    @Override
    protected int getContentView() {
        return R.layout.fragment_registration_step2;
    }

    @Override
    protected void attachFragmentViews(View view) {
        etName = (EditText) view.findViewById(R.id.fragment_registration_step2_et_name);
        etPhone = (EditText) view.findViewById(R.id.fragment_registration_step2_et_phone);
        etZip = (EditText) view.findViewById(R.id.fragment_registration_step2_et_zipcode);
        imvProgress = view.findViewById(R.id.fragment_registration_step2_progress);
    }

    @Override
    protected void initFragmentViews() {
        if (Storage.getInstance().isUserLoggedIn()) {
            imvProgress.setVisibility(View.GONE);
        }
        etName.setText(Storage.getInstance().getCompanyName());
        etPhone.setText(Storage.getInstance().getCompanyPhone());
        etZip.setText(Storage.getInstance().getZipCode());

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
        return true;
    }

    public void saveSettings() {
        Storage.getInstance().setCompanyName(etName.getText().toString());
        Storage.getInstance().setCompanyPhone(etPhone.getText().toString());
        Storage.getInstance().setZipCode(etZip.getText().toString());
    }

    @Override
    public void onItemsSelected(boolean[] selected) {

    }
}