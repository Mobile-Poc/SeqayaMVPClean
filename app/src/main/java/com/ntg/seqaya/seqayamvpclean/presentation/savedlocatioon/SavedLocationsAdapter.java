package com.ntg.seqaya.seqayamvpclean.presentation.savedlocatioon;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ntg.seqaya.seqayamvpclean.R;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Location;

import java.util.List;

import io.reactivex.subjects.PublishSubject;

/**
 * Created by devsaad on 3/26/2018.
 */

public class SavedLocationsAdapter extends RecyclerView.Adapter<SavedLocationsAdapter.LocationsViewHolder> {

    PublishSubject<Location> addressObservable;
    List<Location> locations;

    public SavedLocationsAdapter(List<Location> locations,
                                 PublishSubject<Location> addressObservable) {
        this.locations = locations;
        this.addressObservable = addressObservable;
    }

    @Override
    public LocationsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adress_row, parent, false);
        return new LocationsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final LocationsViewHolder holder, int position) {
        holder.addressTextView.setText(locations.get(position).getAddress());
        holder.itemView.setOnClickListener(v -> {
            for (Location location : locations) {
                if (location.getAddress().equals(holder.addressTextView.getText().toString())) {
                    addressObservable.onNext(location);
                    break;
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return locations.size();
    }

    class LocationsViewHolder extends RecyclerView.ViewHolder {
        TextView addressTextView;

        public LocationsViewHolder(View itemView) {
            super(itemView);
            addressTextView = itemView.findViewById(R.id.adress_txt);

        }
    }
}
