package com.example.youtubeplay.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.example.youtubeplay.R;
import com.example.youtubeplay.adapter.AdapterVideo;
import com.example.youtubeplay.api.ApiDados;
import com.example.youtubeplay.model.Items;
import com.example.youtubeplay.model.Resultado;
import com.example.youtubeplay.retrofit.ConsultaRetrofit;
import com.example.youtubeplay.retrofit.RetrofitDados;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity{

    private List<Items> listVideo=new ArrayList<>();
    private RecyclerView recyclerView;

    private Retrofit retrofit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView=findViewById(R.id.recyclerVideos);
        getSupportActionBar().setTitle("YouTube");
        retrofit= RetrofitDados.getRetrofit();

        carregarListaVideos("");





    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_search,menu);
         SearchView searchView= (SearchView) menu.findItem(R.id.search_menu).getActionView();

        

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                carregarListaVideos(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        searchView.setOnSearchClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //searchView.setBackgroundColor(getColor(R.color.white));

            }
        });


        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
               //searchView.setBackgroundColor(getColor(R.color.red));
                carregarListaVideos("");
                return false;
            }
        });





        return super.onCreateOptionsMenu(menu);
    }

    public void carregarListaVideos(String pesquisa){

        String q=pesquisa.replaceAll(" ","+");
        ConsultaRetrofit consultaRetrofit=retrofit.create(ConsultaRetrofit.class);

        consultaRetrofit.getRetrofit("snippet",ApiDados.CANAL_ID,"20","date",ApiDados.API_KEY,q).enqueue(new Callback<Resultado>() {
            @Override
            public void onResponse(Call<Resultado> call, Response<Resultado> response) {
                if (response.isSuccessful()){
                    listVideo=response.body().items;
                    carregarRecycler();
                    Log.d("resposta","resposta: "+response.toString());

                } else if (response.code()==403)  {
                    AlertDialog.Builder alertDialog=new AlertDialog.Builder(MainActivity.this);
                    alertDialog.setTitle("Erro 403: ");
                    alertDialog.setMessage("Cota diaria de acesso a API do Youtube excedida.Volte amanhã!").create();
                    alertDialog.setCancelable(false);
                    alertDialog.setPositiveButton("Sair do Aplicativo", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            finish();
                            System.exit(0);
                        }
                    }).show();

                }



            }

            @Override
            public void onFailure(Call<Resultado> call, Throwable t) {
                Log.d("reposta","Deu erro aqui:"+t.getMessage());


            }
        });



    }

    public void carregarRecycler(){
        AdapterVideo videoAdapter=new AdapterVideo(listVideo,this);

        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(videoAdapter);
    }
}