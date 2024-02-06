package com.ctun.pokeapi.di;

import static com.ctun.pokeapi.utils.Constants.API_URL;

import com.ctun.pokeapi.data.PokemonRepository;
import com.ctun.pokeapi.data.network.PokemonApiClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class NetworkModule {

    @Singleton
    @Provides
    public Retrofit provideRetrofit() {
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

        return retrofit;
    }

    @Singleton
    @Provides
    public PokemonApiClient provideApiClient(Retrofit retrofit){
        return retrofit.create(PokemonApiClient.class);
    }


}
