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

    /**
     * Return the movieDB url for the given filter.
     * @param apiFilter Integer representing the supported filter types.
     * @return URL string for the movieDB API.
     */
    public static String getMoviesUrl(int apiFilter) {
        return API_BASE_URL + getFilterType(apiFilter) + API_KEY_PARAM + getApiKey();
    }

    private static String getFilterType(int filterPosition) {
        String filter = POPULAR;
        if (filterPosition < filterTypes.length) {
            filter = filterTypes[filterPosition];
        }
        Log.d(TAG, "Filter=" + filter);
        return filter;
    }

    /**
     * Returns themoveDB API key.
     * @return String representing the API key.
     */
    public static String getApiKey() {
        return "";
    }
}
