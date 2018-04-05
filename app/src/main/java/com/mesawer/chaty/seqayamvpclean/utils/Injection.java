package com.mesawer.chaty.seqayamvpclean.utils;

import com.mesawer.chaty.seqayamvpclean.data.ProductsRepository;
import com.mesawer.chaty.seqayamvpclean.data.remote.ProductsRemoteDataSource;

public class Injection {

    public static ProductsRepository provideProductsRepository(){
        return ProductsRepository.getInstance(ProductsRemoteDataSource.getInstance());
    }
}
