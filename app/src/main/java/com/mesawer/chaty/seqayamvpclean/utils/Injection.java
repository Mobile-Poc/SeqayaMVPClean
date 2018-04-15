package com.mesawer.chaty.seqayamvpclean.utils;

import com.mesawer.chaty.seqayamvpclean.base.UseCaseHandler;
import com.mesawer.chaty.seqayamvpclean.data.datasource.remote.FavouritesRemoteDataSource;
import com.mesawer.chaty.seqayamvpclean.data.datasource.remote.LocationsRemoteDataSource;
import com.mesawer.chaty.seqayamvpclean.data.datasource.remote.OrdersRemoteDataSource;
import com.mesawer.chaty.seqayamvpclean.data.datasource.remote.ProductsRemoteDataSource;
import com.mesawer.chaty.seqayamvpclean.data.datasource.remote.UsersRemoteDataSource;
import com.mesawer.chaty.seqayamvpclean.data.repository.FavouritesRepository;
import com.mesawer.chaty.seqayamvpclean.data.repository.LocationsRepository;
import com.mesawer.chaty.seqayamvpclean.data.repository.OrdersRepository;
import com.mesawer.chaty.seqayamvpclean.data.repository.ProductsRepository;
import com.mesawer.chaty.seqayamvpclean.data.repository.UsersRepository;
import com.mesawer.chaty.seqayamvpclean.domain.usecase.favourites.AddFavourite;
import com.mesawer.chaty.seqayamvpclean.domain.usecase.favourites.DeleteFavourite;
import com.mesawer.chaty.seqayamvpclean.domain.usecase.favourites.GetFavourites;
import com.mesawer.chaty.seqayamvpclean.domain.usecase.login.EmailPasswordLogin;
import com.mesawer.chaty.seqayamvpclean.domain.usecase.orderhistory.GetOrderHistory;
import com.mesawer.chaty.seqayamvpclean.domain.usecase.products.GetProducts;
import com.mesawer.chaty.seqayamvpclean.domain.usecase.products.Search;
import com.mesawer.chaty.seqayamvpclean.domain.usecase.registration.AddNewUser;
import com.mesawer.chaty.seqayamvpclean.domain.usecase.savedlocation.GetSavedLocations;

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
