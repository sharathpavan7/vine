package com.sharath.vine.response;

import com.google.gson.annotations.SerializedName;
import com.sharath.vine.modell.HotKeys;
import com.sharath.vine.modell.WHProducts;

import java.util.ArrayList;

public class HomeListResponse {
    @SerializedName("genral_list")
    public ArrayList<WHProducts> homeList;

    @SerializedName("featuredData")
    public ArrayList<WHProducts> featuredList;

    @SerializedName("hotkeys")
    public ArrayList<HotKeys> hotKeyList;

    @SerializedName("response")
    public int response;

    @SerializedName("message")
    public String message;

    @SerializedName("pageno")
    public int currentPage;

    @SerializedName("total_pages")
    public int totalPages;

    @SerializedName("cart_item")
    public int cartCount;
}
