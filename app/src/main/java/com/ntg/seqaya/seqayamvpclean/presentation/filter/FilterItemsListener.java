package com.ntg.seqaya.seqayamvpclean.presentation.filter;

import java.io.Serializable;
import java.util.List;

/**
 * Created by GM7 on 2018-04-22.
 */

public interface FilterItemsListener extends Serializable{
    void onFilterItemsSelected(List<String> items);
}
