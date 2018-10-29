package eloquente.com.eloquente.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ARIELLECIAS on 10/26/2018.
 */

public class ApiClient {

    //http://eloquenteservices.com/eapi/
    public static String BASE_URL = "http://10.0.2.2:8001/";

    private static Retrofit retrofit = null;
    public static Retrofit getClient() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public static void setBaseUrl(String url) {
        BASE_URL =  url.equals("") ? "https://eloquentx12.herokuapp.com/" :  "http://" + url +  "/";
    }

}
