package com.example.android.popularmoviesstageone.utilities;

import android.util.Log;

import java.util.logging.Filter;

/**
 * Utilities to communicate with the Movies DB.
 */

public class NetworkUtils {
    private static final String TAG = NetworkUtils.class.getSimpleName();
    private static final String API_BASE_URL = "https://api.themoviedb.org/3/movie/";
    private static final String API_KEY_PARAM = "?api_key=";
    public static final String POPULAR = "popular";
    public static final String TOP_RATED = "top_rated";

    private static String[] filterTypes = new String[]{POPULAR, TOP_RATED};

    private static final String MOVIES_POPULAR_URL =
            "https://api.themoviedb.org/3/movie/popular?api_key=4c155f804dc423055d1497f3441a34fa";

    private static final String MOVIES_TOP_RATED_URL =
            "https://api.themoviedb.org/3/movie/top_rated?api_key=4c155f804dc423055d1497f3441a34fa";

    public static String getMoviesUrl(int apiFilter) {
        return API_BASE_URL + getFilterType(apiFilter) + API_KEY_PARAM + getApiKey();
    }

    private static String getFilterType(int filterPosition) {
        String filter = POPULAR;
        if (filterPosition < filterTypes.length) {
            filter = filterTypes[filterPosition];
        }
        Log.d("NetworkUtils", "Filter=" + filter);
        return filter;
    }

    public static String getApiKey() {
        return "4c155f804dc423055d1497f3441a34fa";
    }
}
