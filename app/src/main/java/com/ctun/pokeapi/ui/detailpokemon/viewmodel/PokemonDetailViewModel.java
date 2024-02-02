package com.ctun.pokeapi.ui.detailpokemon.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ctun.pokeapi.data.PokemonRepository;
import com.ctun.pokeapi.data.model.PokemonDetail;
import com.ctun.pokeapi.domain.GetPokemonDetailUseCase;
import com.ctun.pokeapi.utils.ApiServiceCallback;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PokemonDetailViewModel extends ViewModel {
    @Inject
    GetPokemonDetailUseCase getPokemonDetailUseCase;

    private MutableLiveData<PokemonDetail> detail;
    private MutableLiveData<Boolean> isLoading;
    private MutableLiveData<Boolean> isRequestFailure;

    @Inject
    public PokemonDetailViewModel() {

        detail = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
        isRequestFailure = new MutableLiveData<>();
    }

    public void getPokemonDetail(int id) {
        isRequestFailure.postValue(false);
        isLoading.postValue(true);
        getPokemonDetailUseCase.getPokemonDetail(new ApiServiceCallback<>() {
            @Override
            public void onSuccess(PokemonDetail pokemonDetail) {
                isLoading.postValue(false);
                Log.d("ViewModelSuccess", "" + pokemonDetail.getAbilities().size());

                if (pokemonDetail != null) {
                    detail.postValue(pokemonDetail);
                } else {
                    isRequestFailure.postValue(true);
                }
            }

            @Override
            public void onFailure(String error) {
                Log.d("ViewModelFailure", "" + error);
                isRequestFailure.postValue(true);
                isLoading.postValue(false);
            }
        }, id);
    }

    public MutableLiveData<PokemonDetail> getDetail(){
        return detail;
    }

    public MutableLiveData<Boolean> getIsLoading(){
        return isLoading;
    }

}
