package com.mesawer.chaty.seqayamvpclean.presentation.favourites;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.mesawer.chaty.seqayamvpclean.R;
import com.mesawer.chaty.seqayamvpclean.base.BaseFragment;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;
import com.mesawer.chaty.seqayamvpclean.utils.Injection;
import com.mesawer.chaty.seqayamvpclean.utils.ViewUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class FavouritesFragment extends BaseFragment implements FavouritesContract.View {

    @BindView(R.id.rv_products)
    RecyclerView favs_rv;
    @BindView(R.id.loading_indicator)
    LinearLayout loadingIndicator;
    @BindView(R.id.no_items_layout)
    LinearLayout noItemsLayout;
    @BindView(R.id.favourites_layout)
    FrameLayout favouritesLayout;
    private LinearLayoutManager linearLayoutManager;
    private FavouritesAdapter favouritesAdapter;
    private Unbinder unBinder;
    private FavouritesContract.Presenter favouritesPresenter;


    public static FavouritesFragment newInstance() {
        FavouritesFragment fragment = new FavouritesFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourites, container, false);
        unBinder = ButterKnife.bind(this, view);
        super.layout = favouritesLayout;
        favouritesPresenter = new FavouritesPresenter(this,
                Injection.provideGetFavourites(),
                Injection.provideDeleteFavourite());
        ViewUtil.setupActionBar(getActivity(), getString(R.string.favourite));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        favouritesPresenter.getFavourites();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unBinder.unbind();
    }

    @Override
    public void showFavourites(List<Product> favourites) {
        if (loadingIndicator != null && favs_rv != null) {
            loadingIndicator.setVisibility(View.GONE);
            setupRecyclerView(favourites);
        }
    }

    private void setupRecyclerView(List<Product> favourites) {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        favs_rv.setLayoutManager(linearLayoutManager);
        favouritesAdapter = new FavouritesAdapter(favourites, getActivity());
        favs_rv.setAdapter(favouritesAdapter);
    }

    @Override
    public void showNoFavourites() {
        if (loadingIndicator != null)
            loadingIndicator.setVisibility(View.GONE);
        noItemsLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeFavourite(Product Product) {

    }

    @Override
    public void setPresenter(FavouritesContract.Presenter presenter) {
        favouritesPresenter = presenter;
    }
}
