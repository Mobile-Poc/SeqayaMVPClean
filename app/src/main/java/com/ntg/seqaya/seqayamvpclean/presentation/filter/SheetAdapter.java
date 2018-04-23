package com.ntg.seqaya.seqayamvpclean.presentation.filter;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.ntg.seqaya.seqayamvpclean.R;

import java.util.List;

public class SheetAdapter extends RecyclerView.Adapter<SheetAdapter.SheetHolder> {

    private String type;
    private List<String> mList;
    private Context context;


    SheetAdapter(Context context, List<String> mList, String type) {
        this.context = context;
        this.mList = mList;
        this.type = type;
    }

    @NonNull
    @Override
    public SheetHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.filter_item_row, parent, false);
        return new SheetHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull SheetHolder holder, int position) {
        String itemText = mList.get(position);
        holder.item.setText(itemText);
        holder.item.setOnClickListener((View) -> {
            boolean on = ((ToggleButton) View).isChecked();
            if (on) {
                switch (type) {
                    case "size":
                        FilterLists.sizeList.put(position, itemText);
                        break;
                    case "num":
                        FilterLists.numList.put(position, itemText);
                        break;
                    default:
                        break;
                }
                activeBtn(itemText, View);
            } else {
                switch (type) {
                    case "size":
                        FilterLists.sizeList.remove(position);
                        break;
                    case "num":
                        FilterLists.numList.remove(position);
                        break;
                    default:
                        break;

                }
                resetBtn(itemText, View);
            }
        });

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    private void activeBtn(String textOn, View view) {
        ((ToggleButton) view).setTextOn(textOn);
        ((ToggleButton) view).setChecked(true);
        ((ToggleButton) view).setBackground(context.getResources().getDrawable(R.drawable.btn_row_background));
        ((ToggleButton) view).setTextColor(context.getResources().getColor(R.color.white));
    }

    private void resetBtn(String textOff, View view) {
        ((ToggleButton) view).setTextOff(textOff);
        ((ToggleButton) view).setChecked(false);
        ((ToggleButton) view).setBackground(context.getResources().getDrawable(R.drawable.custom_row_btn_background));
        ((ToggleButton) view).setTextColor(context.getResources().getColor(R.color.black));
    }

    class SheetHolder extends RecyclerView.ViewHolder {
        ToggleButton item;

        SheetHolder(View itemView) {
            super(itemView);
            item = itemView.findViewById(R.id.custom_btn);
        }
    }

}



