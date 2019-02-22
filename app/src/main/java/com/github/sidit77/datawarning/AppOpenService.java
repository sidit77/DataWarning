package com.github.sidit77.datawarning;

import android.accessibilityservice.AccessibilityService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.util.Log;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;

public class AppOpenService extends AccessibilityService {

    private String currentPackage = "";
    private SharedPreferences preferences;

    @Override
    public void onAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        try {
            if (accessibilityEvent == null || accessibilityEvent.getPackageName() == null)
                return;
            String eventpackage = accessibilityEvent.getPackageName().toString();
            if (!eventpackage.equals(currentPackage)) {
                currentPackage = eventpackage;
                if (preferences.getBoolean(currentPackage, false) && isMobileEnabled()) {
                    Toast.makeText(getApplicationContext(), R.string.message, Toast.LENGTH_LONG).show();
                }
            }
            accessibilityEvent.recycle();
        } catch (Exception e){
            Log.e("AccessibilityServiceError", "There was an unexpected error", e);
        }
    }

    private boolean isMobileEnabled(){
        ConnectivityManager conmgr = (ConnectivityManager)getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return conmgr.getActiveNetworkInfo() != null && conmgr.isActiveNetworkMetered();
    }

    @Override
    public void onInterrupt() {

    }

    @Override
    protected void onServiceConnected() {
        super.onServiceConnected();
        preferences = getSharedPreferences(SettingsActivity.sharedPreferencesName, Context.MODE_PRIVATE);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        return super.onUnbind(intent);
    }
}
