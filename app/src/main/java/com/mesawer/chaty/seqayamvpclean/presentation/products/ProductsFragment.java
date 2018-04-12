package com.mesawer.chaty.seqayamvpclean.presentation.products;


import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mesawer.chaty.seqayamvpclean.R;
import com.mesawer.chaty.seqayamvpclean.base.BaseFragment;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Fav;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;
import com.mesawer.chaty.seqayamvpclean.domain.entity.User;
import com.mesawer.chaty.seqayamvpclean.presentation.main.CartItemsCountListener;
import com.mesawer.chaty.seqayamvpclean.utils.Injection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;


public class ProductsFragment extends BaseFragment implements ProductsContract.View {

    @BindView(R.id.products_rv)
    RecyclerView products_rv;
    private ProductAdapter productAdapter;
    private ProductsPresenter productsPresenter;
    private List<Product> products;
    private PublishSubject<Product> productPublishSubject = PublishSubject.create();
    private CartItemsCountListener cartItemsCountListener;

    public static ProductsFragment newInstance() {

        return new ProductsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle args = getArguments();
        if (args != null)
            cartItemsCountListener = args.getParcelable("count");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_products, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);

        productPublishSubject.subscribe(new Subject<Product>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(Product product) {
                if (product.isLiked()) {
                    productsPresenter.deleteFavourite(String.valueOf(product.getId()));
                } else {
                    Fav fav = new Fav(User.getEmail(), String.valueOf(product.getId()));
                    productsPresenter.addToFavourite(fav);
                }

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }

            @Override
            protected void subscribeActual(Observer<? super Product> observer) {

            }

            @Override
            public boolean hasObservers() {
                return false;
            }

            @Override
            public boolean hasThrowable() {
                return false;
            }

            @Override
            public boolean hasComplete() {
                return false;
            }

            @Override
            public Throwable getThrowable() {
                return null;
            }
        });
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        products_rv.setLayoutManager(linearLayoutManager);
        productAdapter = new ProductAdapter(products, getActivity(),
                productPublishSubject, cartItemsCountListener);
        productsPresenter = new ProductsPresenter(Injection.getProducts()
                , Injection.search()
                , Injection.addFavourite()
                , Injection.provideGetFavourites()
                , Injection.deleteFavourite(), this);
        productsPresenter.getProduct();
        productsPresenter.getFavourites();
        return view;
    }

    @Override
    public void setPresenter(ProductsContract.Presenter presenter) {

    }

    @Override
    public void showProducts(List<Product> productList) {
        this.products = productList;
        productAdapter.setProductList(productList);
        products_rv.setAdapter(productAdapter);
    }

    @Override
    public void showSearchResult(List<Product> productList) {

        if (productList != null) {
            productAdapter = new ProductAdapter(productList, getActivity(), productPublishSubject, cartItemsCountListener);
            products_rv.setAdapter(productAdapter);
            productAdapter.notifyDataSetChanged();
        }

    }

    @Override
    public void showSortResult() {
        final BottomSheetDialog mBottomSheetDialog =
                new BottomSheetDialog(getActivity());
        View dialogView = getActivity().getLayoutInflater()
                .inflate(R.layout.dialog_bottom_sheet, null);
        TextView price = dialogView.findViewById(R.id.price);
        price.setOnClickListener(view -> {
            Collections.sort(products, (product, t1) -> {
                if (product.getPrice() > t1.getPrice()) {
                    return 0;
                } else if (product.getPrice() < t1.getPrice()) {
                    return -1;
                }
                return 1;
            });
            productAdapter.notifyDataSetChanged();
            mBottomSheetDialog.dismiss();
        });
        TextView bottle_size = dialogView.findViewById(R.id.bottle_size);
        bottle_size.setOnClickListener(view -> {
            Collections.sort(products, (product, t1) -> {
                if (product.getBottleSize() > t1.getBottleSize()) {
                    return 0;
                } else if (product.getBottleSize() < t1.getBottleSize()) {
                    return -1;
                }
                return 1;
            });
            productAdapter.notifyDataSetChanged();
            mBottomSheetDialog.dismiss();
        });
        TextView bottle_per_package = dialogView.findViewById(R.id.bottle_per_package);
        bottle_per_package.setOnClickListener(view -> {
            Collections.sort(products, (product, t1) -> {
                if (product.getNo_bpp() > t1.getNo_bpp()) {
                    return 0;
                } else if (product.getNo_bpp() < t1.getNo_bpp()) {
                    return -1;
                }
                return 1;
            });
            productAdapter.notifyDataSetChanged();
            mBottomSheetDialog.dismiss();
        });
        mBottomSheetDialog.setContentView(dialogView);
        mBottomSheetDialog.show();
    }

    @Override
    public void sentFavouriteList(List<Product> favList) {
        if (favList != null)
            productAdapter.setFavouriteProducts(favList);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        MenuItem item = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                productAdapter.clear();
                if (s.equals("")) {
                    productsPresenter.getProduct();
                } else {
                    productsPresenter.searchProduct(s);
                }
                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort: {
                showSortResult();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            cartItemsCountListener = (CartItemsCountListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnHeadlineSelectedListener");
        }
    }
}