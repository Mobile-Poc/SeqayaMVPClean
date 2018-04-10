package com.mesawer.chaty.seqayamvpclean.data.datasource.remote;

import com.mesawer.chaty.seqayamvpclean.data.datasource.ProductsDataSource;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;
import com.mesawer.chaty.seqayamvpclean.domain.repository.ErrorCallback;
import com.mesawer.chaty.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;

public class ProductsRemoteDataSource implements ProductsDataSource {

    private static ProductsRemoteDataSource INSTANCE;

    private ProductsRemoteDataSource() {
    }

    public static ProductsRemoteDataSource getInstance() {
        if (INSTANCE == null) INSTANCE = new ProductsRemoteDataSource();
        return INSTANCE;
    }

    @Override
    public void getProducts(
            SuccessCallback<List<Product>> successCallback,
            ErrorCallback errorCallback) {
    }

    @Override
    public void getSearchResult(String searchKeyword,
                                SuccessCallback<List<Product>> successCallback,
                                ErrorCallback errorCallback) {

    }
}
