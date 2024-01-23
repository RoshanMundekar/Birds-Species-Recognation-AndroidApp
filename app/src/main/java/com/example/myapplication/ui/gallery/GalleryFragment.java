package com.example.myapplication.ui.gallery;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentGalleryBinding;
import com.example.myapplication.pdfread;
import com.example.myapplication.register;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

public class GalleryFragment extends Fragment {

    private Button Download;

    private String filepath = "https://www.birdlife.org/wp-content/uploads/2022/09/SOWB2022_EN_compressed.pdf";
    private URL url = null;
    private String fileName;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        GalleryViewModel galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        View root = FragmentGalleryBinding.inflate(inflater, container, false).getRoot();

        initViews(root);
        setListeners();

        return root;
    }


    private void initViews(View root) {


        Download = root.findViewById(R.id.download1);

        try {
            url = new URL(filepath);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        fileName = url.getPath();
        fileName = fileName.substring(fileName.lastIndexOf('/') + 1);
        // tvFileName.setText(fileName);
    }

    private void setListeners() {

        Download.setOnClickListener(v -> {
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url + ""));
            request.setTitle(fileName);
            request.setMimeType("application/pdf");
            request.allowScanningByMediaScanner();
            request.setAllowedOverMetered(true);
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
            request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName);
            DownloadManager dm = (DownloadManager) requireActivity().getSystemService(Context.DOWNLOAD_SERVICE);
            dm.enqueue(request);
        });


    }




}