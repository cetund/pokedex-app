package com.ctun.pokeapi.ui.detailpokemon.view;

import static android.nfc.NfcAdapter.EXTRA_ID;

import static com.ctun.pokeapi.utils.Constants.POKEMON_IMAGES_URL_HD;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.palette.graphics.Palette;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ctun.pokeapi.R;
import com.ctun.pokeapi.data.model.PokemonAbility;
import com.ctun.pokeapi.databinding.ActivityPokemonDetailBinding;
import com.ctun.pokeapi.ui.detailpokemon.adapter.PokemonTabsDetailAdapter;
import com.ctun.pokeapi.ui.detailpokemon.viewmodel.PokemonDetailViewModel;
import com.ctun.pokeapi.ui.home.viewmodel.HomeViewModel;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.Locale;

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
        setSupportActionBar(binding.toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("");
        }

        binding.toolbar.getNavigationIcon().setTint(ContextCompat.getColor(this, R.color.white));


        supportPostponeEnterTransition();

        OnBackPressedCallback onBackPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                finish();
            }
        };

        getOnBackPressedDispatcher().addCallback(onBackPressedCallback);

        Bundle bundle = getIntent().getExtras();
        idPokemon = bundle.getInt(EXTRA_ID);

        initViewPager();

        Glide.with(this)
                .load(POKEMON_IMAGES_URL_HD + idPokemon + ".png")
                .listener(new RequestListener<>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                        supportStartPostponedEnterTransition();
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(@NonNull Drawable resource,
                                                   @NonNull Object model,
                                                   Target<Drawable> target,
                                                   @NonNull DataSource dataSource,
                                                   boolean isFirstResource) {
                        supportStartPostponedEnterTransition();

                        BitmapDrawable drawable = (BitmapDrawable) resource;
                        Bitmap bitmap = drawable.getBitmap();
                        Palette.from(bitmap).generate(palette -> {
                            int color = palette.getDominantColor(ContextCompat.getColor(getApplicationContext(), R.color.md_white_1000));
                            binding.parentLyt.setBackgroundColor(color);


                            Window window = getWindow();
                            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                            window.setStatusBarColor(color);

                        });

                        return false;
                    }
                })
                .into(binding.imgPokemon);

        viewModel.getPokemonDetail(idPokemon);
        viewModel.getPokemonSpecies(idPokemon);

        viewModel.getDetail().observe(this, pokemonDetail -> {
            binding.tvPokemonName.setText(pokemonDetail.getName().toUpperCase(Locale.getDefault()));
            //viewModel.getPokemonDamageRelation(pokemonDetail.getTypes().get(0).getType().);

            String path = pokemonDetail.getTypes().get(0).getType().getUrl();
            // Split path into segments
            String segments[] = path.split("/");

            // Grab the last segment
            int document = Integer.parseInt(segments[segments.length - 1]);
            Log.d("typeUrl", "" + document);

            viewModel.getPokemonDamageRelation(document);
        });

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
                    } else if (position == 1) {
                        tab.setText(R.string.about);
                    } else if (position == 2) {
                        tab.setText(R.string.evolution);
                    }

                }
        ).attach();


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            getOnBackPressedDispatcher().onBackPressed();
        }

        return super.onOptionsItemSelected(item);
    }
}