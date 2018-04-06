package com.mesawer.chaty.seqayamvpclean.domain.usecase.products;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;

import java.util.List;

public class GetProducts implements UseCase<GetProducts.RequestValues, GetProducts.ResponseValues> {


    @Override
    public void execute(RequestValues requestValue, UseCaseCallback<ResponseValues> useCaseCallback) {
    }

    class RequestValues implements UseCase.RequestValues{

    }

    class ResponseValues implements UseCase.ResponseValues{
        List<Product> products;

        public ResponseValues(List<Product> products) {
            this.products = products;
        }

        public List<Product> getProducts(){
            return  products;
        }
    }
}
