package com.projectsexception.myapplist.work;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.preference.PreferenceManager;
import com.projectsexception.myapplist.PreferenceActivity;
import com.projectsexception.myapplist.model.AppInfo;
import com.projectsexception.myapplist.util.AppUtil;

import java.util.ArrayList;

public class AppListLoader extends AbstractListLoader {
    
    private final PackageManager mPm;
    private final boolean mHideSystemApps;

    public AppListLoader(Context context) {
        super(context);

        // Retrieve the package manager for later use; note we don't
        // use 'context' directly but instead the save global application
        // context returned by getContext().
        mPm = getContext().getPackageManager();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(context);
        mHideSystemApps = prefs.getBoolean(PreferenceActivity.KEY_HIDE_SYSTEM_APPS, true);
    }

    @Override
    public ArrayList<AppInfo> loadAppInfoList() {
        return AppUtil.loadAppInfoList(mPm, mHideSystemApps);
    }

    @Override
    public boolean isPackageIntentReceiver() {
        return true;
    }
}