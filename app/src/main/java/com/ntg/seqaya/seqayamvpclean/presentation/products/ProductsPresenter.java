package com.ntg.seqaya.seqayamvpclean.presentation.products;

import com.ntg.seqaya.seqayamvpclean.base.UseCaseHandler;
import com.ntg.seqaya.seqayamvpclean.base.UseCase;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Fav;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.favourites.AddFavourite;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.favourites.DeleteFavourite;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.favourites.GetFavourites;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.products.GetProducts;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.products.Search;

public class ProductsPresenter implements ProductsContract.Presenter {

    private UseCaseHandler useCaseHandler;
    private GetProducts getProducts;
    private Search search;
    private AddFavourite addFavourite;
    private GetFavourites getFavourites;
    private DeleteFavourite deleteFavourite;
    private ProductsContract.View view;

    public ProductsPresenter(UseCaseHandler useCaseHandler,
                             GetProducts getProducts,
                             Search search,
                             AddFavourite addFavourite,
                             GetFavourites getFavourites,
                             DeleteFavourite deleteFavourite,
                             ProductsContract.View view) {
        this.useCaseHandler = useCaseHandler;
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
        useCaseHandler.execute(deleteFavourite, requestValues,
                new UseCase.UseCaseCallback<DeleteFavourite.ResponseValues>() {
            @Override
            public void onSuccess(DeleteFavourite.ResponseValues response) {

            }

            @Override
            public void onError(String errMsg) {
                view.showErrorMessage(errMsg);
            }
        });
    }

    @Override
    public void getFavourites() {
        useCaseHandler.execute(getFavourites, null, new
                UseCase.UseCaseCallback<GetFavourites.ResponseValues>() {
                    @Override
                    public void onSuccess(GetFavourites.ResponseValues response) {
                        GetFavourites.ResponseValues responseValues = new GetFavourites
                                .ResponseValues(response.getFavs());
                        view.sentFavouriteList(responseValues.getFavs());
                    }

                    @Override
                    public void onError(String errMsg) {
                        view.showErrorMessage(errMsg);
                    }
                });
    }

    @Override
    public void getProduct() {
        useCaseHandler.execute(getProducts, null,
                new UseCase.UseCaseCallback<GetProducts.ResponseValues>() {
                    @Override
                    public void onSuccess(GetProducts.ResponseValues response) {
                        GetProducts.ResponseValues responseValues = new GetProducts
                                .ResponseValues(response.getProducts());
                        view.showProducts(responseValues.getProducts());
                    }

                    @Override
                    public void onError(String errMsg) {
                        view.showErrorMessage(errMsg);
                    }
                });
    }

    @Override
    public void searchProduct(String keyWord) {
        Search.RequestValues requestValues = new Search.RequestValues(keyWord);
        useCaseHandler.execute(search, requestValues, new
                UseCase.UseCaseCallback<Search.ResponseValues>() {
                    @Override
                    public void onSuccess(Search.ResponseValues response) {
                        Search.ResponseValues responseValues =
                                new Search.ResponseValues(response.getProducts());
                        if (responseValues.getProducts() != null) {
                            view.showSearchResult(responseValues.getProducts());
                        }
                    }

                    @Override
                    public void onError(String errMsg) {
                        view.showErrorMessage(errMsg);
                    }
                });
    }

    @Override
    public void addToFavourite(Fav fav) {
        AddFavourite.RequestValues requestValues = new AddFavourite.RequestValues(fav);
        useCaseHandler.execute(addFavourite, requestValues,
                new UseCase.UseCaseCallback<AddFavourite.ResponseValues>() {
                    @Override
                    public void onSuccess(AddFavourite.ResponseValues response) {

                    }

                    @Override
                    public void onError(String errMsg) {
                        view.showErrorMessage(errMsg);
                    }
                });
    }
}
