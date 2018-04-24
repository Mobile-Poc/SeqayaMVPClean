package com.ntg.seqaya.seqayamvpclean.domain.entity;

import java.util.List;

/**
 * Created by islam on 4/22/2018.
 */

public class LocalFavourite {
    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
