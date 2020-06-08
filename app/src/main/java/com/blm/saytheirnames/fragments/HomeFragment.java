package com.blm.saytheirnames.fragments;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blm.saytheirnames.R;
import com.blm.saytheirnames.adapters.PeopleAdapter;
import com.blm.saytheirnames.models.People;
import com.blm.saytheirnames.models.PeopleData;
import com.blm.saytheirnames.network.BackendInterface;
import com.blm.saytheirnames.network.Utils;
import com.blm.saytheirnames.utils.CustomTabUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private static final String ARG_TEXT = "arg_text";
    private static final String ARG_COLOR = "arg_color";

    private String mText;
    private int mColor;

    private View mContent;
    //private TextView mTextView;

    private RecyclerView personRecyclerView;
    private LinearLayoutManager layoutManager;
    private PeopleAdapter peopleAdapter;

    private List<People> peopleArrayList;
    private ProgressBar progressBar;
    private ImageView imageView;
    private ImageButton searchBtn;

    Resources resources;

    public static HomeFragment newInstance() {
        return new HomeFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContent = inflater.inflate(R.layout.fragment_home, container, false);

        resources = getResources();

        bindViews();
        setupPeopleRecyclerView();

        return mContent;
    }

    private void bindViews() {
        imageView = mContent.findViewById(R.id.imageView);
        personRecyclerView = mContent.findViewById(R.id.personRecyclerView);
        progressBar = mContent.findViewById(R.id.progressBar);
        searchBtn = mContent.findViewById(R.id.searchButton);

        imageView.setOnClickListener(view -> {
            if (validateUrl("http://google.com")) {
                visitPage("http://google.com");
            } else {
                Toast.makeText(getContext(), "Error with link", Toast.LENGTH_SHORT).show();
            }
        });

        searchBtn.setOnClickListener(view -> {
            //TODO: Handle search function
        });
    }

    private void setupPeopleRecyclerView() {
        peopleArrayList = new ArrayList<>();
        peopleAdapter = new PeopleAdapter(peopleArrayList, getActivity());

        layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        personRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        personRecyclerView.setAdapter(peopleAdapter);

        loadData();
    }

    private boolean validateUrl(String url) {
        return url != null && url.length() > 0 && (url.startsWith("http://") || url.startsWith("https://"));
    }

    private void visitPage(String url) {
        CustomTabUtil.openCustomTabForUrl(getActivity(), url);
    }

    private void loadData() {
        @SuppressLint("StaticFieldLeak") AsyncTask<Void, Void, Void> getPeople = new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            protected Void doInBackground(Void... params) {
                BackendInterface backendInterface = Utils.getBackendService();
                backendInterface.getPeople().enqueue(new Callback<PeopleData>() {
                    @Override
                    public void onResponse(@NonNull Call<PeopleData> call, @NonNull Response<PeopleData> response) {
                        peopleArrayList.clear();
                        Log.d("API_Response", response.body().toString());
                        List<People> body = response.body().getData();

                        peopleArrayList.addAll(body);
                        progressBar.setVisibility(View.GONE);
                        personRecyclerView.setVisibility(View.VISIBLE);

                        peopleAdapter.notifyDataSetChanged();
                    }

                    @Override
                    public void onFailure(Call<PeopleData> call, Throwable t) {

                    }
                });
                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
            }
        };
        getPeople.execute(null, null, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // initialize views
        mContent = view.findViewById(R.id.navigation_donation);

        // retrieve text and color from bundle or savedInstanceState
        /*if (savedInstanceState == null) {
            Bundle args = getActivity().getIntent().getExtras();
            assert args != null;
            mText = args.getString(ARG_TEXT);
            //mColor = args.getInt(ARG_COLOR);
        } else {
            mText = savedInstanceState.getString(ARG_TEXT);
            // mColor = savedInstanceState.getInt(ARG_COLOR);
        }*/

        Bundle bundle = requireActivity().getIntent().getExtras();
        if (bundle != null) {
            //mTextView.setText(" "+bundle.getString("arg_text"));
        }

        // set text and background color
        // mTextView.setText(text);
        //mContent.setBackgroundColor(mColor);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(ARG_TEXT, mText);
        // outState.putInt(ARG_COLOR, mColor);
        super.onSaveInstanceState(outState);
    }
}