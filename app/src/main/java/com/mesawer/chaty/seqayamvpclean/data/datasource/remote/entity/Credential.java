package com.mesawer.chaty.seqayamvpclean.data.datasource.remote.entity;

import java.io.Serializable;

/**
 * Created by ilias on 27/03/2018.
 */

public class Credential implements Serializable{

    private String email;
    private String password;

    public Credential(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
