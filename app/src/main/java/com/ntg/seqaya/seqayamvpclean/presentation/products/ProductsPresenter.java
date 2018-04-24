package com.ntg.seqaya.seqayamvpclean.presentation.products;

import android.util.Log;

import com.bumptech.glide.request.RequestOptions;
import com.ntg.seqaya.seqayamvpclean.base.UseCaseHandler;
import com.ntg.seqaya.seqayamvpclean.base.UseCase;
import com.ntg.seqaya.seqayamvpclean.domain.entity.CartItem;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.cart.AddToCart;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.favourites.AddFavourite;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.favourites.DeleteFavourite;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.favourites.GetFavourites;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.products.GetProducts;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.products.Search;

import java.util.List;

public class ProductsPresenter implements ProductsContract.Presenter {

    private UseCaseHandler useCaseHandler;
    private GetProducts getProducts;
    private Search search;
    private AddFavourite addFavourite;
    private GetFavourites getFavourites;
    private DeleteFavourite deleteFavourite;
    private AddToCart addToCart;
    private ProductsContract.View view;

    public ProductsPresenter(UseCaseHandler useCaseHandler,
                             GetProducts getProducts,
                             Search search,
                             AddFavourite addFavourite,
                             GetFavourites getFavourites,
                             DeleteFavourite deleteFavourite,
                             AddToCart addToCart, ProductsContract.View view) {
        this.useCaseHandler = useCaseHandler;
        this.getProducts = getProducts;
        this.search = search;
        this.addFavourite = addFavourite;
        this.getFavourites = getFavourites;
        this.deleteFavourite = deleteFavourite;
        this.addToCart = addToCart;
        this.view = view;
    }

    @Override
    public void addToCart(List<CartItem> cartItems , CartItem cartItem) {
        AddToCart.RequestValues requestValues = new AddToCart.RequestValues(cartItems , cartItem);
        useCaseHandler.execute(addToCart, requestValues, new UseCase.UseCaseCallback<AddToCart.ResponseValues>() {
            @Override
            public void onSuccess(AddToCart.ResponseValues response) {
                AddToCart.ResponseValues responseValues = new AddToCart.ResponseValues(response.getCartItems());
                Log.e("cart" , responseValues.getCartItems().size()+"");
            }

            @Override
            public void onError(String errMsg) {

            }
        });
    }

    @Override
    public void deleteFavourite(List<Product> favouriteList , Product fav) {
        DeleteFavourite.RequestValues requestValues = new DeleteFavourite.RequestValues(fav , favouriteList);
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
    public void addToFavourite(List<Product> favouriteList, Product fav) {
        AddFavourite.RequestValues requestValues = new AddFavourite.RequestValues(favouriteList , fav);
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
