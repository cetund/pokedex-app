package com.ctun.pokeapi.ui.home.view;

import static android.nfc.NfcAdapter.EXTRA_ID;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;

import com.ctun.pokeapi.databinding.ActivityHomeBinding;
import com.ctun.pokeapi.ui.home.adapter.PokemonListAdapter;
import com.ctun.pokeapi.ui.detail.view.PokemonDetailActivity;
import com.ctun.pokeapi.ui.home.viewmodel.HomeViewModel;
import com.ctun.pokeapi.utils.MultiClickPreventer;
import com.ctun.pokeapi.utils.PaginationScrollListener;

import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class Home extends AppCompatActivity {
    private ActivityHomeBinding binding;
    private HomeViewModel viewModel;
    private PokemonListAdapter listAdapter;

    private Boolean isLoading = false;
    private Boolean isLastPage = false;

    private Boolean isSearch = false;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                if(isSearch){
                    refreshHome();
                }else{
                    finish();
                }
            }
        };

        getOnBackPressedDispatcher().addCallback(onBackPressedCallback);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        binding.recyclerPokemonList.setLayoutManager(gridLayoutManager);

        binding.recyclerPokemonList.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
            @Override
            protected void loadMoreItems() {
                if(listAdapter!=null){
                    isLoading = true;
                    currentPage = (listAdapter.getItemCount());
                    viewModel.nextPage(currentPage);
                }
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

            @Override
            public boolean isSearch() {
                return isSearch;
            }
        });


        viewModel.onCreate();
        viewModel.getPokemonListData().observe(this, response -> {
            if (listAdapter == null) {
                listAdapter = new PokemonListAdapter(this, response, (view, position, imageView) -> {
                    MultiClickPreventer.preventMultiClick(view);

                    ActivityOptionsCompat activityOptionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(this,
                            imageView,
                            "imageMain");

                    Intent detail = new Intent(Home.this, PokemonDetailActivity.class);
                    detail.putExtra(EXTRA_ID,  (position));
                    startActivity(detail, activityOptionsCompat.toBundle());
                });

                binding.recyclerPokemonList.setAdapter(listAdapter);
            } else {
                listAdapter.setData(response);
            }
            
            if(response.getResultsList()==null){
                binding.lytEmptyList.setVisibility(View.VISIBLE);
                binding.lytNoInternet.setVisibility(View.GONE);
            }else{
                binding.lytEmptyList.setVisibility(View.GONE);
            }

        });

        viewModel.getIsLoadingHome().observe(this, isLoading -> {
            binding.globalProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            binding.inputSearch.setVisibility(isLoading ? View.GONE : View.VISIBLE);
            binding.refreshHome.setVisibility(isLoading ? View.GONE : View.VISIBLE);
            binding.inputSearch.setEnabled(!isLoading);
        });

        viewModel.getIsRequestFailure().observe(this, isRequestFailure -> {
            binding.lytNoInternet.setVisibility(isRequestFailure ? View.VISIBLE : View.GONE);
            binding.recyclerPokemonList.setVisibility(isRequestFailure ? View.GONE : View.VISIBLE);

            if( isRequestFailure && binding.lytEmptyList.getVisibility() == View.VISIBLE){
                binding.lytEmptyList.setVisibility(View.GONE);
            }
        });

        binding.refreshHome.setOnRefreshListener(() -> {
            refreshHome();
        });

        viewModel.getIsRefreshing().observe(this, isRefreshing -> {
            binding.refreshHome.setRefreshing(isRefreshing);
        });

        viewModel.getIsLoadingNextPage().observe(this, isLoadingNextPage -> {
            isLoading = isLoadingNextPage;
            binding.progressBarNextPage.setVisibility(isLoading ? View.VISIBLE : View.GONE);
        });
        viewModel.getIsLastPage().observe(this, isLast -> {
            isLastPage = isLast;
        });

        binding.inputSearch.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                if(!binding.inputSearch.getText().toString().trim().isEmpty()){
                    clearList();
                    viewModel.searchPokemon(binding.inputSearch.getText().toString().trim().toLowerCase(Locale.getDefault()));
                }
            }
            return false;
        });

        viewModel.getIsSearch().observe(this, searched -> {
            isSearch = searched;
        });
        }

        private void clearList(){
            listAdapter = null;
            currentPage = 0;
        }

        private void refreshHome(){
            clearList();
            viewModel.refreshList();
        }
    }