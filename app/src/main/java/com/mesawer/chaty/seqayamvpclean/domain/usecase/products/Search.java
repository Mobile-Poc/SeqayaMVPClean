package com.mesawer.chaty.seqayamvpclean.domain.usecase.products;

import com.mesawer.chaty.seqayamvpclean.base.UseCase;
import com.mesawer.chaty.seqayamvpclean.domain.entity.Product;
import com.mesawer.chaty.seqayamvpclean.domain.repository.IProductsRepository;

import java.util.List;

public class Search extends UseCase<Search.RequestValues, Search.ResponseValues> {

    private IProductsRepository iProductsRepository;

    public Search(IProductsRepository iProductsRepository) {
        this.iProductsRepository = iProductsRepository;
    }

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        iProductsRepository.getSearchResult(requestValues.getKeyWord(), result -> {
            ResponseValues responseValues = new ResponseValues(result);
            getUseCaseCallback().onSuccess(responseValues);
        }, getUseCaseCallback()::onError);
    }

    public static final class RequestValues implements UseCase.RequestValues {
        String keyWord;

        public RequestValues(String keyWord) {
            this.keyWord = keyWord;
        }

        public String getKeyWord() {
            return keyWord;
        }
    }

    public static final class ResponseValues implements UseCase.ResponseValues {
        List<Product> products;

        public ResponseValues(List<Product> products) {
            this.products = products;
        }

        public List<Product> getProducts() {
            return products;
        }
    }
}
