package com.mesawer.chaty.seqayamvpclean.utils;

import com.mesawer.chaty.seqayamvpclean.data.ProductsRepository;
import com.mesawer.chaty.seqayamvpclean.data.remote.ProductsRemoteDataSource;
import com.mesawer.chaty.seqayamvpclean.domain.usecase.login.EmailPasswordLogin;

public class Injection {

    public static ProductsRepository provideProductsRepository() {
        return ProductsRepository.getInstance(ProductsRemoteDataSource.getInstance());
    }

    public static EmailPasswordLogin provideEmailPasswordLogin() {
        return new EmailPasswordLogin(provideProductsRepository());
    }
}
