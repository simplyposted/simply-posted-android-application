package com.qedum.simplyposted.interfaces;

import android.support.annotation.Nullable;

public interface ProgressDialogInterface {

    void showProgress(@Nullable String progressMessage);

    void dismissProgress();

    boolean isProgressShowing();
}
