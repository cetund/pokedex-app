package com.ctun.pokeapi.domain;

import static com.ctun.pokeapi.utils.Constants.API_URL;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.only;
import static org.mockito.Mockito.verify;

import com.ctun.pokeapi.data.PokemonRepository;
import com.ctun.pokeapi.data.model.PokemonList;
import com.ctun.pokeapi.data.network.PokemonApiClient;
import com.ctun.pokeapi.utils.ApiServiceCallback;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


@RunWith(MockitoJUnitRunner.class)
public class GetPokemonListUseCaseTest {



    @Before
    public void onBefore(){
    }


    @Test
    public void whenTheApiReturnData() {
    }

    private PokemonApiClient getApiClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(logging);
        OkHttpClient client = okHttpClientBuilder.build();

        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(PokemonApiClient.class);
    }

}