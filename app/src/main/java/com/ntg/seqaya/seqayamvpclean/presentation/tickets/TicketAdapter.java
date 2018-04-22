package com.ntg.seqaya.seqayamvpclean.presentation.tickets;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder>{
    @NonNull
    @Override
    public TicketAdapter.TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull TicketAdapter.TicketViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class TicketViewHolder extends RecyclerView.ViewHolder{
        public TicketViewHolder(View itemView) {
            super(itemView);
        }
    }
}
