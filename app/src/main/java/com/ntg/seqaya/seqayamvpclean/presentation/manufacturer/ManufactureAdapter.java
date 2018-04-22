package com.ntg.seqaya.seqayamvpclean.presentation.manufacturer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.ntg.seqaya.seqayamvpclean.R;
import com.ntg.seqaya.seqayamvpclean.utils.ViewUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by islam on 4/18/2018.
 */

public class ManufactureAdapter extends RecyclerView.Adapter<ManufactureAdapter.ManufactureViewHolder>{

    private List<Integer> manufacturersList;
    private ManufacturerClick manufacturerClick;
    private Context context;

    public ManufactureAdapter(List<Integer> manufacturersList, ManufacturerClick manufacturerClick, Context context) {
        this.manufacturersList = manufacturersList;
        this.manufacturerClick = manufacturerClick;
        this.context = context;
    }

    @NonNull
    @Override
    public ManufactureViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.manufacture_row , parent , false);
        ViewUtil.addShadowToView(context, view);

        return new ManufactureViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ManufactureViewHolder holder, int position) {
        Glide.with(context)
                .load(manufacturersList.get(position))
                .apply(RequestOptions.circleCropTransform())
                .into(holder.manfacture_image);
        holder.manfacture_image.setOnClickListener(v -> manufacturerClick.clickListener(true));
    }

    @Override
    public int getItemCount() {
        return manufacturersList.size();
    }

    class ManufactureViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.manufacturer_image)
        ImageView manfacture_image;
        public ManufactureViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
