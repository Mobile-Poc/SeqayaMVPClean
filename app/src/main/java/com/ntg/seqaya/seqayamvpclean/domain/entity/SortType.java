package com.ntg.seqaya.seqayamvpclean.domain.entity;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.SOURCE;

/**
 * Created by Sara Elmoghazy on 26/03/2018.
 */

@Retention(SOURCE)
@StringDef({
        SortType.DESC,
        SortType.ASC,
})
public @interface SortType {

    String DESC = "DESC";
    String ASC = "ASC";
}
