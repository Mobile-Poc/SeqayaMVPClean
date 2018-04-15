package com.mesawer.chaty.seqayamvpclean.data.repository;

import com.mesawer.chaty.seqayamvpclean.data.datasource.ProductsDataSource;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;
import com.mesawer.chaty.seqayamvpclean.domain.repository.ErrorCallback;
import com.mesawer.chaty.seqayamvpclean.domain.repository.IProductsRepository;
import com.mesawer.chaty.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;

public class ProductsRepository implements IProductsRepository {

    private static ProductsRepository INSTANCE;
    private ProductsDataSource productsDataSource;

    private ProductsRepository(ProductsDataSource productsDataSource) {
        this.productsDataSource = productsDataSource;
    }

    public static ProductsRepository getInstance(ProductsDataSource productsDataSource) {
        if (INSTANCE == null) INSTANCE = new ProductsRepository(productsDataSource);
        return INSTANCE;
    }

    @Override
    public void getProducts(SuccessCallback<List<Product>> successCallback,
                            ErrorCallback errorCallback) {
       productsDataSource.getProducts(successCallback , errorCallback);
    }

    @Override
    public void getSearchResult(String searchKeyword,
                                SuccessCallback<List<Product>> successCallback,
                                ErrorCallback errorCallback) {
        productsDataSource.getSearchResult(searchKeyword , successCallback , errorCallback);

    }
}
