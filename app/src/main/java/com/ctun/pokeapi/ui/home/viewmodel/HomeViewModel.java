package com.ctun.pokeapi.ui.home.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.ctun.pokeapi.data.PokemonRepository;
import com.ctun.pokeapi.data.model.PokemonList;
import com.ctun.pokeapi.domain.GetPokemonListUseCase;
import com.ctun.pokeapi.domain.SearchPokemonUseCase;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class HomeViewModel extends ViewModel {
    LiveData<PokemonList> pokemonListData;
    LiveData<Boolean> isLoadingHome;
    LiveData<Boolean> isRequestFailure;
    LiveData<Boolean> isRefreshing;
    LiveData<Boolean> isLoadingNextPage;
    LiveData<Boolean> isLastPage;
    LiveData<Boolean> isSearch;

    @Inject
    GetPokemonListUseCase pokemonListUseCase;
    @Inject
    SearchPokemonUseCase searchPokemonUseCase;


    @Inject
    public HomeViewModel(PokemonRepository repository){
        pokemonListData = repository.getPokemonList();
        isLoadingHome =repository.getIsLoadingHome();
        isRequestFailure = repository.getIsRequestFailure();
        isRefreshing = repository.getIsRefreshing();
        isLoadingNextPage = repository.getIsLoadingNextPage();
        isLastPage = repository.getIsLastPage();
        isSearch = repository.getIsSearch();
    }

    public void onCreate(){
        pokemonListData = pokemonListUseCase.getAll(0);
    }

    public void refreshList(){
        pokemonListData = pokemonListUseCase.refreshList(0);
    }

    public void nextPage(int offset){
        pokemonListData = pokemonListUseCase.nextPage(offset);
    }

    public void searchPokemon(String name){
        searchPokemonUseCase.searchPokemon(name);
    }
    public LiveData<PokemonList> getPokemonListData() {
        return pokemonListData;
    }

    public LiveData<Boolean> getIsLoadingHome(){
        return isLoadingHome;
    }

    public LiveData<Boolean> getIsRequestFailure(){
        return isRequestFailure;
    }
    public LiveData<Boolean> getIsRefreshing(){
        return isRefreshing;
    }

    public LiveData<Boolean> getIsLoadingNextPage(){
        return isLoadingNextPage;
    }

    public LiveData<Boolean> getIsLastPage(){
        return isLastPage;
    }

    public LiveData<Boolean> getIsSearch(){
        return isSearch;
    }
}

