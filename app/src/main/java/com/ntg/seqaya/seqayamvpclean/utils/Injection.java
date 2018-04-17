package com.ntg.seqaya.seqayamvpclean.utils;

import com.ntg.seqaya.seqayamvpclean.base.UseCaseHandler;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.FavouritesRemoteDataSource;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.LocationsRemoteDataSource;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.OrdersRemoteDataSource;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.ProductsRemoteDataSource;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.UsersRemoteDataSource;
import com.ntg.seqaya.seqayamvpclean.data.repository.FavouritesRepository;
import com.ntg.seqaya.seqayamvpclean.data.repository.LocationsRepository;
import com.ntg.seqaya.seqayamvpclean.data.repository.OrdersRepository;
import com.ntg.seqaya.seqayamvpclean.data.repository.ProductsRepository;
import com.ntg.seqaya.seqayamvpclean.data.repository.UsersRepository;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.favourites.AddFavourite;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.favourites.DeleteFavourite;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.favourites.GetFavourites;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.login.EmailPasswordLogin;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.orderhistory.GetOrderHistory;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.products.GetProducts;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.products.Search;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.registration.AddNewUser;
import com.ntg.seqaya.seqayamvpclean.domain.usecase.savedlocation.GetSavedLocations;

public class Injection {

    public static ProductsRepository provideProductsRepository() {
        return ProductsRepository.getInstance(ProductsRemoteDataSource.getInstance());
    }

    public static UsersRepository provideUsersRepository() {
        return UsersRepository.getInstance(UsersRemoteDataSource.getInstance());
    }

    public static LocationsRepository provideLocationsRepository() {
        return LocationsRepository.getInstance(LocationsRemoteDataSource.getInstance());
    }

    public static OrdersRepository provideOrdersRepository() {
        return OrdersRepository.getInstance(OrdersRemoteDataSource.getInstance());
    }

    public static FavouritesRepository provideFavouritesRepository() {
        return FavouritesRepository.getInstance(FavouritesRemoteDataSource.getInstance());
    }

    public static GetProducts getProducts(){
        return new GetProducts(provideProductsRepository());
    }

    public static Search search(){
        return new Search(provideProductsRepository());
    }

    public static AddFavourite  addFavourite(){
        return new AddFavourite(provideFavouritesRepository());
    }

    public static DeleteFavourite deleteFavourite(){
        return new DeleteFavourite(provideFavouritesRepository());
    }

    public static GetSavedLocations provideGetSavedLocations(){
        return new GetSavedLocations(provideLocationsRepository());
    }

    public static EmailPasswordLogin provideEmailPasswordLogin() {
        return new EmailPasswordLogin(provideUsersRepository());
    }

    public static AddNewUser provideAddNewUser() {
        return new AddNewUser(provideUsersRepository());
    }

    public static GetOrderHistory provideGetOrderHistory() {
        return new GetOrderHistory(provideOrdersRepository());
    }

    public static GetFavourites provideGetFavourites() {
        return new GetFavourites(provideFavouritesRepository());
    }

    public static DeleteFavourite provideDeleteFavourite() {
        return new DeleteFavourite(provideFavouritesRepository());
    }

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }
}
