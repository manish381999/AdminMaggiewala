package com.tie.adminmaggiewala.ApiClient;


import com.tie.adminmaggiewala.ui.Credential.LoginModel;
import com.tie.adminmaggiewala.ui.Menu.Add_Menu_Model;
import com.tie.adminmaggiewala.ui.Menu.Menu_List_Model;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;

import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginModel> logincall(
            @Field("email") String email,
            @Field("password") String password);

    @Multipart
    @Headers({"Accept: application/json"})
    @POST("insert_menuitems.php")
    Call<Add_Menu_Model> insertMenuItem(
            @Part("item_name")RequestBody item_name,
            @Part("item_price")RequestBody item_price,
            @Part MultipartBody.Part item_img,
            @Part("short_description")RequestBody short_description,
            @Part("ingredient")RequestBody ingredient
            );

//    @GET("get_menu_item.php")
//    Call<Menu_List_Model> getMenuList();
}
