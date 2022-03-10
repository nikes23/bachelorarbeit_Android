package com.example.bachelorarbeit;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CameraAdapter extends RecyclerView.Adapter<CameraAdapter.CameraViewHolder> {
    private ArrayList<CameraItem> mCameraList;
    private OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

    public static class CameraViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mText;

        public CameraViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.camera_imageview);
            mText = itemView.findViewById(R.id.camera_textview);

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

    public CameraAdapter(ArrayList<CameraItem> cameraList) {
        mCameraList = cameraList;
    }

    @NonNull
    @Override
    public CameraViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.camera_item, parent, false);
        CameraViewHolder cameraViewHolder = new CameraViewHolder(view, mListener);
        return cameraViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CameraViewHolder holder, int position) {
        CameraItem currentItem = mCameraList.get(position);
        holder.mImageView.setImageResource(currentItem.getmImageResource());
        holder.mText.setText(currentItem.getmText());

    }

    @Override
    public int getItemCount() {
        return mCameraList.size();
    }
}
