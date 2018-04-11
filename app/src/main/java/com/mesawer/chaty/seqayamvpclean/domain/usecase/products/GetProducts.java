package com.mesawer.chaty.seqayamvpclean.domain.usecase.products;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;
import com.mesawer.chaty.seqayamvpclean.domain.repository.IProductsRepository;

import java.util.List;

public class GetProducts implements UseCase<GetProducts.RequestValues, GetProducts.ResponseValues> {

    private IProductsRepository iProductsRepository;

    public GetProducts(IProductsRepository iProductsRepository) {
        this.iProductsRepository = iProductsRepository;
    }

    @Override
    public void execute(RequestValues requestValue,
                        UseCaseSuccessCallback<ResponseValues> successCallback,
                        UseCaseErrorCallback errorCallback) {
        iProductsRepository.getProducts(result -> {
            ResponseValues responseValues = new ResponseValues(result);
            successCallback.onSuccess(responseValues);
        },errorCallback::onError);
    }

    public static final class RequestValues implements UseCase.RequestValues{

    }

    public static final class ResponseValues implements UseCase.ResponseValues{
        List<Product> products;

        public ResponseValues(List<Product> products) {
            this.products = products;
        }

        public List<Product> getProducts(){
            return  products;
        }
    }
}
