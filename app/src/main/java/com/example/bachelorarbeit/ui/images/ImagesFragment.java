package com.example.bachelorarbeit.ui.images;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bachelorarbeit.GridAdapter;
import com.example.bachelorarbeit.LowerPage;
import com.example.bachelorarbeit.R;
import com.example.bachelorarbeit.SettingsAdapter;
import com.example.bachelorarbeit.SettingsItem;
import com.example.bachelorarbeit.databinding.ActivityMainBinding;
import com.example.bachelorarbeit.databinding.FragmentImagesBinding;

import java.util.ArrayList;

public class ImagesFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private SettingsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ImagesViewModel imagesViewModel;
    private FragmentImagesBinding binding;
    private GridView gridViewImage;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_images, container, false);
        gridViewImage = root.findViewById(R.id.gridView);
        int[] programmingImages = {R.drawable.swift, R.drawable.android, R.drawable.google_flutter_logo, R.drawable.dart, R.drawable.react, R.drawable.xamarin, R.drawable.ionic,
                R.drawable.pwa, R.drawable.html5, R.drawable.css3, R.drawable.js, R.drawable.nodejs, R.drawable.angular, R.drawable.php, R.drawable.java, R.drawable.python};
        GridAdapter gridAdapter = new GridAdapter(getActivity(), programmingImages);
        gridViewImage.setAdapter(gridAdapter);
        return root;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        TextView t = (TextView) getActivity().findViewById(R.id.title);
        t.setText(getString(R.string.title_images));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}