package com.example.youtubeplay.retrofit;

import com.example.youtubeplay.api.ApiDados;

import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitDados {

        public static  Retrofit getRetrofit(){
            return new Retrofit.Builder()
                    .baseUrl(ApiDados.URL_BASE)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

}
