package com.example.android.popularmoviesstageone;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.popularmoviesstageone.adapter.MovieArrayAdapter;
import com.example.android.popularmoviesstageone.model.Movie;
import com.example.android.popularmoviesstageone.utilities.NetworkUtils;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

/**
 * Main activity to display a grid of movie poster images.
 */
public class MovieActivity extends AppCompatActivity implements MovieArrayAdapter.MovieArrayAdapterOnClickHandler {

    ArrayList<Movie> movies;
    MovieArrayAdapter movieAdapter;
    String filter;

    @BindView(R.id.rvMovies) RecyclerView rvMovies;
    @BindView(R.id.tbToolbar) android.support.v7.widget.Toolbar tbToolbar;
    @BindView(R.id.spSortBy) Spinner spinner;

private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_movie);
        ButterKnife.bind(this);

        // Sets the toolbar to act as the ActionBar for this Activity window.
        if (tbToolbar != null) {
            setSupportActionBar(tbToolbar);
        }
        tbToolbar.setTitle(getResources().getString(R.string.app_name));
        initialize();
    }

    private void initialize() {
        movies = new ArrayList<>();
        movieAdapter = new MovieArrayAdapter(this, movies, this);
        rvMovies.setAdapter(movieAdapter);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(
                this,
                3,
                GridLayoutManager.VERTICAL,
                false
        );
        rvMovies.setLayoutManager(gridLayoutManager);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                int selected = adapterView.getSelectedItemPosition();
                fetch(selected);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        fetch(0);
    }

    private void fetch(int filter) {
        if (isOnline()) {
            AsyncHttpClient client = new AsyncHttpClient();
            client.get(NetworkUtils.getMoviesUrl(filter), new JsonHttpResponseHandler() {
                @Override
                public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                    JSONArray movieJsonResults = null;

                    try {
                        movieJsonResults = response.getJSONArray("results");
                        movies.clear();
                        movies.addAll(Movie.fromJSONArray(movieJsonResults));
                        Log.d("DEBUG", movies.toString());
                        movieAdapter.notifyDataSetChanged();
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                    super.onFailure(statusCode, headers, throwable, errorResponse);
                }
            });
        } else {
            Toast.makeText(this, getResources().getString(R.string.network_unavailable),
                    Toast.LENGTH_SHORT);
        }
    }

    /**
     * Check to make sure there is network connection
     * @return True if network is available, false otherwise.
     * Credit:
     * https://stackoverflow.com/questions/1560788/how-to-check-internet-access-on-android-inetaddress-never-times-out
     */
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnected();
    }

    @Override
    public void onClick(Movie movie) {
        Class destinationActivity = DetailActivity.class;
        Context context = MovieActivity.this;
        Intent intent = new Intent(context, destinationActivity);

        intent.putExtra(Movie.MOVIE_EXTRA, Parcels.wrap(movie));
        startActivity(intent);
    }
}
