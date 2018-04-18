package com.ntg.seqaya.seqayamvpclean.data.datasource;

import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

import java.util.List;

public interface ProductsDataSource {

    void getProducts(SuccessCallback<List<Product>> successCallback, ErrorCallback errorCallback);

    void getSearchResult(String searchKeyword,
                         SuccessCallback<List<Product>> successCallback,
                         ErrorCallback errorCallback);
}
