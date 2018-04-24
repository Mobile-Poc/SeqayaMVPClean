package com.ntg.seqaya.seqayamvpclean.domain.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by Sara Elmoghazy on 26/03/2018.
 */

@Retention(SOURCE)
@StringDef({
        SortBy.PRICE,
        SortBy.SIZE,
        SortBy.PACKET_SIZE
})
public @interface SortBy {

    String PRICE = "price";
    String SIZE = "size";
    String PACKET_SIZE = "packetSize";
}
