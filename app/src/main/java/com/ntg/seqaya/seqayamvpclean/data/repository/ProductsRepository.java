package com.ntg.seqaya.seqayamvpclean.data.repository;

import com.ntg.seqaya.seqayamvpclean.data.datasource.ProductsDataSource;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.IProductsRepository;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

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
