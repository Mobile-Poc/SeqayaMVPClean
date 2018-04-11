package com.mesawer.chaty.seqayamvpclean.data.datasource.remote.network;

import com.google.gson.Gson;
import com.mesawer.chaty.seqayamvpclean.data.datasource.remote.entity.APIError;

public class Util {
    public static String apiErrMsg(String jsonString) {
        Gson gson = new Gson();
        APIError apiError = gson.fromJson(jsonString, APIError.class);
        return apiError.getMessage();
    }
}
