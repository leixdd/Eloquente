package eloquente.com.eloquente.API;

import java.util.List;

import eloquente.com.eloquente.Models.Client;
import eloquente.com.eloquente.Models.Menus;
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

}
