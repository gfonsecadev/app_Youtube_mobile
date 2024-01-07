package com.example.youtubeplay.retrofit;

import com.example.youtubeplay.model.Resultado;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ConsultaRetrofit {
    //Coloquei campo da query como chanellID e o certo é chanellId , fiz isso para procurar todos os videos do Youtube
    @GET("search")
    Call<Resultado> getRetrofit(@Query("part") String part,
                                @Query("channelID") String channelId,
                                @Query("maxResults") String maxResults,
                                @Query("date") String order,
                                @Query("key") String key,
                                @Query("q") String q);

}
