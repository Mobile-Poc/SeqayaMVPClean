package com.ntg.seqaya.seqayamvpclean.presentation.products;

import com.ntg.seqaya.seqayamvpclean.domain.entity.Fav;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.favourites.AddFavourite;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.favourites.DeleteFavourite;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.favourites.GetFavourites;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.products.GetProducts;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.products.Search;

public class ProductsPresenter implements ProductsContract.Presenter {

    private GetProducts getProducts;
    private Search search;
    private AddFavourite addFavourite;
    private GetFavourites getFavourites;
    private DeleteFavourite deleteFavourite;

    private ProductsContract.View view;

    public ProductsPresenter(GetProducts getProducts, Search search, AddFavourite addFavourite, GetFavourites getFavourites, DeleteFavourite deleteFavourite, ProductsContract.View view) {
        this.getProducts = getProducts;
        this.search = search;
        this.addFavourite = addFavourite;
        this.getFavourites = getFavourites;
        this.deleteFavourite = deleteFavourite;
        this.view = view;
    }

    @Override
    public void deleteFavourite(String productId) {
        DeleteFavourite.RequestValues requestValues = new DeleteFavourite.RequestValues(productId);
        deleteFavourite.execute(requestValues , response -> {} , errMsg -> {});
    }

    @Override
    public void getFavourites() {
        getFavourites.execute(null , response -> {
            GetFavourites.ResponseValues responseValues = new GetFavourites
                    .ResponseValues(response.getFavs());
            view.sentFavouriteList(responseValues.getFavs());
        },errMsg -> {});
    }

    @Override
    public void getProduct() {
        getProducts.execute(null, response -> {
            GetProducts.ResponseValues responseValues = new GetProducts
                    .ResponseValues(response.getProducts());
            view.showProducts(responseValues.getProducts());

        }, errMsg -> {

        });
    }

    @Override
    public void searchProduct(String keyWord) {
        Search.RequestValues requestValues = new Search.RequestValues(keyWord);
        search.execute(requestValues , response -> {
            Search.ResponseValues responseValues = new Search.ResponseValues(response.getProducts());
            if (responseValues.getProducts() != null){
                view.showSearchResult(responseValues.getProducts());
            }
        },errMsg -> {});
    }

    @Override
    public void addToFavourite(Fav fav) {
        AddFavourite.RequestValues requestValues = new AddFavourite.RequestValues(fav);
        addFavourite.execute(requestValues , response -> {},errMsg -> {});
    }
}
