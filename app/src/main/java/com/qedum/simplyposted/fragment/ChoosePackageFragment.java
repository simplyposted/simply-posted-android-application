package com.qedum.simplyposted.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.qedum.simplyposted.R;
import com.qedum.simplyposted.util.Storage;

public class ChoosePackageFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener {

    private CheckBox chkEntry;
    private CheckBox chkBasic;
    private CheckBox chkPro;

    private static final int ENTRY = 0;
    private static final int BASIC = 1;
    private static final int PRO = 2;

    @Override
    protected int getContentView() {
        return R.layout.fragment_registration_step3;
    }

    @Override
    protected void attachFragmentViews(View view) {
        btnNextStep = (Button) view.findViewById(R.id.activity_reg_form_btn_next);
        chkEntry = (CheckBox) view.findViewById(R.id.fragment_registration_step3_chk_entry);
        chkBasic = (CheckBox) view.findViewById(R.id.fragment_registration_step3_chk_basic);
        chkPro = (CheckBox) view.findViewById(R.id.fragment_registration_step3_chk_pro);
    }

    @Override
    protected void initFragmentViews() {
        chkEntry.setOnCheckedChangeListener(this);
        chkBasic.setOnCheckedChangeListener(this);
        chkPro.setOnCheckedChangeListener(this);

        switch (Storage.getInstance().getChkPackage()) {
            case ENTRY:
                chkEntry.setChecked(true);
                break;
            case BASIC:
                chkBasic.setChecked(true);
                break;
            case PRO:
                chkPro.setChecked(true);
                break;
        }



    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
        switch (compoundButton.getId()) {
            case R.id.fragment_registration_step3_chk_entry:
                if (b) {
                    setCheckBoxStorage(ENTRY);
                    chkBasic.setChecked(false);
                    chkPro.setChecked(false);
                }
                break;
            case R.id.fragment_registration_step3_chk_basic:
                if (b) {
                    setCheckBoxStorage(BASIC);
                    chkEntry.setChecked(false);
                    chkPro.setChecked(false);
                }
                break;
            case R.id.fragment_registration_step3_chk_pro:
                if (b) {
                    setCheckBoxStorage(PRO);
                    chkBasic.setChecked(false);
                    chkEntry.setChecked(false);
                }
                break;
        }
    }

    private void setCheckBoxStorage (int value) {
        Storage.getInstance().setChkPackage(value);
    }

}