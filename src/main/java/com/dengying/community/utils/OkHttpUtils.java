package com.dengying.community.utils;

import okhttp3.*;
public class OkHttpUtils {
    private static OkHttpClient client = new OkHttpClient();
    public static final MediaType mediaType = MediaType.get("application/json; charset=utf-8");
    public static String get(String url){
        Request request = new Request.Builder().url(url).build();
        try {
            Response response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static String post(String url, String json){
    RequestBody body = RequestBody.create(mediaType, json);
        Request request = new Request.Builder().url(url).post(body).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();
            return response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }
       return null;
    }
}
