package com.mesawer.chaty.seqayamvpclean.domain.repository;

import java.util.List;

import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;

public interface IProductsRepository {
    void getProducts(com.mesawer.chaty.seqayamvpclean.domain.repository.SuccessCallback<List<Product>> successCallback, com.mesawer.chaty.seqayamvpclean.domain.repository.ErrorCallback errorCallback);

    void getSearchResult(String searchKeyword,
                         SuccessCallback<List<Product>> successCallback,
                         ErrorCallback errorCallback);
}
