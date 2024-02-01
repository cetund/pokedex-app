package com.ctun.pokeapi.ui.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ctun.pokeapi.data.model.PokemonGetAll;
import com.ctun.pokeapi.databinding.ActivityMainBinding;
import com.ctun.pokeapi.ui.adapter.PokemonListAdapter;
import com.ctun.pokeapi.ui.viewmodel.PokemonListViewModel;
import com.ctun.pokeapi.utils.PaginationScrollListener;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PokemonList extends AppCompatActivity {
    private ActivityMainBinding binding;
    private PokemonListViewModel viewModel;
    private PokemonListAdapter listAdapter;
    private PokemonGetAll pokemonGetAll;

    private Boolean isLoading = false;
    private Boolean isLastPage = false;
    private int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModel = new ViewModelProvider(this).get(PokemonListViewModel.class);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);

        binding.recyclerPokemonList.setLayoutManager(gridLayoutManager);

        binding.recyclerPokemonList.addOnScrollListener(new PaginationScrollListener(gridLayoutManager) {
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                Log.d("loading", "load more!");

                currentPage = (listAdapter.getItemCount() );
                viewModel.nextPage(currentPage);
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });




        viewModel.onCreate();
        viewModel.getPokemonListData().observe(this, response -> {
            if(listAdapter==null){
                listAdapter = new PokemonListAdapter(this, response);
                binding.recyclerPokemonList.setAdapter(listAdapter);
            }else{
                listAdapter.setData(response);
            }

        } );

        viewModel.getIsLoadingHome().observe(this, isLoading -> {
            binding.globalProgressBar.setVisibility(isLoading ? View.VISIBLE : View.GONE);
            binding.inputSearch.setVisibility(isLoading ? View.GONE: View.VISIBLE);
            binding.refreshHome.setVisibility(isLoading ? View.GONE: View.VISIBLE);
        });

        viewModel.getIsRequestFailure().observe(this, isRequestFailure -> {
            binding.lytNoInternet.setVisibility(isRequestFailure ? View.VISIBLE : View.GONE);
            binding.recyclerPokemonList.setVisibility(isRequestFailure ? View.GONE : View.VISIBLE);
        });

        binding.refreshHome.setOnRefreshListener(() -> {
            listAdapter = null;
            currentPage = 0;
            viewModel.refreshList();
        });

        viewModel.getIsRefreshing().observe(this, isRefreshing -> {
            binding.refreshHome.setRefreshing(isRefreshing);
        });

        viewModel.getIsLoadingNextPage().observe(this, isLoadingNextPage -> {
            isLoading = isLoadingNextPage;
            binding.progressBarNextPage.setVisibility(isLoadingNextPage ? View.VISIBLE: View.GONE);
        });
    }
}