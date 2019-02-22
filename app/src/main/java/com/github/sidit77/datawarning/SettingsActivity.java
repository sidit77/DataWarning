package com.github.sidit77.datawarning;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SettingsActivity extends Activity {

    public static final String sharedPreferencesName = "settings";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        RecyclerView rv = findViewById(R.id.app_list);
        rv.setAdapter(new AppListAdapter(getPackageManager(), getSharedPreferences(sharedPreferencesName, Context.MODE_PRIVATE)));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new DividerItemDecoration(rv.getContext(), layoutManager.getOrientation()));
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
}
