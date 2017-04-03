package com.qedum.simplyposted.fragment;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.qedum.simplyposted.activity.BaseActivity;


public abstract class BaseFragment extends Fragment {

    protected Button btnNextStep;

    @Override
    public final View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return onCreateView(getContentView(), inflater, container, savedInstanceState);
    }

    public View onCreateView(int resId, LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (resId < 0) return null;
        View view = inflater.inflate(resId, container, false);
        attachFragmentViews(view);
        initFragmentViews();
        return view;
    }

    protected abstract int getContentView();

    @Override
    public void onPause() {
        super.onPause();
        hideKeyboard();
    }


    protected abstract void attachFragmentViews(View view);

    protected abstract void initFragmentViews();

    public BaseActivity getBaseActivity() {
        BaseActivity bActivity = null;
        Activity activity = getActivity();
        if (activity != null && activity instanceof BaseActivity) {
            bActivity = (BaseActivity) activity;
        }
        return bActivity;
    }

    public void hideKeyboard() {
        BaseActivity bActivity = getBaseActivity();
        if (bActivity != null) {
            bActivity.hideKeyboard();
        }
    }

    public void showWaitingDialog(String waitingMessage, boolean cancelable) {
        BaseActivity bActivity = getBaseActivity();
        if (bActivity != null) {
            bActivity.showWaitingDialog(waitingMessage, cancelable);
        }
    }

    public void showWaitingDialog(String waitingMessage) {
        BaseActivity bActivity = getBaseActivity();
        if (bActivity != null) {
            bActivity.showWaitingDialog(waitingMessage);
        }
    }

    public void showInformationDialog(String title, String message) {
        BaseActivity bActivity = getBaseActivity();
        if (bActivity != null) {
            bActivity.showInformationDialog(title, message);
        }
    }

    public void showInformationDialog(int title, int message) {
        BaseActivity bActivity = getBaseActivity();
        if (bActivity != null) {
            bActivity.showInformationDialog(title, message);
        }
    }

    public void showInformationDialog(String title, String message, DialogInterface.OnClickListener listener) {
        BaseActivity bActivity = getBaseActivity();
        if (bActivity != null) {
            bActivity.showInformationDialog(title, message, listener);
        }
    }

    public void showInformationDialog(@StringRes int titleId, @StringRes int messageId, DialogInterface.OnClickListener listener) {
        BaseActivity bActivity = getBaseActivity();
        if (bActivity != null) {
            bActivity.showInformationDialog(titleId, messageId, listener);
        }
    }

    public void showInformationDialog(int titleId, int messageId, int positiveId, int negativeId,
                                      DialogInterface.OnClickListener positiveListener, DialogInterface.OnClickListener negativeListener) {
        BaseActivity bActivity = getBaseActivity();
        if (bActivity != null) {
            bActivity.showInformationDialog(titleId, messageId, positiveId, negativeId, positiveListener, negativeListener);
        }

    }

    public void dismissWaitingDialog() {
        BaseActivity bActivity = getBaseActivity();
        if (bActivity != null) {
            bActivity.dismissWaitingDialog();
        }
    }
}