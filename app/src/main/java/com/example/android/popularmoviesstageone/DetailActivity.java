package com.example.android.popularmoviesstageone;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toolbar;

import com.example.android.popularmoviesstageone.model.Movie;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public final int RATING_MAX = 5;

    @BindView(R.id.ivMovieImage) ImageView image;
    @BindView(R.id.tvOverview) TextView overview;
    @BindView(R.id.tvOriginalTitle) TextView originalTitle;
    @BindView(R.id.tvReleaseDate) TextView releaseDate;
    @BindView(R.id.rbRating) RatingBar rating;
    @BindView(R.id.detailToolbar)
    android.support.v7.widget.Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        // Enable up icon
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();

        if (intent.hasExtra("movie")) {
            Movie movie = (Movie) Parcels.unwrap(getIntent().getParcelableExtra("movie"));
            Picasso.with(this).load(movie.getBackdropPath()).into(image);
            overview.setText(movie.getOverview());
            originalTitle.setText(movie.getOriginalTitle());
            releaseDate.setText(movie.getReleaseDate());
            // to get the smaller rating bar
            //https://stackoverflow.com/questions/2874537/how-to-make-a-smaller-ratingbar
            rating.setRating((int) Math.round(movie.getVoteAverage()));

            /*
            String s = intent.getStringExtra(Intent.EXTRA_TEXT);
            ImageView imageView = findViewById(R.id.ivDetailMovieImage);

            String title = intent.getStringExtra("TITLE");
            TextView textView = findViewById(R.id.tvOriginalTitle);
            textView.setText(title);
            */

        }
    }
}
