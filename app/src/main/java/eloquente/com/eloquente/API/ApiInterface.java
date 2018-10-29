package eloquente.com.eloquente.API;

import java.util.List;

import eloquente.com.eloquente.Models.Client;
import eloquente.com.eloquente.Models.Menus;
import eloquente.com.eloquente.Models.Reservations;
import eloquente.com.eloquente.Models.Reserve;
import eloquente.com.eloquente.Models.Reviews;
import eloquente.com.eloquente.Models.Services;
import eloquente.com.eloquente.Models.WebResponse;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by ARIELLECIAS on 10/26/2018.
 */

public interface ApiInterface {

    @FormUrlEncoded
    @POST("login.php")
    Call<Client> login(
            @Field("username") String username,
            @Field("password") String password);

    @FormUrlEncoded
    @POST("register.php")
    Call<WebResponse> register(
            @Field("email") String email,
            @Field("password") String password,
            @Field("fn") String fn,
            @Field("ln") String ln,
            @Field("mn") String mn,
            @Field("phone") String phone
    );

    @FormUrlEncoded
    @POST("actions.php")
    Call<List<Menus>> getAllMenus(@Field("action") String action);

    @FormUrlEncoded
    @POST("actions.php")
    Call<List<Menus>> getMenuCat(@Field("action") String action, @Field("cat") String cat);

    @FormUrlEncoded
    @POST("actions.php")
    Call<List<Reviews>> getReviews(@Field("action") String action, @Field("menu_id") String id);

    @FormUrlEncoded
    @POST("actions.php")
    Call<WebResponse> comment(
            @Field("action") String action,
            @Field("menu_id") String menu_id,
            @Field("account_id") String account_id,
            @Field("comment") String comment,
            @Field("ratings") String ratings
    );

    @FormUrlEncoded
    @POST("actions.php")
    Call<Services> getPackage(@Field("action") String action, @Field("service_id") String id);

    @FormUrlEncoded
    @POST("actions.php")
    Call<WebResponse> reservation(
            @Field("action") String action,
            @Field("account_id") String account_id,
            @Field("package_id") String package_id,
            @Field("inclusions") String inclusions,
            @Field("date") String date,
            @Field("timeStart") String timeStart,
            @Field("timeEnd") String timeEnd,
            @Field("totalAmount") String totalAmount,
            @Field("menu") String menu
            );

    @FormUrlEncoded
    @POST("actions.php")
    Call<List<Reservations>> getAllReserves(@Field("action") String action, @Field("account_id") String account_id);

    @FormUrlEncoded
    @POST("actions.php")
    Call<WebResponse> payment(
            @Field("action") String action,
            @Field("email") String email,
            @Field("token") String token,
            @Field("schedule_id") String sid,
            @Field("amount") String amount,
            @Field("total_amount") String ta,
            @Field("remaining_balance") String rb
    );



}
