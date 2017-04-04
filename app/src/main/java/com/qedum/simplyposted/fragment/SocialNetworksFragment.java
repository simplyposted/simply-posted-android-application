package com.qedum.simplyposted.fragment;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.qedum.simplyposted.R;

public class SocialNetworksFragment extends BaseFragment implements View.OnClickListener {

    private TextView tvFacebook;
    private TextView tvLinkedin;
    private TextView tvTwitter;
    private TextView tvInstagram;

    @Override
    protected int getContentView() {
        return R.layout.fragment_registration_step1;
    }

    @Override
    protected void attachFragmentViews(View view) {
        tvFacebook = (TextView) view.findViewById(R.id.fragment_registration_step1_tv_facebook);
        tvLinkedin = (TextView) view.findViewById(R.id.fragment_registration_step1_tv_linkedin);
        tvTwitter = (TextView) view.findViewById(R.id.fragment_registration_step1_tv_twitter);
        tvInstagram = (TextView) view.findViewById(R.id.fragment_registration_step1_tv_instagram);
    }

    @Override
    protected void initFragmentViews() {
        tvFacebook.setOnClickListener(this);
        tvLinkedin.setOnClickListener(this);
        tvTwitter.setOnClickListener(this);
        tvInstagram.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_registration_step1_tv_facebook:
                Toast.makeText(this.getActivity(), R.string.fragment_social_account_connected, Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragment_registration_step1_tv_linkedin:
                Toast.makeText(this.getActivity(), R.string.fragment_social_account_connected, Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragment_registration_step1_tv_twitter:
                Toast.makeText(this.getActivity(), R.string.fragment_social_account_connected, Toast.LENGTH_SHORT).show();
                break;
            case R.id.fragment_registration_step1_tv_instagram:
                Toast.makeText(this.getActivity(), R.string.fragment_social_account_connected, Toast.LENGTH_SHORT).show();
                break;
        }
    }
}