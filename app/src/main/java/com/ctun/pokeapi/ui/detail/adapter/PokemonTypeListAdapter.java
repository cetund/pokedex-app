package com.ctun.pokeapi.ui.detail.adapter;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.ctun.pokeapi.data.model.PokemonType;
import com.ctun.pokeapi.databinding.TypeListItemBinding;
import com.ctun.pokeapi.utils.TypeColors;

import java.util.List;
import java.util.Locale;

public class PokemonTypeListAdapter extends RecyclerView.Adapter<PokemonTypeListAdapter.ViewHolder> {

    private List<PokemonType> mValues;
    private final Context mContext;

    public PokemonTypeListAdapter(Context context, List<PokemonType> items) {
        mValues = items;
        mContext = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(TypeListItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));

    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        PokemonType item = mValues.get(holder.getAdapterPosition());

        TypeColors colorType = TypeColors.getColorType(item.getName());

        int color = Color.parseColor(colorType.getHexColor());
        int radius = 20;

        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setColor(color);
        gradientDrawable.setCornerRadius(radius);
        holder.mParent.setBackground(gradientDrawable);

        holder.mPokemonType.setText(item.getName().toUpperCase(Locale.getDefault()));
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
        public TextView mPokemonType;
        public LinearLayout mParent;
        public ViewHolder(TypeListItemBinding binding) {
            super(binding.getRoot());
            mPokemonType = binding.tvType;
            mParent = binding.lytParent;
        }

    }

    public List<PokemonType> getMoviesList(){
        return mValues;
    }


}
