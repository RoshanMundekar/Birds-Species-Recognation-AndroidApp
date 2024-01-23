package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.pdf.PdfRenderer;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.widget.ImageView;

public class pdfread extends AppCompatActivity {
    private ImageView pdfImageView;
    private PdfRenderer pdfRenderer;
    private PdfRenderer.Page currentPage;
    private ParcelFileDescriptor parcelFileDescriptor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pdfread);
        getSupportActionBar().hide();
        pdfImageView = findViewById(R.id.pdfImageView);






    }
}