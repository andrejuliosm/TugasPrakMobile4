package com.example.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.ListViewHolder> {
    private Context context;
    private ArrayList<heromodel> heromodels;
    private OnItemClickCallback onItemClickCallback;


    public  HeroAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<heromodel> getHeromodels(){
        return heromodels;
    }

    public void setHeromodels(ArrayList<heromodel>heromodels){
        this.heromodels = heromodels;
    }
    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemRow = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.item_hero,viewGroup,false);
        return new ListViewHolder(itemRow);
    }

    @Override
    public void onBindViewHolder(@NonNull final HeroAdapter.ListViewHolder holder, int i) {
        heromodel hero = heromodels.get(i);
        Glide.with(holder.itemView.getContext())
                .load(hero.getGambarHero())
                .apply(new RequestOptions().override(300, 300))
                .into(holder.ivGambarHero);
        holder.tvNamaHero.setText(getHeromodels().get(i).getNamaHero());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(heromodels.get(holder.getAdapterPosition()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return getHeromodels().size();
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivGambarHero;
        private TextView tvNamaHero;

        public ListViewHolder (@NonNull View itemView){
            super(itemView);
            ivGambarHero = itemView.findViewById(R.id.hero_image);
            tvNamaHero = itemView.findViewById(R.id.hero_name);
        }
    }
    public interface OnItemClickCallback {
        void onItemClicked(heromodel hero);
    }
}
