package com.muhammadwildansyabani.katalogfilmkuv2.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.muhammadwildansyabani.katalogfilmkuv2.BuildConfig;
import com.muhammadwildansyabani.katalogfilmkuv2.R;
import com.muhammadwildansyabani.katalogfilmkuv2.adapter.MovieAdapter;
import com.muhammadwildansyabani.katalogfilmkuv2.click.ItemClickSupport;
import com.muhammadwildansyabani.katalogfilmkuv2.detail.DetailActivityMovie;
import com.muhammadwildansyabani.katalogfilmkuv2.entity.MovieParcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {

    private RecyclerView mListview;
    private View view;
    private RecyclerView.Adapter movieAdapter;
    private ProgressBar mProgressBar;
    private List<MovieParcelable> movie;
    private static final String STATE_RESULT = "state_result";

    final String STATE_TITLE = "state_string";
    final String STATE_LIST = "state_list";
    final String STATE_MODE = "state_mode";
    int mode;

    private static final String API_KEY = BuildConfig.TMDB_API_KEY;


    public MovieFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_movie, container, false);
        mListview=view.findViewById(R.id.rv_category);
        mListview.setHasFixedSize(true);
        mListview.setLayoutManager(new LinearLayoutManager(getActivity()));
        movie=new ArrayList<>();

        if(getActivity() != null) {
            ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.movies);
        }


        mProgressBar=view.findViewById(R.id.progressBar);
        loadinBackground();
        return view;
    }

    String urlDiscoverMovie="https://api.themoviedb.org/3/discover/movie?api_key="+API_KEY+"&language=en-US";

    public void loadinBackground(){
        final ArrayList<MovieParcelable>moviemodels = new ArrayList<>();
        StringRequest stringRequest=new StringRequest(Request.Method.GET, urlDiscoverMovie, new Response.Listener<String>() {
            @Override
            public void onResponse(String responseBody) {

                try {

                    JSONObject responseObject = new JSONObject(responseBody);
                    final JSONArray list = responseObject.getJSONArray("results");

                    for (int i = 0; i < list.length(); i++) {
                        JSONObject object = list.getJSONObject(i);
                        MovieParcelable movieModel = new MovieParcelable(object);
                        moviemodels.add(movieModel);
                    }
                    movieAdapter=new MovieAdapter(moviemodels,getActivity());
                    mListview.setAdapter(movieAdapter);

                    ItemClickSupport.addTo(mListview).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
                        @Override
                        public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                            gass(moviemodels.get(position));
                        }
                    });

                    mProgressBar.setVisibility(View.GONE);
                    if(mProgressBar.getVisibility()==View.GONE){
                        mListview.setVisibility(View.VISIBLE);}

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(getActivity(), "Error" + error.toString(), Toast.LENGTH_SHORT).show();
            }
        }
        );

        if(getActivity() != null) {
            RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
            requestQueue.add(stringRequest);
        }

    }

    private void gass(MovieParcelable movieModel){
        Intent misiMovie= new Intent(getActivity(), DetailActivityMovie.class);
        misiMovie.putExtra("movie",movieModel);
        startActivity(misiMovie);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(STATE_TITLE,((AppCompatActivity)getActivity()).getSupportActionBar().getTitle().toString());
        outState.putParcelableArrayList(STATE_LIST, (ArrayList<? extends Parcelable>) movie);
        outState.putInt(STATE_MODE,mode);
    }

}
