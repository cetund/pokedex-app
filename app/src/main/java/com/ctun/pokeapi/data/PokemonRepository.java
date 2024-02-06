package com.ctun.pokeapi.data;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.ctun.pokeapi.data.model.EvolutionChain;
import com.ctun.pokeapi.data.model.Species;
import com.ctun.pokeapi.data.model.PokemonDetail;
import com.ctun.pokeapi.data.model.PokemonList;
import com.ctun.pokeapi.data.model.Results;
import com.ctun.pokeapi.data.model.TypeDetail;
import com.ctun.pokeapi.data.network.PokemonApiClient;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Singleton
public class PokemonRepository {

    @Inject
    PokemonApiClient apiClient;
    MutableLiveData<Boolean> isLoadingHome;
    MutableLiveData<Boolean> isRequestFailure;
    MutableLiveData<Boolean> isRefreshing;
    MutableLiveData<Boolean> isLoadingNextPage;
    MutableLiveData<Boolean> isLastPage;
    MutableLiveData<Boolean> isSearch;


    MutableLiveData<PokemonList> pokemonlist;
    MutableLiveData<Boolean> isLoading;
    MutableLiveData<PokemonDetail> pokemonDetail;

    MutableLiveData<Species> species;

    MutableLiveData<TypeDetail> typeDetail;

    MutableLiveData<EvolutionChain> evolutionChain;

    @Inject
    public PokemonRepository() {
        pokemonlist = new MutableLiveData<>();
        isLoadingHome = new MutableLiveData<>();
        isRequestFailure = new MutableLiveData<>();
        isRefreshing = new MutableLiveData<>();
        isLoadingNextPage = new MutableLiveData<>();
        isLastPage = new MutableLiveData<>();
        isSearch = new MutableLiveData<>();
        isLoading = new MutableLiveData<>();
        pokemonDetail = new MutableLiveData<>();
        species = new MutableLiveData<>();
        typeDetail = new MutableLiveData<>();
        evolutionChain = new MutableLiveData<>();
    }

    public LiveData<PokemonList> getAll(int offset) {
        Call<PokemonList> response = apiClient.getPokemonList(offset);
        isLoadingHome.postValue(true);

        response.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {

                if (response.body() != null) {
                    isLastPage.postValue(false);
                    isLoadingHome.postValue(false);
                    isRequestFailure.postValue(false);
                    pokemonlist.setValue(response.body());
                } else {
                    isLoadingHome.postValue(false);
                    isRequestFailure.postValue(true);
                }

            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {

                isLoadingHome.postValue(false);
                isRequestFailure.postValue(true);
                pokemonlist.setValue(null);
            }
        });


        return pokemonlist;
    }

    public LiveData<PokemonList> searchPokemon(String name) {
        Call<Results> call = apiClient.searchPokemon(name);

        isLoadingHome.postValue(true);
        call.enqueue(new Callback<Results>() {
            @Override
            public void onResponse(Call<Results> call, Response<Results> response) {
                isLoadingHome.postValue(false);
                isSearch.postValue(false);

                PokemonList auxList = new PokemonList();

                if (response.body() != null) {
                    isSearch.postValue(true);

                    List<Results> result = new ArrayList<>();
                    result.add(response.body());
                    auxList.setResultsList(result);

                    isRequestFailure.postValue(false);
                }

                pokemonlist.setValue(auxList);

            }

            @Override
            public void onFailure(Call<Results> call, Throwable t) {
                isRequestFailure.postValue(true);
                isSearch.postValue(false);
                isLoadingHome.postValue(false);
            }
        });


        return pokemonlist;
    }

    public LiveData<PokemonList> refreshList(int offset) {
        Call<PokemonList> call = apiClient.getPokemonList(offset);
        MutableLiveData<PokemonList> data = new MutableLiveData<>();
        isRefreshing.postValue(true);
        isSearch.postValue(false);
        call.enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
                if (response.body() != null) {
                    isLastPage.postValue(false);
                    pokemonlist.postValue(response.body());
                    isRefreshing.postValue(false);
                    isRequestFailure.postValue(false);
                } else {
                    isRefreshing.postValue(false);
                    isRequestFailure.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
                isRefreshing.postValue(false);
                isRequestFailure.postValue(true);
            }
        });

        return pokemonlist;
    }

    public LiveData<PokemonList> nextPage(int offset) {
        Call<PokemonList> call = apiClient.getPokemonList(offset);
        MutableLiveData<PokemonList> data = new MutableLiveData<>();
        isLoadingNextPage.postValue(true);
        call.enqueue(new Callback<PokemonList>() {
            @Override
            public void onResponse(Call<PokemonList> call, Response<PokemonList> response) {
                if (response.body() != null) {
                    if (response.body().getNext() == null) {
                        isLastPage.postValue(true);
                    }
                    pokemonlist.postValue(response.body());
                    isLoadingNextPage.postValue(false);
                    isRequestFailure.postValue(false);
                } else {
                    isLoadingNextPage.postValue(false);
                    isRequestFailure.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<PokemonList> call, Throwable t) {
                isLoadingNextPage.postValue(false);
                isRequestFailure.postValue(true);
            }
        });


        return pokemonlist;

    }

    public LiveData<PokemonDetail> getPokemonDetail(int id) {
        Call<PokemonDetail> response = apiClient.getPokemonDetail(id);

        isRequestFailure.postValue(false);
        isLoading.postValue(true);
        response.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<PokemonDetail> call, Response<PokemonDetail> response) {
                isLoading.postValue(false);

                if (response.body() != null) {
                    pokemonDetail.postValue(response.body());
                } else {
                    isRequestFailure.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<PokemonDetail> call, Throwable t) {
                isRequestFailure.postValue(true);
                isLoading.postValue(false);
            }
        });

        return pokemonDetail;
    }

    public LiveData<Species> getPokemonSpecies(int id) {
        Call<Species> flavorTextEntriesCall = apiClient.getPokemonSpecie(id);

        isRequestFailure.postValue(false);
        isLoading.postValue(true);
        flavorTextEntriesCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<Species> call, Response<Species> response) {
                isLoading.postValue(false);

                if (species != null) {
                    species.postValue(response.body());
                } else {
                    isRequestFailure.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<Species> call, Throwable t) {
                isRequestFailure.postValue(true);
                isLoading.postValue(false);
            }
        });

        return species;
    }

    public LiveData<TypeDetail> getPokemonDamageRelationship(int id) {
        Call<TypeDetail> damageRelationsCall = apiClient.getDamageRelations(id);

        isRequestFailure.postValue(false);
        isLoading.postValue(true);

        damageRelationsCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<TypeDetail> call, Response<TypeDetail> response) {
                isLoading.postValue(false);

                if (response.body() != null) {
                    typeDetail.postValue(response.body());
                } else {
                    isRequestFailure.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<TypeDetail> call, Throwable t) {
                isRequestFailure.postValue(true);
                isLoading.postValue(false);
            }
        });

        return typeDetail;

    }

    public LiveData<EvolutionChain> getPokemonEvolutionChain(int id) {
        Call<EvolutionChain> evolutionChainCall = apiClient.getEvolutionChain(id);
        isRequestFailure.postValue(false);
        isLoading.postValue(true);
        evolutionChainCall.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<EvolutionChain> call, Response<EvolutionChain> response) {
                isLoading.postValue(false);

                if (response.body() != null) {
                    evolutionChain.postValue(response.body());
                } else {
                    isRequestFailure.postValue(true);
                }
            }

            @Override
            public void onFailure(Call<EvolutionChain> call, Throwable t) {
                isRequestFailure.postValue(true);
                isLoading.postValue(false);
            }
        });


        return evolutionChain;
    }

    public LiveData<Boolean> getIsLoadingHome() {
        return isLoadingHome;
    }

    public LiveData<Boolean> getIsRequestFailure() {
        return isRequestFailure;
    }

    public LiveData<Boolean> getIsRefreshing() {
        return isRefreshing;
    }

    public LiveData<Boolean> getIsLoadingNextPage() {
        return isLoadingNextPage;
    }

    public LiveData<Boolean> getIsLastPage() {
        return isLastPage;
    }

    public LiveData<Boolean> getIsSearch() {
        return isSearch;
    }

    public LiveData<PokemonList> getPokemonList() {
        return pokemonlist;
    }

    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    public void setIsLoading(MutableLiveData<Boolean> isLoading) {
        this.isLoading = isLoading;
    }

    public MutableLiveData<PokemonDetail> getPokemonDetail() {
        return pokemonDetail;
    }

    public MutableLiveData<Species> getSpecies() {
        return species;
    }

    public MutableLiveData<TypeDetail> getTypeDetail() {
        return typeDetail;
    }

    public MutableLiveData<EvolutionChain> getEvolutionChain() {
        return evolutionChain;
    }


}
