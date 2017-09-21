package com.amknews.amknews.networking;

import com.amknews.amknews.model.GetArticlesResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by alex on 9/21/17.
 */

public class NewsAPI {
    private static final String APIKEY = "c95773660c4042239ce4988c79606b65";
    private static final String APIPATH = "https://newsapi.org/v1/";

    private static NewsService newsService = null;

    public static NesService getApi() {
        if(newsService == null) {
            // initialize NewsService
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(APIPATH)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            newsService = retrofit.create(NewsService.class);
        }
        return newsService;
    }

    public interface NewsService {
        @GET("articles?apiKey=" + APIKEY)
        Call<GetArticlesResponse> getArticles(@Query("source") String source, @Query("sortBy") String sortBy);
    }
}
