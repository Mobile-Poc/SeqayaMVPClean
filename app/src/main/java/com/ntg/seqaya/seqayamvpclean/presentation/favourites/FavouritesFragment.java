package com.ntg.seqaya.seqayamvpclean.presentation.favourites;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.ntg.seqaya.seqayamvpclean.R;
import com.ntg.seqaya.seqayamvpclean.base.BaseFragment;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;
import com.ntg.seqaya.seqayamvpclean.utils.Injection;
import com.ntg.seqaya.seqayamvpclean.utils.ViewUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;


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
    private PublishSubject<Product> unLikedProduct = PublishSubject.create();
    private Disposable disposable;


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
        favouritesPresenter = new FavouritesPresenter(Injection.provideUseCaseHandler(),
                this,
                Injection.provideGetFavourites(),
                Injection.provideDeleteFavourite());
        ViewUtil.setupActionBar(getActivity(), getString(R.string.favourite));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        favouritesPresenter.getFavourites();
        disposable = unLikedProduct.subscribe(
                product -> favouritesPresenter.removeFavourite(String.valueOf(product.getId())));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        unBinder.unbind();
        disposable.dispose();
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
        favouritesAdapter = new FavouritesAdapter(favourites, getActivity(), unLikedProduct);
        favs_rv.setAdapter(favouritesAdapter);
    }

    @Override
    public void showNoFavourites() {
        if (loadingIndicator != null)
            loadingIndicator.setVisibility(View.GONE);
        noItemsLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void removeFavourite(int productId) {
        favouritesAdapter.removeFavourite(productId);
    }

    @Override
    public void setPresenter(FavouritesContract.Presenter presenter) {
        favouritesPresenter = presenter;
    }
}
