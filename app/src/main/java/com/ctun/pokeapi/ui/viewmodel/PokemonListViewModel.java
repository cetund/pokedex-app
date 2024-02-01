package com.ctun.pokeapi.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ctun.pokeapi.data.model.PokemonGetAll;
import com.ctun.pokeapi.domain.GetPokemonListUseCase;
import com.ctun.pokeapi.utils.ApiServiceCallback;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PokemonListViewModel extends ViewModel {
    MutableLiveData<Boolean> isLoadingHome;
    MutableLiveData<PokemonGetAll> pokemonListData;
    MutableLiveData<Boolean> isRequestFailure;
    MutableLiveData<Boolean> isRefreshing;
    MutableLiveData<Boolean> isLoadingNextPage;
    @Inject
    GetPokemonListUseCase pokemonListUseCase;

    @Inject
    public PokemonListViewModel(){
        pokemonListData = new MutableLiveData<>();
        isLoadingHome = new MutableLiveData<>();
        isRequestFailure = new MutableLiveData<>();
        isRefreshing = new MutableLiveData<>();
        isLoadingNextPage = new MutableLiveData<>();
    }

    private int offset = 0;

    public void onCreate(){
        isLoadingHome.postValue(true);

        pokemonListUseCase.getAll(new ApiServiceCallback<>() {

            @Override
            public void onSuccess(PokemonGetAll response) {
                if(response!=null){
                    pokemonListData.postValue(response);
                    isLoadingHome.postValue(false);
                    isRequestFailure.postValue(false);
                }else {
                    isLoadingHome.postValue(false);
                    isRequestFailure.postValue(true);
                }

            }

            @Override
            public void onFailure(String error) {
                isLoadingHome.postValue(false);
                isRequestFailure.postValue(true);
            }
        }, 0);
    }

    public void refreshList(){
        isRefreshing.postValue(true);
        pokemonListUseCase.getAll(new ApiServiceCallback<>() {

            @Override
            public void onSuccess(PokemonGetAll response) {
                if(response!=null){
                    pokemonListData.postValue(response);
                    isRefreshing.postValue(false);
                    isRequestFailure.postValue(false);
                }else {
                    isRefreshing.postValue(false);
                    isRequestFailure.postValue(true);
                }

            }

            @Override
            public void onFailure(String error) {
                isRefreshing.postValue(false);
                isRequestFailure.postValue(true);
            }
        }, 0);
    }

    public void nextPage(int offset){
        pokemonListUseCase.getAll(new ApiServiceCallback<>() {

            @Override
            public void onSuccess(PokemonGetAll response) {
                if(response!=null){
                    pokemonListData.postValue(response);
                    isLoadingNextPage.postValue(false);
                    isRequestFailure.postValue(false);
                }else {
                    isLoadingNextPage.postValue(false);
                    isRequestFailure.postValue(true);
                }

            }

            @Override
            public void onFailure(String error) {
                isLoadingNextPage.postValue(false);
                isRequestFailure.postValue(true);
            }
        }, offset);
    }

    public MutableLiveData<PokemonGetAll> getPokemonListData() {
        return pokemonListData;
    }
    public MutableLiveData<Boolean> getIsLoadingHome(){
        return isLoadingHome;
    }

    public MutableLiveData<Boolean> getIsRequestFailure(){
        return isRequestFailure;
    }
    public MutableLiveData<Boolean> getIsRefreshing(){
        return isRefreshing;
    }

    public MutableLiveData<Boolean> getIsLoadingNextPage(){
        return isLoadingNextPage;
    }
}

