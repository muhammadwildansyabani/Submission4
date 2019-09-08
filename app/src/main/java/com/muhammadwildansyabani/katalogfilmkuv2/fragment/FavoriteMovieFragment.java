package com.muhammadwildansyabani.katalogfilmkuv2.fragment;


import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.muhammadwildansyabani.katalogfilmkuv2.R;
import com.muhammadwildansyabani.katalogfilmkuv2.adapter.FavoriteMovieAdapter;

import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractMovie.MovieColumns.CONTENT_URI_MOVIE;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteMovieFragment extends Fragment {

    private Context context;
    private RecyclerView rvFavorite;
    private Cursor list;
    private FavoriteMovieAdapter adapter;
    private ProgressBar progressBar;



    public FavoriteMovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite_movie, container, false);
        context = view.getContext();

        rvFavorite = view.findViewById(R.id.rv_category);
        progressBar = view.findViewById(R.id.progressBar);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.favorite_movie);


        setupList();

        new loadMoviesAsync().execute();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        new loadMoviesAsync().execute();
    }

    private void setupList() {
        adapter = new FavoriteMovieAdapter(list);
        rvFavorite.setLayoutManager(new LinearLayoutManager(context));
        rvFavorite.setHasFixedSize(true);
        rvFavorite.setAdapter(adapter);
    }

    private class loadMoviesAsync extends AsyncTask<Void, Void, Cursor> {


        @Override
        protected Cursor doInBackground(Void... voids) {
            return context.getContentResolver().query(CONTENT_URI_MOVIE, null, null, null, null);

        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);

            list = cursor;
            adapter.replaceAll(list);
            progressBar.setVisibility(View.GONE);
        }
    }

    //
    @Override
    public void onDestroy() {
        super.onDestroy();
    }


}
