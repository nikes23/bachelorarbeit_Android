package com.example.bachelorarbeit.ui.camera;

import static android.app.Activity.RESULT_CANCELED;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bachelorarbeit.CameraAdapter;
import com.example.bachelorarbeit.CameraItem;
import com.example.bachelorarbeit.LowerPage;
import com.example.bachelorarbeit.MainActivity;
import com.example.bachelorarbeit.R;
import com.example.bachelorarbeit.SettingsAdapter;
import com.example.bachelorarbeit.SettingsItem;
import com.example.bachelorarbeit.databinding.FragmentCameraBinding;
import com.example.bachelorarbeit.databinding.FragmentSettingsBinding;
import com.example.bachelorarbeit.ui.settings.SettingsViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CameraFragment extends Fragment {

    private View root;
    private ImageView imageView;
    private RecyclerView mRecyclerView;
    private CameraAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private Toolbar toolbar;
    private CameraViewModel cameraViewModel;
    private FragmentCameraBinding binding;
    public static final int REQUEST_ID_MULTIPLE_PERMISSIONS = 101;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_camera, container, false);
        //getActivity().setContentView(R.layout.fragment_camera);
        imageView = getActivity().findViewById(R.id.camera_view);

        if (checkAndRequestPermissions(getActivity())) {
            chooseImage(getActivity());
        }

        TextView t = (TextView) getActivity().findViewById(R.id.title);
        t.setText(getString(R.string.title_camera));
        final ArrayList<CameraItem> cameraList = new ArrayList<>();
        cameraList.add(new CameraItem(R.drawable.ic_baseline_photo_camera_24, getString(R.string.open_camera)));
        cameraList.add(new CameraItem(R.drawable.ic_baseline_image_24, getString(R.string.open_gallery)));
        mRecyclerView = root.findViewById(R.id.recycler_view_camera);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new CameraAdapter(cameraList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CameraAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                if (cameraList.get(position) == cameraList.get(0)) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);
                }
                if (cameraList.get(position) == cameraList.get(1)) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);
                }
            }
        });
        return root;
    }

    private void chooseImage(Context context) {
        TextView t = (TextView) getActivity().findViewById(R.id.title);
        t.setText(getString(R.string.title_camera));
        final ArrayList<CameraItem> cameraList = new ArrayList<>();
        cameraList.add(new CameraItem(R.drawable.ic_baseline_photo_camera_24, getString(R.string.open_camera)));
        cameraList.add(new CameraItem(R.drawable.ic_baseline_image_24, getString(R.string.open_gallery)));
        mRecyclerView = root.findViewById(R.id.recycler_view_camera);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mAdapter = new CameraAdapter(cameraList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new CameraAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                if (cameraList.get(position) == cameraList.get(0)) {
                    Intent takePicture = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(takePicture, 0);
                }
                if (cameraList.get(position) == cameraList.get(1)) {
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto, 1);
                }
            }
        });
    }

    public static boolean checkAndRequestPermissions(final Activity context) {
        int WExtstorePermission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int cameraPermission = ContextCompat.checkSelfPermission(context,
                Manifest.permission.CAMERA);
        List<String> listPermissionsNeeded = new ArrayList<>();
        if (cameraPermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded.add(Manifest.permission.CAMERA);
        }
        if (WExtstorePermission != PackageManager.PERMISSION_GRANTED) {
            listPermissionsNeeded
                    .add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(context, listPermissionsNeeded
                            .toArray(new String[listPermissionsNeeded.size()]),
                    REQUEST_ID_MULTIPLE_PERMISSIONS);
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_ID_MULTIPLE_PERMISSIONS:
                if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "FlagUp Requires Access to Camara.", Toast.LENGTH_SHORT)
                            .show();

                } else if (ContextCompat.checkSelfPermission(getActivity(),
                        Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getActivity().getApplicationContext(),
                            "FlagUp Requires Access to Your Storage.",
                            Toast.LENGTH_SHORT).show();

                } else {
                    chooseImage(getActivity());
                }
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode != RESULT_CANCELED) {
            switch (requestCode) {
                case 0:
                    if (resultCode == getActivity().RESULT_OK && data != null) {
                        Bitmap photo = (Bitmap) data.getExtras().get("data");
                        Bitmap rotateImage;
                        float degrees = 90; //rotation degree
                        Matrix matrix = new Matrix();
                        matrix.setRotate(degrees);
                        rotateImage = Bitmap.createBitmap(photo, 0, 0, photo.getWidth(), photo.getHeight(), matrix, true);
                        imageView = (ImageView) requireActivity().findViewById(R.id.camera_view);
                        imageView.setImageBitmap(rotateImage);
                    }
                    break;
                case 1:
                    if (resultCode == getActivity().RESULT_OK && data != null) {
                        Uri selectedImage = data.getData();
                        String[] filePathColumn = {MediaStore.Images.Media.DATA};
                        if (selectedImage != null) {
                            Cursor cursor = getActivity().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                            if (cursor != null) {
                                cursor.moveToFirst();

                                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                                String picturePath = cursor.getString(columnIndex);
                                imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));
                                cursor.close();
                            }
                        }

                    }
                    break;
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}