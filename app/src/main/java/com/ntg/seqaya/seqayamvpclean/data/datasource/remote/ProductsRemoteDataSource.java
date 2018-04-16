package com.ntg.seqaya.seqayamvpclean.data.datasource.remote;

import android.support.annotation.NonNull;

import com.ntg.seqaya.seqayamvpclean.data.datasource.ProductsDataSource;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network.ApiClient;
import com.ntg.seqaya.seqayamvpclean.data.datasource.remote.network.ProductService;
import com.ntg.seqaya.seqayamvpclean.domain.entity.Product;
import com.ntg.seqaya.seqayamvpclean.domain.repository.ErrorCallback;
import com.ntg.seqaya.seqayamvpclean.domain.repository.SuccessCallback;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        try {
            Response<List<Product>> response = ApiClient.getClient()
                    .create(ProductService.class)
                    .getProducts().execute();
            successCallback.onSuccess(response.body());
        } catch (IOException e) {
            errorCallback.onError("تأكد من اتصال الانترنت");
        }
    }

    @Override
    public void getSearchResult(String searchKeyword,
                                SuccessCallback<List<Product>> successCallback,
                                ErrorCallback errorCallback) {
        try {
            Response<List<Product>> response = ApiClient.getClient()
                    .create(ProductService.class)
                    .getSearchResult(searchKeyword).execute();
            successCallback.onSuccess(response.body());
        } catch (IOException e) {
            errorCallback.onError("تأكد من اتصال الانترنت");
        }
    }
}
