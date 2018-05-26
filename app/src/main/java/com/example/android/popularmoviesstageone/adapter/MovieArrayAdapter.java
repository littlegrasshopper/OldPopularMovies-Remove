package com.example.android.popularmoviesstageone.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.popularmoviesstageone.R;
import com.example.android.popularmoviesstageone.model.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fonda on 5/2/18.
 */

public class MovieArrayAdapter /*extends ArrayAdapter<Movie> {*/ extends
        RecyclerView.Adapter<MovieArrayAdapter.MovieViewHolder> {

    public static final String TAG = MovieArrayAdapter.class.getSimpleName();

    public interface MovieArrayAdapterOnClickHandler {
        void onClick(Movie m);
    }

    private Context mContext;

    private final MovieArrayAdapterOnClickHandler mClickHandler;

    private List<Movie> mMovies;

    public MovieArrayAdapter(Context context, List<Movie> movies, MovieArrayAdapterOnClickHandler clickHandler) {
        //super(context, android.R.layout.simple_list_item_1, movies);
        mContext = context;
        mClickHandler = clickHandler;
        mMovies = movies;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View movieView = inflater.inflate(R.layout.item_movie, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(movieView);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        Movie movie = mMovies.get(position);

        // find the image view
       //TextView overView = (TextView) holder.overView;
        //TextView titleView = (TextView) holder.titleView;
       ImageView imageView = (ImageView) holder.ivImage;

        Picasso.with(mContext).load(movie.getPosterPath()).into(imageView);
        //holder.bind(position);*/

      // overView.setText(movie.getOverview());
       //titleView.setText(movie.getOriginalTitle());
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }


    /**
     * Private ViewHolder class
     */
    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        /* ButterKnife binding */
        @BindView(R.id.ivMovieImage) ImageView ivImage;
        //@BindView(R.id.tvTitle) TextView tvTitle;
        //@BindView(R.id.tvOverview) TextView tvOverview;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int adapterPosition = getAdapterPosition();
            Movie movie = mMovies.get(adapterPosition);
            mClickHandler.onClick(movie);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    /*
    @NonNull
    @Override

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // get the data item for position
        Movie movie = getItem(position);

        // check the existing view being reused
        ViewHolder viewHolder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.item_movie, parent, false);
            viewHolder = new ViewHolder(convertView);

            viewHolder.imageView.setImageResource(0);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // find the image view
        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);

        // clear out the image from convertView
        ivImage.setImageResource(0);

        TextView tvTitle = (TextView) convertView.findViewById(R.id.tvTitle);
        TextView tvOverview = (TextView) convertView.findViewById(R.id.tvOverview);

        // populate data
        tvTitle.setText(movie.getOriginalTitle());
        tvOverview.setText(movie.getOverview());

        Picasso.with(getContext()).load(movie.getPosterPath()).into(viewHolder.imageView);
        return convertView;
    }
*/
}
