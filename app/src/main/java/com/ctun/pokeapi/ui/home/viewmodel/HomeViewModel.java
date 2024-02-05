package com.ctun.pokeapi.ui.home.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ctun.pokeapi.data.model.PokemonList;
import com.ctun.pokeapi.data.model.Results;
import com.ctun.pokeapi.domain.GetPokemonListUseCase;
import com.ctun.pokeapi.domain.SearchPokemonUseCase;
import com.ctun.pokeapi.utils.ApiServiceCallback;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.xml.transform.Result;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    MutableLiveData<Boolean> isLoadingHome;
    MutableLiveData<PokemonList> pokemonListData;
    MutableLiveData<Boolean> isRequestFailure;
    MutableLiveData<Boolean> isRefreshing;
    MutableLiveData<Boolean> isLoadingNextPage;
    MutableLiveData<Boolean> isLastPage;
    MutableLiveData<Boolean> isSearch;

    @Inject
    GetPokemonListUseCase pokemonListUseCase;
    @Inject
    SearchPokemonUseCase searchPokemonUseCase;

    @Inject
    public HomeViewModel(){
        pokemonListData = new MutableLiveData<>();
        isLoadingHome = new MutableLiveData<>();
        isRequestFailure = new MutableLiveData<>();
        isRefreshing = new MutableLiveData<>();
        isLoadingNextPage = new MutableLiveData<>();
        isLastPage = new MutableLiveData<>();
        isSearch = new MutableLiveData<>();
    }

    private int offset = 0;

    public void onCreate(){
        isLoadingHome.postValue(true);

        pokemonListUseCase.getAll(new ApiServiceCallback<>() {

            @Override
            public void onSuccess(PokemonList response) {
                if(response!=null){
                    isLastPage.postValue(false);
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
        isSearch.postValue(false);
        pokemonListUseCase.getAll(new ApiServiceCallback<>() {

            @Override
            public void onSuccess(PokemonList response) {
                if(response!=null){
                    isLastPage.postValue(false);
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

        isLoadingNextPage.postValue(true);
        pokemonListUseCase.getAll(new ApiServiceCallback<>() {

            @Override
            public void onSuccess(PokemonList response) {
                if(response!=null){
                    if(response.getNext()==null){
                        isLastPage.postValue(true);
                    }
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

    public void searchPokemon(String name){
        isLoadingHome.postValue(true);
        searchPokemonUseCase.searchPokemon(new ApiServiceCallback<>() {
            @Override
            public void onSuccess(Results results) {
                isLoadingHome.postValue(false);
                isSearch.postValue(false);

                PokemonList auxList = new PokemonList();

                if(results!=null){
                    isSearch.postValue(true);
                    List<Results> result = new ArrayList<>();
                    result.add(results);
                    auxList.setResultsList(result);
                    isRequestFailure.postValue(false);
                }

                pokemonListData.postValue(auxList);

            }

            @Override
            public void onFailure(String error) {
                isRequestFailure.postValue(true);
                isSearch.postValue(false);
                isLoadingHome.postValue(false);
            }
        }, name);
    }

    public MutableLiveData<PokemonList> getPokemonListData() {
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

    public MutableLiveData<Boolean> getIsLastPage(){
        return isLastPage;
    }

    public MutableLiveData<Boolean> getIsSearch(){
        return isSearch;
    }
}

