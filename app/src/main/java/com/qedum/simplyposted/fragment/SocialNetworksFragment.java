package com.qedum.simplyposted.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.qedum.simplyposted.R;
import com.qedum.simplyposted.util.Storage;

public class SocialNetworksFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout llFacebook;
    private LinearLayout llLinkedIn;
    private LinearLayout llTwitter;
    private LinearLayout llInstagram;
    private TextView tvFacebook;
    private TextView tvLinkedIn;
    private TextView tvTwitter;
    private TextView tvInstagram;
    private ImageView imvFacebook;
    private ImageView imvLinkedIn;
    private ImageView imvTwitter;
    private ImageView imvInstagram;
    private View imvProgress;

    @Override
    protected int getContentView() {
        return R.layout.fragment_registration_step1;
    }

    @Override
    protected void attachFragmentViews(View view) {
        llFacebook = (LinearLayout) view.findViewById(R.id.fragment_registration_step1_facebook);
        llLinkedIn = (LinearLayout) view.findViewById(R.id.fragment_registration_step1_linkedin);
        llTwitter = (LinearLayout) view.findViewById(R.id.fragment_registration_step1_twitter);
        llInstagram = (LinearLayout) view.findViewById(R.id.fragment_registration_step1_instagram);
        tvFacebook = (TextView) view.findViewById(R.id.fragment_registration_step1_tv_facebook);
        tvLinkedIn = (TextView) view.findViewById(R.id.fragment_registration_step1_tv_linkedin);
        tvTwitter = (TextView) view.findViewById(R.id.fragment_registration_step1_tv_twitter);
        tvInstagram = (TextView) view.findViewById(R.id.fragment_registration_step1_tv_instagram);
        imvFacebook = (ImageView) view.findViewById(R.id.fragment_registration_step1_imv_facebook);
        imvLinkedIn = (ImageView) view.findViewById(R.id.fragment_registration_step1_imv_linkedin);
        imvTwitter = (ImageView) view.findViewById(R.id.fragment_registration_step1_imv_twitter);
        imvInstagram = (ImageView) view.findViewById(R.id.fragment_registration_step1_imv_instagram);
        imvProgress = view.findViewById(R.id.fragment_registration_step1_progress);
    }

    @Override
    protected void initFragmentViews() {
        if(Storage.getInstance().isUserLoggedIn()){
            imvProgress.setVisibility(View.GONE);
        }
        llFacebook.setOnClickListener(this);
        llLinkedIn.setOnClickListener(this);
        llTwitter.setOnClickListener(this);
        llInstagram.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_registration_step1_facebook:
                onClickChange(tvFacebook, imvFacebook);
                break;
            case R.id.fragment_registration_step1_linkedin:
                onClickChange(tvLinkedIn, imvLinkedIn);
                break;
            case R.id.fragment_registration_step1_twitter:
                onClickChange(tvTwitter, imvTwitter);
                break;
            case R.id.fragment_registration_step1_instagram:
                onClickChange(tvInstagram, imvInstagram);
                break;
        }
    }

    private void onClickChange(TextView text, ImageView img) {
        if (img.getVisibility() == View.INVISIBLE) {
            img.setVisibility(View.VISIBLE);
            text.setText(getString(R.string.fragment_social_account_connected));
        } else {
            img.setVisibility(View.INVISIBLE);
            text.setText(getString(R.string.fragment_social_account_not_connected));
        }
    }
}