package com.ctun.pokeapi.ui.detailpokemon.view;

import static android.nfc.NfcAdapter.EXTRA_ID;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;

import com.ctun.pokeapi.R;
import com.ctun.pokeapi.databinding.ActivityPokemonDetailBinding;
import com.ctun.pokeapi.ui.detailpokemon.adapter.PokemonTabsDetailAdapter;
import com.ctun.pokeapi.ui.detailpokemon.viewmodel.PokemonDetailViewModel;
import com.ctun.pokeapi.ui.home.viewmodel.HomeViewModel;
import com.google.android.material.tabs.TabLayoutMediator;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PokemonDetailActivity extends AppCompatActivity {
    private ActivityPokemonDetailBinding binding;

    private PokemonDetailViewModel viewModel;

    private int idPokemon = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(PokemonDetailViewModel.class);
        binding = ActivityPokemonDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Bundle bundle = getIntent().getExtras();
        idPokemon = bundle.getInt(EXTRA_ID);

        initViewPager();

    }

    private void initViewPager() {
        FragmentManager fm = getSupportFragmentManager();
        PokemonTabsDetailAdapter sa = new PokemonTabsDetailAdapter(getApplicationContext(), fm, getLifecycle());
        StatsFragment statsFragment = StatsFragment.newInstance();
        AboutFragment aboutFragment = AboutFragment.newInstance(idPokemon);
        EvolutionFragment evolutionFragment = EvolutionFragment.newInstance();

        sa.addFragment(statsFragment);
        sa.addFragment(aboutFragment);
        sa.addFragment(evolutionFragment);
        binding.pager.setAdapter(sa);

        new TabLayoutMediator(binding.tabLayout, binding.pager,
                (tab, position) -> {
                    if (position == 0) {
                        tab.setText(R.string.stats);
                    }else if(position == 1){
                        tab.setText(R.string.about);
                    }else if(position == 2){
                        tab.setText(R.string.evolution);
                    }

                }
        ).attach();


    }
}