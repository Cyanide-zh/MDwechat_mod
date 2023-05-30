package org.devio.takephoto.model;

import android.app.Activity;
import android.preference.PreferenceFragment;
import androidx.fragment.app.Fragment;

/**
 * Author: JPH
 * Date: 2016/8/11 17:01
 */
public class TContextWrap {
    private Activity activity;
    private Fragment fragment;
    private PreferenceFragment preferenceFragment;

    private TContextWrap(Activity activity) {
        this.activity = activity;
    }

    private TContextWrap(Fragment fragment) {
        this.fragment = fragment;
        this.activity = fragment.getActivity();
    }

    private TContextWrap(PreferenceFragment fragment) {
        this.preferenceFragment = fragment;
        this.activity = fragment.getActivity();
    }

    public static TContextWrap of(Activity activity) {
        return new TContextWrap(activity);
    }

    public static TContextWrap of(Fragment fragment) {
        return new TContextWrap(fragment);
    }

    public static TContextWrap of(PreferenceFragment fragment) {
        return new TContextWrap(fragment);
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public Fragment getFragment() {
        return fragment;
    }

    public PreferenceFragment getPreferenceFragment() {
        return preferenceFragment;
    }

    public void setFragment(Fragment fragment) {
        this.fragment = fragment;
    }
}
