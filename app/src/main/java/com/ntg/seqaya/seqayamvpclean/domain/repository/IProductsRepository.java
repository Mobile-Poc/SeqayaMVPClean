package com.ntg.seqaya.seqayamvpclean.domain.repository;

import java.util.List;

import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;

public interface IProductsRepository {
    void getProducts(com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback<List<Product>> successCallback, com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback errorCallback);

    void getSearchResult(String searchKeyword,
                         SuccessCallback<List<Product>> successCallback,
                         ErrorCallback errorCallback);
}
