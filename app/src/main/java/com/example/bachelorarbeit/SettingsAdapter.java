package com.example.bachelorarbeit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SettingsAdapter extends RecyclerView.Adapter<SettingsAdapter.SettingsViewHolder> {
    private ArrayList<SettingsItem> mSettingsList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class SettingsViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mText;

        public SettingsViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.settings_item_imageview);
            mText = itemView.findViewById(R.id.settings_item_text);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }

    public SettingsAdapter(ArrayList<SettingsItem> settingsList) {
        mSettingsList = settingsList;
    }

    @NonNull
    @Override
    public SettingsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.settings_item, parent, false);
        SettingsViewHolder settingsViewHolder = new SettingsViewHolder(view, mListener);
        return settingsViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SettingsViewHolder holder, int position) {
        SettingsItem currentItem = mSettingsList.get(position);
        holder.mImageView.setImageResource(currentItem.getmImageResource());
        holder.mText.setText(currentItem.getmText());

    }

    @Override
    public int getItemCount() {
        return mSettingsList.size();
    }
}
