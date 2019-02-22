package com.github.sidit77.datawarning;

import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class AppListAdapter extends RecyclerView.Adapter<AppListAdapter.ViewHolder>{

    private List<AppItem> apps;
    private PackageManager pm;

    private SharedPreferences preferences;

    public AppListAdapter(PackageManager pm, SharedPreferences preferences){
        this.pm = pm;
        this.preferences = preferences;
        apps = pm.getInstalledApplications(PackageManager.GET_META_DATA).stream()
                .filter(ai -> pm.getLaunchIntentForPackage(ai.packageName) != null)
                .map(AppItem::new)
                .sorted()
                .collect(Collectors.toList());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View appView = inflater.inflate(R.layout.applist_item, parent, false);
        return new ViewHolder(appView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        AppItem ai = apps.get(position);

        holder.iconImageView.setImageDrawable(ai.getIcon());
        holder.nameTextView.setText(ai.getName());
        holder.selectedCheckBox.setOnCheckedChangeListener(null);
        holder.selectedCheckBox.setChecked(preferences.getBoolean(ai.getPackageName(), false));
        holder.selectedCheckBox.setOnCheckedChangeListener((b,c) -> {
            preferences.edit().putBoolean(ai.getPackageName(), c).apply();
        });
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView iconImageView;
        public TextView nameTextView;
        public CheckBox selectedCheckBox;

        public ViewHolder(View itemView) {
            super(itemView);
            iconImageView = itemView.findViewById(R.id.app_icon);
            nameTextView = itemView.findViewById(R.id.app_name);
            selectedCheckBox = itemView.findViewById(R.id.app_checkbox);
        }
    }

    private class AppItem implements Comparable<AppItem>{
        private String name, packageName;
        private Drawable icon;
        public AppItem(ApplicationInfo ai){
            name = ai.loadLabel(pm).toString();
            packageName = ai.packageName;
            icon = ai.loadIcon(pm);
        }

        public String getName() {
            return name;
        }

        public String getPackageName() {
            return packageName;
        }

        public Drawable getIcon(){
            return icon;
        }

        @Override
        public int compareTo(@NonNull AppItem appItem) {
            return name.compareToIgnoreCase(appItem.getName());
        }
    }

}
