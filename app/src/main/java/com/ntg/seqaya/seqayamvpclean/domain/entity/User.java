package com.ntg.seqaya.seqayamvpclean.domain.entity;

import java.io.Serializable;

/**
 * Created by ilias on 27/03/2018.
 */

public class User implements Serializable {

    private static String email;
    private static String password;
    private static String userToken;
    private static String firstName;
    private static String middleName;
    private static String lastName;
    private static String phoneNumber;
    private static boolean isCorporate;
    private static ShoppingCart shoppingCart = new ShoppingCart();

    public static ShoppingCart getShoppingCart() {
        return shoppingCart;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        User.email = email;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        User.password = password;
    }

    public static String getUserToken() {
        return userToken;
    }

    public static void setUserToken(String userToken) {
        User.userToken = userToken;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        User.firstName = firstName;
    }

    public static String getMiddleName() {
        return middleName;
    }

    public static void setMiddleName(String middleName) {
        User.middleName = middleName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        User.lastName = lastName;
    }

    public static String getPhoneNumber() {
        return phoneNumber;
    }

    public static void setPhoneNumber(String phoneNumber) {
        User.phoneNumber = phoneNumber;
    }

    public static boolean isIsCorporate() {
        return isCorporate;
    }

    public static void setIsCorporate(boolean isCorporate) {
        User.isCorporate = isCorporate;
    }

    public static void setShoppingCart(ShoppingCart shoppingCart) {
        User.shoppingCart = shoppingCart;
    }

    public static void clearCart(){
        shoppingCart.getCartItemList().clear();
    }
}
