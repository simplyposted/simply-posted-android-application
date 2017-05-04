package com.qedum.simplyposted.api;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;

import java.lang.ref.WeakReference;

public abstract class ApiCallback<T> {
    private WeakReference<Activity> activity;
    private WeakReference<Fragment> fragment;
    private boolean isShowProgressDialog;
    private String progressMessage;

//    public ApiCallback() {
//        this.isBackGround = true;
//    }

    public ApiCallback() {
    }

    public ApiCallback(@NonNull Activity activity) {
        attachActivity(activity);
    }

    public ApiCallback(@NonNull Fragment fragment) {
        attachFragment(fragment);
    }

    public ApiCallback(@NonNull Activity activity, @Nullable String progressMessage) {
        this(activity);
        isShowProgressDialog = true;
        this.progressMessage = progressMessage;
    }

    public ApiCallback(@NonNull Activity activity, @StringRes int progressTextId) {
        this(activity, activity.getString(progressTextId));
    }

    public ApiCallback(@NonNull Fragment fragment, @Nullable String progressMessage) {
        this(fragment);
        isShowProgressDialog = true;
        this.progressMessage = progressMessage;
    }

    public ApiCallback(@NonNull Fragment fragment, @StringRes int progressTextId) {
        this(fragment, fragment.getString(progressTextId));
    }

    public final void attachActivity(Activity activity) {
        if (activity != null) {
            this.activity = new WeakReference<>(activity);
        }
    }

    public final void attachFragment(Fragment fragment) {
        if (fragment != null) {
            this.fragment = new WeakReference<>(fragment);
        }
    }

    private Activity getActivity() {
        if (activity != null) {
            Activity act = activity.get();
            if (act != null && !act.isFinishing()) {
                return act;
            }
        } else if (fragment != null) {
            Fragment frag = fragment.get();
            if (frag != null && frag.isAdded()) {
                return frag.getActivity();
            }
        }
        return null;
    }

    private void deliver(Runnable action) {
        Activity act = getActivity();
        if (act != null) {
            act.runOnUiThread(action);
        } else {
            new Thread(action).start();
        }
    }

    public final void deliverSuccess(final T response) {
        deliver(new Runnable() {
            @Override
            public void run() {
                onSuccess(response);
            }
        });
    }

    public final void deliverError(final String errorResponse, final String rejectReason) {
        deliver(new Runnable() {
            @Override
            public void run() {
                onFailure(errorResponse, rejectReason);
            }
        });
    }

    public abstract void onSuccess(T responseObject);

    public abstract void onFailure(String errorResponse, String rejectReason);

}