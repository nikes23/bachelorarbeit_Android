package com.example.bachelorarbeit.ui.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bachelorarbeit.LowerPage;
import com.example.bachelorarbeit.R;
import com.example.bachelorarbeit.SettingsAdapter;
import com.example.bachelorarbeit.SettingsItem;
import com.example.bachelorarbeit.databinding.FragmentSettingsBinding;

import java.util.ArrayList;

public class SettingsFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private SettingsAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private SettingsViewModel settingsViewModel;
    private FragmentSettingsBinding binding;
    private Toolbar toolbar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_settings, container, false);
        //toolbar = getActivity().findViewById(R.id.toolbar);

        TextView t = (TextView) getActivity().findViewById(R.id.title) ;
        t.setText(getString(R.string.title_settings)) ;

        /*((AppCompatActivity)getActivity()).setSupportActionBar(toolbar);
        ((AppCompatActivity)getActivity()).setTitle(R.string.title_settings);*/


        //getActivity().setTitle(getString(R.string.title_settings));

        final ArrayList<SettingsItem> settingsList = new ArrayList<>();
        settingsList.add(new SettingsItem(R.drawable.ic_baseline_account_box_24, getString(R.string.account)));
        settingsList.add(new SettingsItem(R.drawable.ic_baseline_security_24, getString(R.string.privacy)));
        settingsList.add(new SettingsItem(R.drawable.ic_baseline_lock_24, getString(R.string.security)));
        settingsList.add(new SettingsItem(R.drawable.ic_baseline_palette_24, getString(R.string.design)));
        settingsList.add(new SettingsItem(R.drawable.ic_baseline_notifications_active_24, getString(R.string.sound_notifications)));
        settingsList.add(new SettingsItem(R.drawable.ic_baseline_chat_bubble_24, getString(R.string.chat)));
        settingsList.add(new SettingsItem(R.drawable.ic_baseline_image_24, getString(R.string.media_storage)));
        settingsList.add(new SettingsItem(R.drawable.ic_baseline_bug_report_24, getString(R.string.bug)));
        settingsList.add(new SettingsItem(R.drawable.ic_baseline_info_24, getString(R.string.about)));

        mRecyclerView = root.findViewById(R.id.recycler_view_settings);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new SettingsAdapter(settingsList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new SettingsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                if (settingsList.get(position) == settingsList.get(0)) {
                    Intent account = new Intent(getActivity(), LowerPage.class);
                    startActivity(account);
                }
                if (settingsList.get(position) == settingsList.get(1)) {
                    Intent privacy = new Intent(getActivity(), LowerPage.class);
                    startActivity(privacy);
                }
                if (settingsList.get(position) == settingsList.get(2)) {
                    Intent security = new Intent(getActivity(), LowerPage.class);
                    startActivity(security);
                }
                if (settingsList.get(position) == settingsList.get(3)) {
                    Intent design = new Intent(getActivity(), LowerPage.class);
                    startActivity(design);
                }
                if (settingsList.get(position) == settingsList.get(4)) {
                    Intent sound = new Intent(getActivity(), LowerPage.class);
                    startActivity(sound);
                }
                if (settingsList.get(position) == settingsList.get(5)) {
                    Intent chat = new Intent(getActivity(), LowerPage.class);
                    startActivity(chat);
                }
                if (settingsList.get(position) == settingsList.get(6)) {
                    Intent media = new Intent(getActivity(), LowerPage.class);
                    startActivity(media);
                }
                if (settingsList.get(position) == settingsList.get(7)) {
                    Intent bugs = new Intent(getActivity(), LowerPage.class);
                    startActivity(bugs);
                }
                if (settingsList.get(position) == settingsList.get(8)) {
                    Intent about = new Intent(getActivity(), LowerPage.class);
                    startActivity(about);
                }
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}