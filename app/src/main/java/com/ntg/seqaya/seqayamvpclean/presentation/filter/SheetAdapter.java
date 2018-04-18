package com.ntg.seqaya.seqayamvpclean.presentation.filter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.ntg.seqaya.seqayamvpclean.R;

import java.util.List;

public class SheetAdapter extends RecyclerView.Adapter<SheetAdapter.SheetHolder> {

    List<String> mList;
    Context context;

    public SheetAdapter(Context context, List<String> mList) {
        this.context = context;
        this.mList = mList;
    }

    @Override
    public SheetHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_item_row, parent, false);
        SheetHolder holder = new SheetHolder(row);
        return holder;
    }

    @Override
    public void onBindViewHolder(SheetHolder holder, int position) {
        String item = mList.get(position);
        holder.item.setText(item);
        holder.item.setOnClickListener((View) -> {
            boolean on = ((ToggleButton) View).isChecked();
            if (on) {
                ((ToggleButton) View).setTextOn(item);
                ((ToggleButton) View).setChecked(true);
                ((ToggleButton) View).setBackground(context.getResources().getDrawable(R.drawable.btn_row_background));
                ((ToggleButton) View).setTextColor(context.getResources().getColor(R.color.white));
            } else {
                ((ToggleButton) View).setTextOff(item);
                ((ToggleButton) View).setChecked(false);
                ((ToggleButton) View).setBackground(context.getResources().getDrawable(R.drawable.custom_row_btn_background));
                ((ToggleButton) View).setTextColor(context.getResources().getColor(R.color.black));
            }
        });

    }

    @Override
    public int getItemCount() {

        return mList.size();
    }

    class SheetHolder extends RecyclerView.ViewHolder {
        ToggleButton item;

        public SheetHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.custom_btn);

        }
    }

}



