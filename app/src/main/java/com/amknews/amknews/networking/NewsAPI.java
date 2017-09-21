package com.amknews.amknews.networking;

/**
 * Created by alex on 9/21/17.
 */

public class NewsAPI {
    private static final String APIKEY = "c95773660c4042239ce4988c79606b65";
    private static final String APIPATH = "https://newsapi.org/v1/";

    public interface NewsService {
        @GET("articles?apiKey=" + APIKEY)
        Call<GetArticurnpointesResponse> getArticles(@Query("source") String source, @Query("sortBy") String sortBy);
    }
}
