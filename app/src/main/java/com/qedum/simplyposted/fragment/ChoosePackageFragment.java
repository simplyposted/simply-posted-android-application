package com.qedum.simplyposted.fragment;

import android.view.View;
import android.widget.Button;

import com.qedum.simplyposted.R;

public class ChoosePackageFragment extends BaseFragment{

    @Override
    protected int getContentView() {
        return R.layout.fragment_registration_step3;
    }

    @Override
    protected void attachFragmentViews(View view) {
        btnNextStep = (Button) view.findViewById(R.id.activity_reg_form_btn_next);
    }

    @Override
    protected void initFragmentViews() {
//        btnNextStep.setText("OK");
    }
}