package com.example.myapplication.ui.slideshow;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ExampleAdapter6;
import com.example.myapplication.ExampleItem6;
import com.example.myapplication.R;
import com.example.myapplication.UrlLinks;
import com.example.myapplication.databinding.FragmentSlideshowBinding;
import com.example.myapplication.jSOnClassforData;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    EditText Searchtext;
    private ExampleAdapter6 adapter;
    ImageButton bt_mic;
    private List<ExampleItem6> exampleList;
    private List<ExampleItem6> examples;

    private FragmentSlideshowBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        SlideshowViewModel slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);

        View root = FragmentSlideshowBinding.inflate(inflater, container, false).getRoot();

        fillExampleList();
        this.Searchtext = (EditText) root.findViewById(R.id.search_input);
        this.Searchtext.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            public void afterTextChanged(Editable editable) {
                filterQuery(editable.toString());
            }
        });

        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) RecyclerView recyclerView = (RecyclerView) root.findViewById( R.id.RecyclerView6);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        this.adapter = new ExampleAdapter6(exampleList,getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(this.adapter);





        return root;
    }
    private void fillExampleList() {
        exampleList = new ArrayList();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        String url = UrlLinks.getALLbirds;



        List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(1);
//
        String result = null;
        try {
            result = jSOnClassforData.forCallingStringAndreturnSTring(url, nameValuePairs);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            JSONArray jsonArray = new JSONArray(result);
            for (int i = 0; i < jsonArray.length(); i++) {


                String NAME = String.valueOf(jsonArray.getJSONArray(i).getString(1));
                String KINGDOM = String.valueOf(jsonArray.getJSONArray(i).getString(2));
                String PHYLUM = String.valueOf(jsonArray.getJSONArray(i).getString(3));
                String CLASS = String.valueOf(jsonArray.getJSONArray(i).getString(4));
                String ORDER = String.valueOf(jsonArray.getJSONArray(i).getString(5));

                String FAMILY = String.valueOf(jsonArray.getJSONArray(i).getString(6));
                String GENUS = String.valueOf(jsonArray.getJSONArray(i).getString(7));
                String SPECIES = String.valueOf(jsonArray.getJSONArray(i).getString(8));
                String DESCRIPTION = String.valueOf(jsonArray.getJSONArray(i).getString(9));
                String IMAGE = String.valueOf(jsonArray.getJSONArray(i).getString(10));

                String stUrl =UrlLinks.urlserverpython+"/static/birds/"+IMAGE;
                Bitmap bitmap = null;
                try {
                    InputStream inputStream = new java.net.URL(stUrl).openStream();
                    bitmap = BitmapFactory.decodeStream(inputStream);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                exampleList.add(new ExampleItem6(NAME,KINGDOM,PHYLUM,CLASS,ORDER,FAMILY,GENUS,SPECIES,bitmap,DESCRIPTION));

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    /* access modifiers changed from: private */
    public void filterQuery(String text) {
        ArrayList<ExampleItem6> filterdNames = new ArrayList<>();
        for (ExampleItem6 s : exampleList) {
            if (s.getmText1().toLowerCase().contains(text) || s.getmText2().toLowerCase().contains(text)) {
                filterdNames.add(s);
            }
        }
        this.adapter.setFilter(filterdNames);
    }

}