package com.ctun.pokeapi.ui.detail.adapter;


import static com.ctun.pokeapi.utils.Constants.POKEMON_IMAGES_URL_HD;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.ctun.pokeapi.R;
import com.ctun.pokeapi.data.model.Evolution;
import com.ctun.pokeapi.databinding.EvolutionListItemBinding;

import java.util.List;
import java.util.Locale;

public class PokemonEvolutionListAdapter extends RecyclerView.Adapter<PokemonEvolutionListAdapter.ViewHolder> {

    private List<Evolution> mValues;
    private final Context mContext;
    private int lastPosition = -1;

    public PokemonEvolutionListAdapter(Context context, List<Evolution> items) {
        mValues = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(EvolutionListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Evolution item = mValues.get(holder.getAdapterPosition());

        holder.mEvolutionName.setText(item.getName().toUpperCase(Locale.getDefault()));
        holder.mEvolutionLvl.setText(item.getLevel()!=0 ? "LVL. " + item.getLevel() : "");

        Glide.with(mContext)
                .load(POKEMON_IMAGES_URL_HD + item.getIdPokemon() + ".png")
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

                        /*BitmapDrawable drawable  = (BitmapDrawable) resource;
                        Bitmap bitmap  = drawable.getBitmap();
                        Palette.from(bitmap).generate(palette -> {
                            int color = palette.getDominantColor(ContextCompat.getColor(mContext, R.color.md_white_1000));
                            holder.mBackground.setCardBackgroundColor(color);
                        });*/


                        return false;
                    }
                })
                .into(holder.mEvolutionImage);

        /*TypeColors colorType = TypeColors.getColorType(item.getName());

        int color = Color.parseColor(colorType.getHexColor());
        int radius = 20;

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(radius);
        holder.mParent.setBackground(gradientDrawable);

        holder.mPokemonType.setText(item.getName().toUpperCase(Locale.getDefault()));*/
        setAnimation(holder.mParent, holder.getAdapterPosition());
    }

    @Override
    public int getItemCount() {
        if(mValues!=null){
            return mValues.size();
        }else{
            return 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView mEvolutionName;
        public ImageView mEvolutionImage;
        public ConstraintLayout mParent;
        public TextView mEvolutionLvl;
        public ViewHolder(EvolutionListItemBinding binding) {
            super(binding.getRoot());
            mEvolutionImage = binding.ivEvolution;
            mEvolutionName = binding.tvEvolutionName;
            mParent = binding.lytParent;
            mEvolutionLvl = binding.tvLvl;
        }

    }

    private void setAnimation(View viewToAnimate, int position)
    {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.translate_from_bottom);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    public List<Evolution> getEvolutionList(){
        return mValues;
    }


}
