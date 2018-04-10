package com.mesawer.chaty.seqayamvpclean.data.datasource;

import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;
import com.mesawer.chaty.seqayamvpclean.domain.repository.ErrorCallback;
import com.mesawer.chaty.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;

public interface ProductsDataSource {

    void getProducts(SuccessCallback<List<Product>> successCallback, ErrorCallback errorCallback);

    void getSearchResult(String searchKeyword,
                         SuccessCallback<List<Product>> successCallback,
                         ErrorCallback errorCallback);
}
