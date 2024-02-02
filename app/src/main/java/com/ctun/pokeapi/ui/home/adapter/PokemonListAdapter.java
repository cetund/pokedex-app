package com.ctun.pokeapi.ui.home.adapter;

import static android.nfc.NfcAdapter.EXTRA_ID;
import static com.ctun.pokeapi.utils.Constants.POKEMON_IMAGES_URL_HD;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.palette.graphics.Palette;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ctun.pokeapi.R;
import com.ctun.pokeapi.data.model.PokemonList;
import com.ctun.pokeapi.data.model.Results;
import com.ctun.pokeapi.databinding.PokemonListItemBinding;
import com.ctun.pokeapi.ui.detailpokemon.view.PokemonDetailActivity;
import com.ctun.pokeapi.ui.home.view.Home;

import java.util.Locale;

public class PokemonListAdapter extends RecyclerView.Adapter<PokemonListAdapter.ViewHolder> {

    private PokemonList mValues;
    private final Context mContext;
    private OnClickPokemonLister mOnClickPokemonLister;

    public PokemonListAdapter(Context context, PokemonList items, OnClickPokemonLister onClickPokemonLister) {
        mOnClickPokemonLister = onClickPokemonLister;
        mValues = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(PokemonListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Results item = mValues.getResultsList().get(holder.getAdapterPosition());
        holder.mPokemonName.setText(item.getName().toUpperCase(Locale.getDefault()));

        int positionAux = item.getId() == 0 ? (holder.getAdapterPosition() + 1) : item.getId();

        holder.mParent.setOnClickListener(view -> {
            Log.d("idPokemon", "" + positionAux);
            mOnClickPokemonLister.goDetail(view, positionAux, holder.mImageViewCover);
        });

        holder.mPokemonId.setText("#" + positionAux);
        Glide.with(mContext)
                .load(POKEMON_IMAGES_URL_HD + (positionAux) + ".png")
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, @Nullable Object model, @NonNull Target<Drawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(@NonNull Drawable resource,
                                                   @NonNull Object model,
                                                   Target<Drawable> target,
                                                   @NonNull DataSource dataSource,
                                                   boolean isFirstResource) {

                        BitmapDrawable drawable  = (BitmapDrawable) resource;
                        Bitmap bitmap  = drawable.getBitmap();
                        Palette.from(bitmap).generate(palette -> {
                            int color = palette.getDominantColor(ContextCompat.getColor(mContext, R.color.md_white_1000));
                            holder.mBackground.setCardBackgroundColor(color);
                        });

                        return false;
                    }
                })
                .into(holder.mImageViewCover);




    }
    public void addPokemon(Results pokemon){
        this.mValues.getResultsList().add(pokemon);
        notifyItemInserted(this.mValues.getResultsList().size() - 1);
    }
    public void setData(PokemonList data){
       for(Results pokemon : data.getResultsList()){
           addPokemon(pokemon);
       }
    }
    @Override
    public int getItemCount() {
        if(mValues.getResultsList()!=null){
            return mValues.getResultsList().size();
        }else{
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageViewCover;
        public TextView mPokemonName;
        public TextView mPokemonId;
        public ConstraintLayout mParent;
        public CardView mBackground;
        public ViewHolder(PokemonListItemBinding binding) {
            super(binding.getRoot());
            mImageViewCover = binding.pokemonImage;
            mPokemonName = binding.tvPokemonName;
            mParent = binding.parentLyt;
            mPokemonId = binding.tvId;
            mBackground = binding.cardContainer;
        }

    }

    public PokemonList getMoviesList(){
        return mValues;
    }

    public interface OnClickPokemonLister{
        void goDetail(View view, int position, View imageView);
    }

}
