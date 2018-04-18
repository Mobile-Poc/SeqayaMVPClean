package com.ntg.seqaya.seqayamvpclean.presentation.savedlocatioon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;

import com.ntg.seqaya.seqayamvpclean.R;
import com.ntg.seqaya.seqayamvpclean.base.BaseFragment;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Location;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Order;
import com.ntg.seqaya.seqayamvpclean.presentation.main.MainActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;


public class SavedLocationsFragment extends BaseFragment implements SavedLocationsContract.View{

    @BindView(R.id.adress_list)
    RecyclerView savedLocationsRv;
    @BindView(R.id.cancel_button)
    Button cancelButton;
    @BindView(R.id.fram)
    FrameLayout savedLocationsLayout;
    Unbinder unbinder;
    SavedLocationsAdapter savedLocationsAdapter;
    LinearLayoutManager layoutManager;
    PublishSubject<Location> addressObservable = PublishSubject.create();
    private Order order;
    private Disposable disposable;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_adresses, container, false);
        unbinder = ButterKnife.bind(this, view);
        super.layout = savedLocationsLayout;
        order = (Order) getArguments().getSerializable(MainActivity.ORDER);

        observeOnLocationClick();
        savedLocationsLayout.setOnClickListener(v -> getActivity().getFragmentManager().popBackStack());
        cancelButton.setOnClickListener(v -> getActivity().getFragmentManager().popBackStack());

        return view;
    }

    private void observeOnLocationClick() {
        disposable = addressObservable.subscribe(location -> {
            order.setLocation(location);
            Intent returnIntent = new Intent();
            returnIntent.putExtra(MainActivity.ORDER, order);
            getActivity().setResult(Activity.RESULT_OK, returnIntent);
            getActivity().finish();
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        disposable.dispose();
        unbinder.unbind();
    }

    @Override
    public void showSavedLocations(List<Location> locations) {
        setupRecyclerView(locations);
    }

    private void setupRecyclerView(List<Location> locations) {
        layoutManager = new LinearLayoutManager(getActivity());
        savedLocationsRv.setLayoutManager(layoutManager);
        savedLocationsRv.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        savedLocationsAdapter = new SavedLocationsAdapter(locations, addressObservable);
        savedLocationsRv.setAdapter(savedLocationsAdapter);
    }

    @Override
    public void showNoLocations() {

    }

    @Override
    public void setPresenter(SavedLocationsContract.Presenter presenter) {

    }
}
