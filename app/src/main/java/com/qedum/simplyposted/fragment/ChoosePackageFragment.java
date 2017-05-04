package com.qedum.simplyposted.fragment;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qedum.simplyposted.R;
import com.qedum.simplyposted.util.Storage;

public class ChoosePackageFragment extends BaseFragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    private CheckBox chkEntry;
    private CheckBox chkBasic;
    private CheckBox chkPro;
    private LinearLayout llEntry;
    private LinearLayout llBasic;
    private LinearLayout llPro;
    private TextView tvEntryText;
    private TextView tvBasicText;
    private TextView tvProText;
    private ImageView imvEntryArrow;
    private ImageView imvBasicArrow;
    private ImageView imvProArrow;

    private View imvProgress;

    private static final int ENTRY = 0;
    private static final int BASIC = 1;
    private static final int PRO = 2;


    @Override
    protected int getContentView() {
        return R.layout.fragment_registration_step3;
    }

    @Override
    protected void attachFragmentViews(View view) {
        chkEntry = (CheckBox) view.findViewById(R.id.fragment_registration_step3_chk_entry);
        chkBasic = (CheckBox) view.findViewById(R.id.fragment_registration_step3_chk_basic);
        chkPro = (CheckBox) view.findViewById(R.id.fragment_registration_step3_chk_pro);
        llEntry = (LinearLayout) view.findViewById(R.id.fragment_registration_step3_ll_entry);
        llBasic = (LinearLayout) view.findViewById(R.id.fragment_registration_step3_ll_basic);
        llPro = (LinearLayout) view.findViewById(R.id.fragment_registration_step3_ll_pro);
        tvEntryText = (TextView) view.findViewById(R.id.fragment_registration_step3_entry_array);

        tvBasicText = (TextView) view.findViewById(R.id.fragment_registration_step3_basic_array);
        tvProText = (TextView) view.findViewById(R.id.fragment_registration_step3_pro_array);
        imvEntryArrow = (ImageView) view.findViewById(R.id.fragment_registration_step3_imv_entry_arrow);
        imvBasicArrow = (ImageView) view.findViewById(R.id.fragment_registration_step3_imv_basic_arrow);
        imvProArrow = (ImageView) view.findViewById(R.id.fragment_registration_step3_imv_pro_arrow);

        imvProgress = view.findViewById(R.id.fragment_registration_step3_progress);
    }

    @Override
    protected void initFragmentViews() {
        if (Storage.getInstance().isUserLoggedIn()) {
            imvProgress.setVisibility(View.GONE);
        }
        llEntry.setOnClickListener(this);
        llBasic.setOnClickListener(this);
        llPro.setOnClickListener(this);
        chkEntry.setOnCheckedChangeListener(this);
        chkBasic.setOnCheckedChangeListener(this);
        chkPro.setOnCheckedChangeListener(this);


        switch (Storage.getInstance().getUserPackage()) {
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
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (isChecked) {
            switch (compoundButton.getId()) {
                case R.id.fragment_registration_step3_chk_entry:
                    setCheckBoxStorage(ENTRY);
                    chkBasic.setChecked(false);
                    chkPro.setChecked(false);
                    break;
                case R.id.fragment_registration_step3_chk_basic:
                    setCheckBoxStorage(BASIC);
                    chkEntry.setChecked(false);
                    chkPro.setChecked(false);
                    break;
                case R.id.fragment_registration_step3_chk_pro:
                    setCheckBoxStorage(PRO);
                    chkBasic.setChecked(false);
                    chkEntry.setChecked(false);
                    break;
            }
        }
    }

    private void setCheckBoxStorage(int value) {
        Storage.getInstance().setUserPackage(value);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_registration_step3_ll_entry:
                onClickCollapse(tvEntryText, imvEntryArrow);
                break;
            case R.id.fragment_registration_step3_ll_basic:
                onClickCollapse(tvBasicText, imvBasicArrow);
                break;
            case R.id.fragment_registration_step3_ll_pro:
                onClickCollapse(tvProText, imvProArrow);
                break;
        }
    }


    private void onClickCollapse(TextView text, ImageView img) {
        if (text.getVisibility() == View.GONE) {
            text.setVisibility(View.VISIBLE);
            img.setImageResource(R.drawable.ic_arrow_up_white);
        } else {
            text.setVisibility(View.GONE);
            img.setImageResource(R.drawable.ic_arrow_down_white);
        }
    }
}