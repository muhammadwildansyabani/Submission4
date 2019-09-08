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
import com.muhammadwildansyabani.katalogfilmkuv2.adapter.FavoriteTvShowAdapter;

import static com.muhammadwildansyabani.katalogfilmkuv2.db.DatabaseContractTvShow.TvShowColumns.CONTENT_URI_TV_SHOW;

/**
 * A simple {@link Fragment} subclass.
 */
public class FavoriteTvShowFragment extends Fragment {

    private Context context;
    private RecyclerView rvFavorite;
    private Cursor list;
    private FavoriteTvShowAdapter adapter;
    private ProgressBar progressBar;


    public FavoriteTvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_favorite_tv_show, container, false);
        context = view.getContext();

        rvFavorite = view.findViewById(R.id.rv_category);
        progressBar = view.findViewById(R.id.progressBar);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(R.string.favorite_tv_show);


        setupList();

        new FavoriteTvShowFragment.loadTvShowsAsync().execute();
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
        new FavoriteTvShowFragment.loadTvShowsAsync().execute();
    }

    private void setupList() {
        adapter = new FavoriteTvShowAdapter(list);
        rvFavorite.setLayoutManager(new LinearLayoutManager(context));
        rvFavorite.setHasFixedSize(true);
        rvFavorite.setAdapter(adapter);
    }

    private class loadTvShowsAsync extends AsyncTask<Void, Void, Cursor> {


        @Override
        protected Cursor doInBackground(Void... voids) {
            return context.getContentResolver().query(CONTENT_URI_TV_SHOW, null, null, null, null);

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
