package com.example.android.popularmoviesstageone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.android.popularmoviesstageone.model.Movie;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Activity for displaying details of a movie.
 */
public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.ivMovieImage) ImageView image;
    @BindView(R.id.tvOverview) TextView overview;
    @BindView(R.id.tvOriginalTitle) TextView originalTitle;
    @BindView(R.id.tvReleaseDate) TextView releaseDate;
    @BindView(R.id.rbRating) RatingBar rating;
    @BindView(R.id.detailToolbar) android.support.v7.widget.Toolbar toolbar;

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

        Intent intent = getIntent();

        if (intent.hasExtra(Movie.MOVIE_EXTRA)) {
            Movie movie = Parcels.unwrap(getIntent().getParcelableExtra(Movie.MOVIE_EXTRA));
            Picasso.with(this).load(movie.getBackdropPath()).into(image);
            overview.setText(movie.getOverview());
            originalTitle.setText(movie.getOriginalTitle());
            releaseDate.setText(movie.getReleaseDate());
            // to get the smaller rating bar
            //Credit: https://stackoverflow.com/questions/2874537/how-to-make-a-smaller-ratingbar
            rating.setRating((int) Math.round(movie.getVoteAverage()));
        }
    }
}